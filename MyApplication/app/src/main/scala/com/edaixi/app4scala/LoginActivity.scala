package com.edaixi.app4scala


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader
import android.database.Cursor
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.{Editable, TextWatcher, TextUtils}
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.ArrayList
import java.util.List
import android.Manifest.permission.READ_CONTACTS
import com.edaixi.app4scala.http.LoginUtil

/**
 *
 * @author wei-springs
 *
 *         A login screen that offers login via email/password.
 **/
object LoginActivity {
  /**
   * Id to identity READ_CONTACTS permission request.
   */
  private val REQUEST_READ_CONTACTS: Int = 0
  /**
   * A dummy authentication store containing known user names and passwords.
   * TODO: remove after connecting to a real authentication system.
   */
  private val DUMMY_CREDENTIALS: Array[String] = Array[String]("foo@example.com:hello", "bar@example.com:world")

  private object ProfileQuery {
    val PROJECTION: Array[String] = Array(ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract.CommonDataKinds.Email.DISPLAY_NAME)
    val ADDRESS: Int = 0
    val IS_PRIMARY: Int = 1
  }

}

class LoginActivity extends AppCompatActivity with LoaderCallbacks[Cursor] {
  /**
   * Keep track of the login task to ensure we can cancel it if requested.
   */
  private var mAuthTask: LoginActivity#UserLoginTask = null
  private var mEmailView: AutoCompleteTextView = null
  private var mPasswordView: EditText = null
  private var mProgressView: View = null
  private var mLoginFormView: View = null

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    mEmailView = findViewById(R.id.email).asInstanceOf[AutoCompleteTextView]
    populateAutoComplete
    mPasswordView = findViewById(R.id.password).asInstanceOf[EditText]
    mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      def onEditorAction(textView: TextView, id: Int, keyEvent: KeyEvent): Boolean = {
        if (id == R.id.login || id == EditorInfo.IME_NULL) {
          attemptLogin
          return true
        }
        return false
      }
    })
    val mEmailSignInButton: Button = findViewById(R.id.email_sign_in_button).asInstanceOf[Button]
    mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
      def onClick(view: View) {
        attemptLogin
      }
    })
    mLoginFormView = findViewById(R.id.login_form)
    mProgressView = findViewById(R.id.login_progress)
  }

  private def populateAutoComplete {
    if (!mayRequestContacts) {
      return
    }
    getLoaderManager.initLoader(0, null, this)
  }

  private def mayRequestContacts: Boolean = {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      return true
    }
    if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
      return true
    }
    if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
      Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.M) def onClick(v: View) {
          requestPermissions(Array[String](READ_CONTACTS), LoginActivity.REQUEST_READ_CONTACTS)
        }
      })
    }
    else {
      requestPermissions(Array[String](READ_CONTACTS), LoginActivity.REQUEST_READ_CONTACTS)
    }
    return false
  }

  /**
   * Callback received when a permissions request has been completed.
   */
  override def onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array[String], @NonNull grantResults: Array[Int]) {
    if (requestCode == LoginActivity.REQUEST_READ_CONTACTS) {
      if (grantResults.length == 1 && grantResults(0) == PackageManager.PERMISSION_GRANTED) {
        populateAutoComplete
      }
    }
  }

  /**
   * Attempts to sign in or register the account specified by the login form.
   * If there are form errors (invalid email, missing fields, etc.), the
   * errors are presented and no actual login attempt is made.
   */
  private def attemptLogin {
    if (mAuthTask != null) {
      return
    }
    mEmailView.setError(null)
    mPasswordView.setError(null)
    val email: String = mEmailView.getText.toString
    val password: String = mPasswordView.getText.toString
    var cancel: Boolean = false
    var focusView: View = null
    if (TextUtils.isEmpty(password)) {
      mPasswordView.setError(getString(R.string.error_field_required))
      focusView = mPasswordView
      cancel = true
    }
    else if (!isPasswordValid(password)) {
      mPasswordView.setError(getString(R.string.error_invalid_password))
      focusView = mPasswordView
      cancel = true
    }
    if (TextUtils.isEmpty(email)) {
      mEmailView.setError(getString(R.string.error_field_required))
      focusView = mEmailView
      cancel = true
    }
    else if (!isEmailValid(email)) {
      mEmailView.setError(getString(R.string.error_invalid_email))
      focusView = mEmailView
      cancel = true
    }
    if (cancel) {
      focusView.requestFocus
    }
    else {
      showProgress(true)
      //mAuthTask = new LoginActivity#UserLoginTask(email, password)
      //mAuthTask.execute(null.asInstanceOf[Void])
    }
  }

  private def isEmailValid(email: String): Boolean = {
    return email.length == 11
  }

  private def isPasswordValid(password: String): Boolean = {
    return password.length > 3 && password.matches("[0-9]*")
  }

  /**
   * Shows the progress UI and hides the login form.
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2) private def showProgress(show: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      val shortAnimTime: Int = getResources.getInteger(android.R.integer.config_shortAnimTime)
      mLoginFormView.setVisibility(if (show) View.GONE else View.VISIBLE)
      mLoginFormView.animate.setDuration(shortAnimTime).alpha(if (show) 0 else 1).setListener(new AnimatorListenerAdapter() {
        override def onAnimationEnd(animation: Animator) {
          mLoginFormView.setVisibility(if (show) View.GONE else View.VISIBLE)
        }
      })
      mProgressView.setVisibility(if (show) View.VISIBLE else View.GONE)
      mProgressView.animate.setDuration(shortAnimTime).alpha(if (show) 1 else 0).setListener(new AnimatorListenerAdapter() {
        override def onAnimationEnd(animation: Animator) {
          mProgressView.setVisibility(if (show) View.VISIBLE else View.GONE)
        }
      })
    }
    else {
      mProgressView.setVisibility(if (show) View.VISIBLE else View.GONE)
      mLoginFormView.setVisibility(if (show) View.GONE else View.VISIBLE)
    }
  }

  def onCreateLoader(i: Int, bundle: Bundle): Loader[Cursor] = {
    return null
    //return new CursorLoader(this, Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY), LoginActivity.ProfileQuery.PROJECTION, ContactsContract.Contacts.Data.MIMETYPE + " = ?", Array[String](ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE), ContactsContract.Contacts.Data.IS_PRIMARY + " DESC")
  }

  def onLoadFinished(cursorLoader: Loader[Cursor], cursor: Cursor) {
    val emails: List[String] = new ArrayList[String]
    cursor.moveToFirst
    while (!cursor.isAfterLast) {
      emails.add(cursor.getString(LoginActivity.ProfileQuery.ADDRESS))
      cursor.moveToNext
    }
    addEmailsToAutoComplete(emails)
  }

  def onLoaderReset(cursorLoader: Loader[Cursor]) {
  }

  private def addEmailsToAutoComplete(emailAddressCollection: List[String]) {
    val adapter: ArrayAdapter[String] = new ArrayAdapter[String](LoginActivity.this, android.R.layout.simple_dropdown_item_1line, emailAddressCollection)
    mEmailView.setAdapter(adapter)
  }

  /**
   * Represents an asynchronous login/registration task used to authenticate
   * the user.
   */
  class UserLoginTask extends AsyncTask[Void, Void, Boolean] {
    private final var mEmail: String = null
    private final var mPassword: String = null

    private[app4scala] def this(email: String, password: String) {
      this()
      mEmail = email
      mPassword = password
    }

    protected def doInBackground(params: Void*): Boolean = {
      try {
        Thread.sleep(2000)
      }
      catch {
        case e: InterruptedException => {
          return false
        }
      }
      for (credential <- LoginActivity.DUMMY_CREDENTIALS) {
        val pieces: Array[String] = credential.split(":")
        if (pieces(0) == mEmail) {
          return pieces(1) == mPassword
        }
      }
      return true
    }

    protected override def onPostExecute(success: Boolean) {
      mAuthTask = null
      showProgress(false)
      if (success) {
        finish
      }
      else {
        mPasswordView.setError(getString(R.string.error_incorrect_password))
        mPasswordView.requestFocus
      }
    }

    protected override def onCancelled {
      mAuthTask = null
      showProgress(false)
    }
  }

}

