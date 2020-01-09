package br.com.zup.onboarding.android.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.Objects;

import br.com.zup.onboarding.android.RetrofitInitializer;
import br.com.zup.onboarding.android.UserService;
import br.com.zup.onboarding.android.model.entity.Alternative;
import br.com.zup.onboarding.android.model.entity.FinishedStep;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.model.entity.User;
import br.com.zup.onboarding.android.model.entity.UserAlternative;
import br.com.zup.onboarding.android.model.entity.UserAlternativeRequest;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final UserService service;
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Question>> questionListLiveData = new MutableLiveData<>();
    private final MutableLiveData<FinishedStep> finishedStepMutableLiveData = new MutableLiveData<>();

    private UserRepository() {
        service = new RetrofitInitializer().getUserService();
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
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

    public void saveAlternative(int alternativeId, User user) {
        UserAlternativeRequest userAlternativeRequest = new UserAlternativeRequest(user.getId());
        UserAlternative userAlternative = new UserAlternative(userAlternativeRequest, new Alternative(alternativeId));
        Disposable disposable = service.saveAlternative(userAlternative)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSaveAlternativeResponse, this::onError);
    }

    public void finishStep(int id) {
        User user = new User(id);
        Disposable disposable = service.finishStep(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFinishStepResponse, this::onError);
    }

    private void onSaveResponse(User response) {
        Log.e("User received", response.toString());
    }

    private void onGetByEmailResponse(User response) {
        userLiveData.setValue(response);
        questionListLiveData.setValue(response.getStep().getQuestions());
    }

    private void onSaveAlternativeResponse(User response) {
        Log.e("Alternative saved", response.toString());
    }

    private void onFinishStepResponse(FinishedStep response) {
        Log.e("Step finished", response.toString());
        finishedStepMutableLiveData.setValue(response);
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

    public LiveData<FinishedStep> getFinishedStepLiveData() {
        return finishedStepMutableLiveData;
    }
}