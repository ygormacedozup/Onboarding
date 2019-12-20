package br.com.zup.onboarding.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

public class GoogleAuthentication {
    private GoogleAuthentication instance;
    private Activity activity;
    private GoogleSignInOptions gso;
    private GoogleSignInClient client;
    //private GoogleSignInAccount account;
    private String userName;
    private String userEmail;
    private Uri userPhoto;

    public GoogleAuthentication(Activity activity/*, GoogleSignInAccount account*/) {
        this.activity = activity;
        //this.account = account;
        start();
    }

    private void start() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        client = GoogleSignIn.getClient(activity, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);

        if (account != null ) {
            setUserData(account.getDisplayName(), account.getEmail(), account.getPhotoUrl());
        }
    }

    public Task<Void> signOut() {
        return client.signOut();
    }

    private void setUserData(String name, String email, Uri photo) {
        userName = name;
        userEmail = email;
        userPhoto = photo;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Uri getUserPhoto() {
        return userPhoto;
    }

    public Intent getSignInIntent() {
        return client.getSignInIntent();
    }
}
