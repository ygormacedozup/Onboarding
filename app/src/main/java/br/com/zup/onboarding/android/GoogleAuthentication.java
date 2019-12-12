package br.com.zup.onboarding.android;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleAuthentication {
    private Activity activity;
    private GoogleSignInOptions gso;
    private GoogleSignInClient client;

    public GoogleAuthentication(Activity activity) {
        this.activity = activity;
        start();
    }

    private void start() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(activity, gso);
    }

    public Intent getSignInIntent() {
        return client.getSignInIntent();
    }
}
