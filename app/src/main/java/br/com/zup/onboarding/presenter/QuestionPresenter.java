package br.com.zup.onboarding.presenter;

import android.util.Log;

import java.util.List;

import br.com.zup.onboarding.QuestionService;
import br.com.zup.onboarding.RetrofitInitializer;
import br.com.zup.onboarding.contract.QuestionContract;
import br.com.zup.onboarding.model.entity.Question;
import br.com.zup.onboarding.model.entity.Step;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionPresenter implements QuestionContract.Presenter {
    private QuestionContract.View view;
    private QuestionService questionService;

    public QuestionPresenter() {
        questionService = new RetrofitInitializer().getQuestionService();
    }

    @Override
    public void start(QuestionContract.View view) {
        this.view = view;
    }

    @Override
    public void stop() {
        view = null;
    }

    @Override
    public void loadQuestions() {
        Call<Step> call = new RetrofitInitializer().getQuestionService().getStep();
        call.enqueue(new Callback<Step>() {
            @Override
            public void onResponse(Call<Step> call, Response<Step> response) {
                List<Question> questions = response.body().getQuestions();
                Log.e("Questions", questions.toString());
                view.setQuestions(questions);
            }

            @Override
            public void onFailure(Call<Step> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onQuestionsLoaded() {
        view.setFragments();
        view.showFragment();
    }

    @Override
    public void changeQuestion() {
        view.showNextQuestion();
    }

    @Override
    public void resetQuestions() {
        view.showResetQuestions();
    }
}
