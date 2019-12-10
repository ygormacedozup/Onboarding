package br.com.zup.onboarding.android.model.repository;

import java.util.List;

import br.com.zup.onboarding.android.QuestionService;
import br.com.zup.onboarding.android.RetrofitInitializer;
import br.com.zup.onboarding.android.contract.QuestionContract;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.model.entity.StepSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionRepository implements QuestionContract.Repository {
    private QuestionService questionService;

    public QuestionRepository() {
        questionService = new RetrofitInitializer().getQuestionService();
    }

    @Override
    public void getQuestions(final QuestionContract.OnRequestFinishedListener listener) {
        Call<StepSet> call = questionService.getStepSet();
        call.enqueue(new Callback<StepSet>() {
            @Override
            public void onResponse(Call<StepSet> call, Response<StepSet> response) {
                if (response.isSuccessful()) {
                    List<Question> questions = response.body().getSteps().get(0).getQuestions();
                    listener.onRequestFinished(questions);
                }
            }

            @Override
            public void onFailure(Call<StepSet> call, Throwable t) {
                listener.onRequestFailed(t);
            }
        });
    }
}