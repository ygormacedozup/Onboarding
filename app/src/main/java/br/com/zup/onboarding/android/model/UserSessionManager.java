package br.com.zup.onboarding.android.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

public class UserSessionManager {
    private SharedPreferences preferences;
    private final String EMAIL_KEY = "email";
    private final String EMPTY_EMAIL = "";
    private final String NAME_KEY = "name";
    private final String EMPTY_NAME = "";

    public UserSessionManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setName(String name) {
        preferences.edit().putString(NAME_KEY, name).apply();
        Log.e("session set name", name);
    }

    public void setEmail(String email) {
        preferences.edit().putString(EMAIL_KEY, email).apply();
        Log.e("session set email", email);
    }

    public String getName() {
        String name = preferences.getString(NAME_KEY, EMPTY_NAME);
        Log.e("session get name", name);
        return name;
    }

    public String getEmail() {
        String email = preferences.getString(EMAIL_KEY, EMPTY_EMAIL);
        Log.e("session get email", email);
        return email;
    }
}
