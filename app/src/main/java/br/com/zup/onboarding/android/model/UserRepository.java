package br.com.zup.onboarding.android.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.Objects;

import br.com.zup.onboarding.android.RetrofitInitializer;
import br.com.zup.onboarding.android.UserService;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.model.entity.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    private final UserService service;
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Question>> questionListLiveData = new MutableLiveData<>();

    public UserRepository() {
        service = new RetrofitInitializer().getUserService();
    }

    public void loadUser() {
        Disposable disposable = service.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadResponse, this::onError);
    }

    public void saveUser(User user) {
        Disposable disposable = service.saveUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSaveResponse, this::onError);
    }

    public void getUserByEmail(String email) {
        Disposable disposable = service.getUserByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetByEmailResponse, this::onError);
    }

    private void onLoadResponse(User response) {
        userLiveData.setValue(response);
        questionListLiveData.setValue(response.getStep().getQuestions());
    }

    private void onSaveResponse(User response) {
        Log.e("User received", response.toString());
    }

    private void onGetByEmailResponse(User response) {
        Log.e("User with email", response.toString());
        userLiveData.setValue(response);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        Log.e("Throwable", Objects.requireNonNull(throwable.getMessage()));
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<Question> getQuestionLiveData(int index) {
        return Transformations.map(questionListLiveData, questions -> questions.get(index));
    }

    public LiveData<Integer> getMaxQuestionsLiveData() {
        return Transformations.map(questionListLiveData, List::size);
    }
}