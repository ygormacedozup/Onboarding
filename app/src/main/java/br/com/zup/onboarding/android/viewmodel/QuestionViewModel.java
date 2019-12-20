package br.com.zup.onboarding.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.Question;

public class QuestionViewModel extends ViewModel {
    private UserRepository repository;
    private LiveData<Question> questionLiveData;
    private MutableLiveData<Integer> questionNumberLiveData = new MutableLiveData<>();
    private int currentQuestion = 0;
    private int maxQuesitons = 0;
    private LiveData<Integer> maxQuestionsLiveData;
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();

    public QuestionViewModel() {
        isLoadingLiveData.setValue(true);
        repository = new UserRepository();
        questionNumberLiveData.setValue(currentQuestion + 1);
        maxQuestionsLiveData = repository.getMaxQuestionsLiveData();
    }

    public MutableLiveData<Boolean> getIsLoadingLiveData() {
        return isLoadingLiveData;
    }

    public void stopLoading() {
        isLoadingLiveData.setValue(false);
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

    ;
}