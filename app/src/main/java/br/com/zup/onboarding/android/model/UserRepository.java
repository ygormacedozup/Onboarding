package br.com.zup.onboarding.android.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

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
    private MutableLiveData<List<Question>> questionListLiveData = new MutableLiveData<>();

    public UserRepository() {
        service = new RetrofitInitializer().getUserService();
        loadUser();
    }

    private void loadUser() {
        Disposable disposable = service.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadResponse, this::onError);
    }

    private void onLoadResponse(User response) {
        userLiveData.setValue(response);
        questionListLiveData.setValue(response.getStep().getQuestions());
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
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