package com.example.hilary.foody.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hilary on 4/5/17.
 */

public class PrefUtils {

    /**
     * Shared Preferences
     */
    SharedPreferences sharedPreferencesCompat;

    /**
     * Editor for Shared preferences
     */
    SharedPreferences.Editor editorCompat;

    /**
     * Context
     */
    Context mContext;

    /**
     * Shared pref mode
     */
    private int PRIVATE_MODE = 0;

    /**
     * Shared preferences file name
     */
    private static final String PREF_NAME = "FoodyCookPrefs";

    public PrefUtils(Context mContext) {
        this.mContext = mContext;
        this.sharedPreferencesCompat = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editorCompat = this.sharedPreferencesCompat.edit();
    }
}
