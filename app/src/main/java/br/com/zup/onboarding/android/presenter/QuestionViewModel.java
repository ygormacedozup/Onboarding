package br.com.zup.onboarding.android.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import br.com.zup.onboarding.android.model.UserRepository;
import br.com.zup.onboarding.android.model.entity.Question;

public class QuestionViewModel extends ViewModel {
    private LiveData<List<Question>> questionLiveData;

    public QuestionViewModel() {
        UserRepository repository = new UserRepository();
        questionLiveData = repository.getQuestionsLiveData();
    }

    public LiveData<List<Question>> getQuestionLiveData() {
        return questionLiveData;
    }
}
