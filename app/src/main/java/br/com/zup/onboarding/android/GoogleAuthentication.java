package br.com.zup.onboarding.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

public class GoogleAuthentication {
    private Context context;
    private GoogleSignInClient client;
    private String userName;
    private String userEmail;
    private Uri userPhoto;

    public GoogleAuthentication(Context context) {
        //start(context);
        this.context = context;
        start();
    }

    //private void start(Context context) {
    private void start() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        client = GoogleSignIn.getClient(context, gso);
    }

    public void setAccount(GoogleSignInAccount account) {
        userName = account.getDisplayName();
        userEmail = account.getEmail();
        userPhoto = account.getPhotoUrl();
    }

    //public GoogleSignInAccount getLastSignedInAccount(Context context) {
    public GoogleSignInAccount getLastSignedInAccount() {
        return GoogleSignIn.getLastSignedInAccount(context);
    }

    public Task<Void> signOut() {
        return client.signOut();
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
