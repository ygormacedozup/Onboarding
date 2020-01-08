package br.com.zup.onboarding.android.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

public class UserSessionManager {
    private SharedPreferences preferences;
    private final String EMAIL_KEY = "email";
    private final String EMPTY_EMAIL = "";

    public UserSessionManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setEmail(String email) {
        preferences.edit().putString(EMAIL_KEY, email).apply();
        Log.e("Session set", email);
    }

    public String getEmail() {
        String email = preferences.getString(EMAIL_KEY, EMPTY_EMAIL);
        Log.e("Session get", email);
        return email;
    }
}
