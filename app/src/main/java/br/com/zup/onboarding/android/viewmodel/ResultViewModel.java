package br.com.zup.onboarding.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.User;

public class ResultViewModel extends ViewModel {
    private UserRepository repository;
    private GoogleAuthentication authentication;
    private LiveData<User> userLiveData;

    public ResultViewModel() {
        repository = UserRepository.getInstance();
        MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();
        isLoadingLiveData.setValue(true);
    }

    public void setAuthentication(GoogleAuthentication authentication) {
        this.authentication = authentication;
        loadUser();
    }

    private void loadUser() {
        GoogleSignInAccount account = authentication.getLastSignedInAccount();
        repository.getUserByEmail(account.getEmail());
        userLiveData = repository.getUserLiveData();
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
