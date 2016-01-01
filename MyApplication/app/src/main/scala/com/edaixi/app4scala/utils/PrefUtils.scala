package com.edaixi.app4scala.utils

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by weichunsheng on 16/1/1.
 */
class PrefUtils {
  /**
   * Called to save supplied value in shared preferences against given key.
   * @param context Context of caller activity
   * @param key Key of value to save against
   * @param value Value to save
   */
  def saveToPrefs(context: Context, key: String, value: String) {
    val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor: SharedPreferences.Editor = prefs.edit
    editor.putString(key, value)
    editor.commit
  }

  /**
   * Called to retrieve required value from shared preferences, identified by given key.
   * Default value will be returned of no value found or error occurred.
   * @param context Context of caller activity
   * @param key Key to find value against
   * @param defaultValue Value to return if no data found against given key
   * @return Return the value found against given key, default if not found or any error occurs
   */
  def getFromPrefs(context: Context, key: String, defaultValue: String): String = {
    val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    try {
      return sharedPrefs.getString(key, defaultValue)
    }
    catch {
      case e: Exception => {
        e.printStackTrace
        return defaultValue
      }
    }
  }

  /**
   *
   * @param context Context of caller activity
   * @param key Key to delete from SharedPreferences
   */
  def removeFromPrefs(context: Context, key: String) {
    val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor: SharedPreferences.Editor = prefs.edit
    editor.remove(key)
    editor.commit
  }
}
