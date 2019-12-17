package br.com.zup.onboarding.android.presenter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.User;

public class HomeViewModel extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<User> userLiveData;

    public HomeViewModel(Application application) {
        super(application);
        repository = new UserRepository();
        userLiveData = repository.getUser();
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
