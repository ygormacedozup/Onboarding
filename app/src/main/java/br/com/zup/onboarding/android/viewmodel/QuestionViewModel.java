package br.com.zup.onboarding.android.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.model.entity.User;

public class QuestionViewModel extends ViewModel {
    private final UserRepository repository;
    private User user;
    private LiveData<Question> questionLiveData;
    private final MutableLiveData<Integer> questionNumberLiveData = new MutableLiveData<>();
    private int currentQuestion = 0;
    private int maxQuesitons = 0;
    private final LiveData<Integer> maxQuestionsLiveData;

    public QuestionViewModel() {
        repository = UserRepository.getInstance();
        user = repository.getUserLiveData().getValue();

        questionNumberLiveData.setValue(currentQuestion + 1);
        maxQuestionsLiveData = repository.getMaxQuestionsLiveData();
    }

    public LiveData<Integer> getMaxQuestionsLiveData() {
        return maxQuestionsLiveData;
    }

    public LiveData<Question> getQuestionLiveData() {
        questionLiveData = repository.getQuestionLiveData(currentQuestion);
        return questionLiveData;
    }

    public LiveData<Integer> getQuestionNumberLiveData() {
        return questionNumberLiveData;
    }

    private void setCurrentQuestion() {
        questionNumberLiveData.setValue(currentQuestion + 1);
        questionLiveData = repository.getQuestionLiveData(currentQuestion);
    }

    public void saveAlternative(int alternativeId) {
        Log.e("Alternative id", String.valueOf(alternativeId));
        repository.saveAlternative(alternativeId, user);
    }
  
    public void updateQuestion() {
        currentQuestion++;
        setCurrentQuestion();
    }

    public void setMaxQuestions(int max) {
        maxQuesitons = max;
    }

    public boolean isFinalized() {
        return currentQuestion == maxQuesitons - 1;
    }
}