package br.com.zup.onboarding.android.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.zup.onboarding.android.RetrofitInitializer;
import br.com.zup.onboarding.android.UserService;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.model.entity.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    private UserService service;
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public UserRepository() {
        service = new RetrofitInitializer().getUserService();
        loadUser();
    }

    private void loadUser() {
        Disposable disposable = service.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onResponse, this::onError);
    }

    private void onResponse(User response) {
        userLiveData.setValue(response);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }


    public LiveData<User> getUser() {
        return userLiveData;
    }

    public List<Question> getQuestions() {
        return userLiveData.getValue().getStep().getQuestions();
    }
}
