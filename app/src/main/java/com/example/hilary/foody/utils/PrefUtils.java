package com.example.hilary.foody.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Lawrence on 10/12/16.
 */

public class PrefUtils {

    /**
     * Shared Preferences
     */
    private SharedPreferences sharedPreferencesCompat;

    /**
     * Editor for Shared preferences
     */
    private SharedPreferences.Editor editor;

    /**
     * Context
     */
    private Context mContext;

    /**
     * Shared pref mode
     */
    private int PRIVATE_MODE = 0;

    /**
     * Shared preferences file name     */
    private static final String PREF_NAME = "FoodyPrefs";

    /**
     *
     */
    private static final String KEY_IS_LOGGED_IN = "isUserLoggedIn";


    private static final String KEY_ACCESS_TOKEN = "token";

    private static final String KEY_USER_ID = "user_id";

    private static final String KEY_IS_WAITING_FOR_SMS = "IsUserWaitingForSms";

    private static final String KEY_IS_NEW_USER = "IsNewUser";

    private static final String KEY_NAME = "name";

    private static final String KEY_PHONE = "phone";

    private static final String KEY_TEAM = "team";

    private static final String KEY_BALANCE = "balance";

    private static final String KEY_BETS_SYNC_JOB = "bets_sync_job";

    private static final String KEY_INVITES_JOB = "invites_job";

    private static final String KEY_NOTIFICATIONS_COUNT = "notifications_count";

    private static final String KEY_HAMBURGER_OVERLAY_SHOWN = "hamburger_overlay_shown";

    private static final String KEY_ONLINE_CHECKOUT_DIALOG = "online_checkout_dialog";


    public PrefUtils(Context mContext) {
        this.mContext = mContext;
        this.sharedPreferencesCompat = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = this.sharedPreferencesCompat.edit();
    }


    public boolean isLoggedIn() {
        return sharedPreferencesCompat.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public boolean isWaitingForSms() {
        return sharedPreferencesCompat.getBoolean(KEY_IS_WAITING_FOR_SMS, false);
    }

    public void setIsWaitingForSms(boolean isWaiting) {
        editor.putBoolean(KEY_IS_WAITING_FOR_SMS, isWaiting);
        editor.commit();
    }

    public void setBetsSyncJobStarted(boolean isJobStarted) {
        editor.putBoolean(KEY_BETS_SYNC_JOB, isJobStarted);
        editor.commit();
    }

    public boolean isBetsSyncJobStarted() {
        return sharedPreferencesCompat.getBoolean(KEY_BETS_SYNC_JOB, false);
    }


    public void setInvitesJobStarted(boolean isJobStarted) {
        editor.putBoolean(KEY_INVITES_JOB, isJobStarted);
        editor.commit();
    }

    public boolean isInvitesJobStarted() {
        return sharedPreferencesCompat.getBoolean(KEY_INVITES_JOB, false);
    }

    public String getUserAccessToken() {
        return sharedPreferencesCompat.getString(KEY_ACCESS_TOKEN, null);
    }

    public void setUserAccessToken(String token) {
        editor.putString(KEY_ACCESS_TOKEN, token);
        editor.commit();
    }

    public int getUserId() {
        return sharedPreferencesCompat.getInt(KEY_USER_ID, 0);
    }

    public void setUserId(int user_id) {
        editor.putInt(KEY_USER_ID, user_id);
        editor.commit();
    }

    public boolean isNewUser() {
        return sharedPreferencesCompat.getBoolean(KEY_IS_NEW_USER, false);
    }

    public void setIsNewUser(boolean isNewUser) {
        editor.putBoolean(KEY_IS_NEW_USER, isNewUser);
        editor.commit();
    }



    public void setUserBalance(String balance) {
        editor.putString(KEY_BALANCE, balance);
        editor.commit();
    }

    public String getUserBalance() {
        return sharedPreferencesCompat.getString(KEY_BALANCE, null);
    }

    public void hideOnlineCheckoutDialog(boolean hideDialog) {
        editor.putBoolean(KEY_ONLINE_CHECKOUT_DIALOG, hideDialog);
        editor.commit();
    }

    public boolean isOnlineCheckoutDialogHidden() {
        return sharedPreferencesCompat.getBoolean(KEY_ONLINE_CHECKOUT_DIALOG, false);
    }

    public String getUserPhone() {
        return sharedPreferencesCompat.getString(KEY_PHONE, null);
    }

    public void setNewNotificationCount() {
        int current_notification_count = sharedPreferencesCompat.getInt(KEY_NOTIFICATIONS_COUNT, 0);
        int new_notification_count = ++current_notification_count;
        editor.putInt(KEY_NOTIFICATIONS_COUNT, new_notification_count);
        editor.commit();
    }

    public int getNewNotificationCount() {
        return sharedPreferencesCompat.getInt(KEY_NOTIFICATIONS_COUNT, 0);
    }

    public void clearNotificationCount() {
        editor.remove(KEY_NOTIFICATIONS_COUNT);
        editor.commit();
    }

    public boolean isHamburgerOverlayShown() {
        return sharedPreferencesCompat.getBoolean(KEY_HAMBURGER_OVERLAY_SHOWN, false);
    }

    public void markHamburgerOverlayAsShown(boolean isShown) {
        editor.putBoolean(KEY_HAMBURGER_OVERLAY_SHOWN, isShown);
        editor.commit();
    }

    public void storeUserDetails(String name, String phone, String team, String balance) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_TEAM, team);
        editor.putString(KEY_BALANCE, balance);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();
        profile.put("name", sharedPreferencesCompat.getString(KEY_NAME, null));
        profile.put("phone", sharedPreferencesCompat.getString(KEY_PHONE, null));
        profile.put("team", sharedPreferencesCompat.getString(KEY_TEAM, null));
        profile.put("balance", sharedPreferencesCompat.getString(KEY_BALANCE, null));
        return profile;
    }
}
