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
import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.Location;
import br.com.zup.onboarding.android.model.entity.Pod;
import br.com.zup.onboarding.android.model.entity.User;

public class LoginViewModel extends ViewModel {
    private final UserRepository repository;
    private GoogleAuthentication authentication;
    private final int RC_SIGN_IN = 0;
    private LoginResultEvent loginResultEvent;
    private final MutableLiveData<LoginState> stateLiveData = new MutableLiveData<>();

    public LoginViewModel() {
        repository = new UserRepository();
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

            if (account != null) {
                authentication.setAccount(account);
            }

            stateLiveData.setValue(LoginState.SIGN_IN_SUCCESS);
        } catch (ApiException e) {
            stateLiveData.setValue(LoginState.SIGN_IN_ERROR);
        }
    }

    public void saveUser(String podName, String locationName) {
        User user = new User();

        user.setName(authentication.getUserName());
        user.setEmail(authentication.getUserEmail());

        user.setPod(new Pod(podName));
        user.setLocation(new Location(locationName));

        repository.saveUser(user);
        stateLiveData.setValue(LoginState.REGISTER_SUCCESS);
    }

    public String getUserName() {
        return authentication.getUserName();
    }
}
