package br.com.zup.onboarding.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.User;

public class HomeViewModel extends ViewModel {
    private final LiveData<User> userLiveData;

    public HomeViewModel() {
        UserRepository repository = new UserRepository();
        repository.loadUser();
        userLiveData = repository.getUserLiveData();
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
