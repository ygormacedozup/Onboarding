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
import br.com.zup.onboarding.android.model.entity.ZupperExists;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final UserService service;
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Question>> questionListLiveData = new MutableLiveData<>();
    private final MutableLiveData<FinishedStep> finishedStepLiveData = new MutableLiveData<>();
    private final MutableLiveData<ZupperExists> zupperExistsLiveData = new MutableLiveData<>();

    private UserRepository() {
        service = new RetrofitInitializer().getUserService();
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }

    public void checkUserExists(ZupperExists zupperExists) {
        Disposable disposable = service.zupperExists(zupperExists)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onZupperExistsResponse, this::onError);
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

    private void onZupperExistsResponse(Response<ZupperExists> response) {
        zupperExistsLiveData.setValue(response.body());
    }

    private void onSaveResponse(User response) {
        Log.e("User received", response.toString());
    }

    private void onGetByEmailResponse(Response<User> response) {
        userLiveData.setValue(response.body());
        questionListLiveData.setValue(response.body().getStep().getQuestions());
    }

    private void onSaveAlternativeResponse(User response) {
        Log.e("Alternative saved", response.toString());
    }

    private void onFinishStepResponse(FinishedStep response) {
        finishedStepLiveData.setValue(response);
    }

    private void onError(Throwable throwable) {
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
        return finishedStepLiveData;
    }

    public MutableLiveData<ZupperExists> getZupperExistsLiveData() {
        return zupperExistsLiveData;
    }
}