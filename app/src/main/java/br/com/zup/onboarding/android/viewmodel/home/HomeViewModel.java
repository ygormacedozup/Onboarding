package br.com.zup.onboarding.android.viewmodel.home;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.UserSessionManager;
import br.com.zup.onboarding.android.model.entity.User;

public class HomeViewModel extends ViewModel {
    private UserRepository repository;
    private GoogleAuthentication authentication;
    private UserSessionManager manager;
    private LiveData<User> userLiveData;
    private MutableLiveData<Uri> userPhotoLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();
    private LiveData<HomeState> stateLiveData;

    public HomeViewModel() {
        repository = UserRepository.getInstance();
        isLoadingLiveData.setValue(true);
    }

    public void setAuthentication(GoogleAuthentication authentication) {
        this.authentication = authentication;
    }

    public void setUserSessionManager(UserSessionManager manager) {
        this.manager = manager;
        verySessionSaved();
    }

    public LiveData<HomeState> getStateLiveData() {
        return stateLiveData;
    }

    private void verySessionSaved() {
        String email = manager.getEmail();
        if (email != null) {
            loadUser(email);
        }
    }

    public MutableLiveData<Boolean> getIsLoadingLiveData() {
        return isLoadingLiveData;
    }

    public void stopLoading() {
        isLoadingLiveData.setValue(false);
    }

    private void loadUser(String email) {
        GoogleSignInAccount account = authentication.getLastSignedInAccount();
        repository.getUserByEmail(email);
        userLiveData = repository.getUserLiveData();
        userPhotoLiveData.setValue(account.getPhotoUrl());

        stateLiveData = setState();
    }

    private LiveData<HomeState> setState() {
        return Transformations.map(userLiveData, input -> {
            int stepId = input.getStep().getId();
            boolean isCompleted = input.getStep().isCompleted();
            HomeState homeState = HomeState.NO_STEPS_COMPLETED;

            switch (stepId) {
                case 1:
                    homeState = isCompleted ? HomeState.FIRST_STEP_COMPLETED : HomeState.NO_STEPS_COMPLETED;
                    break;
                case 2:
                    homeState = isCompleted ? HomeState.SECOND_STEP_COMPLETED : HomeState.FIRST_STEP_COMPLETED;
                    break;
                case 3:
                    homeState = isCompleted ? HomeState.ALL_STEPS_COMPLETED : HomeState.SECOND_STEP_COMPLETED;
                    break;
            }

            return homeState;
        });
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<Uri> getUserPhotoLiveData() {
        return userPhotoLiveData;
    }
}
