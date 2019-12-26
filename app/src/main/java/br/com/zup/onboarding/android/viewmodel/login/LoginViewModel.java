package br.com.zup.onboarding.android.viewmodel.login;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.model.entity.User;

public class LoginViewModel extends ViewModel {
    private GoogleAuthentication authentication;
    private int RC_SIGN_IN = 0;
    private LoginResultEvent loginResultEvent;
    private MutableLiveData<LoginState> stateLiveData = new MutableLiveData<>();

    public LoginViewModel() {
    }

    public void setAuthentication(GoogleAuthentication authentication) {
        this.authentication = authentication;
    }

    public LiveData<LoginState> getStateLiveData() {
        return stateLiveData;
    }

    public void setLoginResultEvent(LoginResultEvent loginResultEvent) {
        this.loginResultEvent = loginResultEvent;
    }

    public void startSignInIntent() {
        Intent intent = authentication.getSignInIntent();
        loginResultEvent.onResult(intent, RC_SIGN_IN);
    }

    public void onSignInResultReceived(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            User user = new User();

            if (account != null) {
                user.setId(1);
                user.setName(account.getDisplayName());
                user.setEmail(account.getEmail());
            }

            stateLiveData.setValue(LoginState.SUCCESS);
        } catch (ApiException e) {
            stateLiveData.setValue(LoginState.ERROR);
        }
    }
}
