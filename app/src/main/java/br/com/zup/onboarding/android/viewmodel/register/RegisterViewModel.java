package br.com.zup.onboarding.android.viewmodel.register;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.UserSessionManager;
import br.com.zup.onboarding.android.model.entity.Location;
import br.com.zup.onboarding.android.model.entity.Pod;
import br.com.zup.onboarding.android.model.entity.User;
import br.com.zup.onboarding.android.model.entity.ZupperExists;

public class RegisterViewModel extends ViewModel {
    private final UserRepository repository;
    private GoogleAuthentication authentication;
    private UserSessionManager manager;
    private final int RC_SIGN_IN = 0;
    private LoginResultEvent loginResultEvent;
    private final MutableLiveData<RegisterState> stateLiveData = new MutableLiveData<>();


    private MutableLiveData<ZupperExists> zupperExistsLiveData = new MutableLiveData<>();

    public RegisterViewModel() {
        repository = UserRepository.getInstance();
    }

    public void setAuthentication(GoogleAuthentication authentication) {
        this.authentication = authentication;
    }

    public void setUserSessionManager(UserSessionManager manager) {
        this.manager = manager;
        verifySessionSaved();
        verifyZupperExists(manager.getName(), manager.getEmail());
    }

    private void verifySessionSaved() {
        String email = manager.getEmail();

        if (email != null && !email.isEmpty()) {
            stateLiveData.setValue(RegisterState.ALREADY_LOGGED);
        }
    }

    private void verifyZupperExists(String name, String email) {
        ZupperExists zupper = new ZupperExists();
        zupper.setName(name);
        zupper.setEmail(email);

        repository.checkUserExists(zupper);
        zupperExistsLiveData = repository.getZupperExistsLiveData();
    }

    public void setState(RegisterState state) {
        stateLiveData.setValue(state);
    }

    public LiveData<RegisterState> getStateLiveData() {
        return stateLiveData;
    }

    public LiveData<ZupperExists> getZupperExistsLiveData() {
        return zupperExistsLiveData;
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
                Log.e("Sign In Account", Objects.requireNonNull(account.getEmail()));
                authentication.setAccount(account);

                verifyZupperExists(account.getDisplayName(), account.getEmail());
            }

            stateLiveData.setValue(RegisterState.SIGN_IN_SUCCESS);
        } catch (ApiException e) {
            stateLiveData.setValue(RegisterState.SIGN_IN_ERROR);
        }
    }

    public void saveUser(String podName, String locationName) {
        User user = new User();

        user.setName(authentication.getUserName());
        user.setEmail(authentication.getUserEmail());

        manager.setName(user.getName());
        manager.setEmail(user.getEmail());

        user.setPod(new Pod(podName));
        user.setLocation(new Location(locationName));

        repository.saveUser(user);
        stateLiveData.setValue(RegisterState.REGISTER_SUCCESS);
    }

    public String getUserName() {
        return authentication.getUserName();
    }
}