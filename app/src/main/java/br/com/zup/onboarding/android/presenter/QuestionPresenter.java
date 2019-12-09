package br.com.zup.onboarding.android.presenter;

import java.util.List;

import br.com.zup.onboarding.android.contract.QuestionContract;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.model.repository.QuestionRepository;

public class QuestionPresenter implements QuestionContract.Presenter, QuestionContract.OnRequestFinishedListener {
    private QuestionContract.View view;
    private QuestionContract.Repository repository;

    public QuestionPresenter() {
        repository = new QuestionRepository();
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
        repository.getQuestions(this);
    }

    @Override
    public void onRequestFinished(List<Question> questions) {
        view.setQuestions(questions);
    }

    @Override
    public void onRequestFailed(Throwable throwable) {
        throwable.printStackTrace();
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
