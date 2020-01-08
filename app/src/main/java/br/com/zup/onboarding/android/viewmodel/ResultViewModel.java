package br.com.zup.onboarding.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.UserSessionManager;
import br.com.zup.onboarding.android.model.entity.FinishedStep;
import br.com.zup.onboarding.android.model.entity.User;

public class ResultViewModel extends ViewModel {
    private User user;
    private UserRepository repository;
    private UserSessionManager manager;
    private LiveData<FinishedStep> finishedStepLiveData;

    public ResultViewModel() {
        repository = UserRepository.getInstance();
        MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();
        isLoadingLiveData.setValue(true);
    }

    public void setUserSessionManager(UserSessionManager manager) {
        this.manager = manager;
        loadUser();
        loadFinishedStep();
    }

    private void loadUser() {
        repository.getUserByEmail(manager.getEmail());
        user = repository.getUserLiveData().getValue();
    }

    private void loadFinishedStep() {
        repository.finishStep(user.getId());
        finishedStepLiveData = repository.getFinishedStepLiveData();
    }

    public LiveData<FinishedStep> getFinishedStepLiveData() {
        return finishedStepLiveData;
    }
}
