package br.com.zup.onboarding.android.viewmodel.home;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.User;

public class HomeViewModel extends ViewModel {
    private UserRepository repository;
    private GoogleAuthentication authentication;
    private LiveData<User> userLiveData;
    private MutableLiveData<Uri> userPhotoLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();

    public HomeViewModel() {
        repository = UserRepository.getInstance();
        isLoadingLiveData.setValue(true);
    }

    public void setAuthentication(GoogleAuthentication authentication) {
        this.authentication = authentication;
        loadUser();
    }

    public MutableLiveData<Boolean> getIsLoadingLiveData() {
        return isLoadingLiveData;
    }

    public void stopLoading() {
        isLoadingLiveData.setValue(false);
    }

    private void loadUser() {
        GoogleSignInAccount account = authentication.getLastSignedInAccount();
        repository.getUserByEmail(account.getEmail());
        userLiveData = repository.getUserLiveData();
        userPhotoLiveData.setValue(account.getPhotoUrl());
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<Uri> getUserPhotoLiveData() {
        return userPhotoLiveData;
    }
}
