package br.com.zup.onboarding.android.contract;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginContract {
    interface View {
        void setLoginButtonClickListener();
        void startSignInIntent();
        void navigateToHome(GoogleSignInAccount account);
    }

    interface Presenter {
        void start(View view);
        void stop();
        void onSignInClicked();
        Intent getSignInIntent();
        void onSignInResultReceived(Intent data);
    }
}
