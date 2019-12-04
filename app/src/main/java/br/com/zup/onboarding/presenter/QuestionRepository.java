package br.com.zup.onboarding.presenter;

import java.util.List;

import br.com.zup.onboarding.QuestionService;
import br.com.zup.onboarding.RetrofitInitializer;
import br.com.zup.onboarding.model.entity.Question;
import br.com.zup.onboarding.model.entity.StepSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionRepository {
    private QuestionService questionService;

    public QuestionRepository() {
        questionService = new RetrofitInitializer().getQuestionService();
    }

    public void getQuestions(final OnFinishedListener listener) {
        Call<StepSet> call = questionService.getStepSet();
        call.enqueue(new Callback<StepSet>() {
            @Override
            public void onResponse(Call<StepSet> call, Response<StepSet> response) {
                if (response.isSuccessful()) {
                    List<Question> questions = response.body().getSteps().get(0).getQuestions();
                    listener.onFinished(questions);
                }
            }

            @Override
            public void onFailure(Call<StepSet> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    interface OnFinishedListener {
        void onFinished(List<Question> questions);
        void onFailure(Throwable throwable);
    }
}