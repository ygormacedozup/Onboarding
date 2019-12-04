package br.com.zup.onboarding.presenter;

import java.util.List;

import br.com.zup.onboarding.contract.QuestionContract;
import br.com.zup.onboarding.model.entity.Question;

public class QuestionPresenter implements QuestionContract.Presenter, QuestionRepository.OnFinishedListener {
    private QuestionContract.View view;
    private QuestionRepository repository;

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
    public void onFinished(List<Question> questions) {
        view.setQuestions(questions);
    }

    @Override
    public void onFailure(Throwable throwable) {
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
