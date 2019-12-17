package br.com.zup.onboarding.android.model;

import android.util.Log;

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
    private MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();

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
        questionsLiveData.setValue(response.getStep().getQuestions());
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }


    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<List<Question>> getQuestionsLiveData() {
        return questionsLiveData;
    }
}
