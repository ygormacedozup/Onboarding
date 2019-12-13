package br.com.zup.onboarding.android.presenter;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private GoogleAuthentication authentication;

    public LoginPresenter(Activity activity) {
        authentication = new GoogleAuthentication(activity);
    }

    @Override
    public void start(LoginContract.View view) {
        this.view = view;
        view.setLoginButtonClickListener();
    }

    @Override
    public void stop() {
        view = null;
    }

    @Override
    public void onSignInClicked() {
        view.startSignInIntent();
    }

    @Override
    public Intent getSignInIntent() {
        return authentication.getSignInIntent();
    }

    @Override
    public void onSignInResultReceived(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            view.navigateToHome(account);
        } catch (ApiException e) {
            String ERROR_MESSAGE = "Por favor, faça o login com email zup!";
            view.showErrorMessage(ERROR_MESSAGE);
        }
    }
}