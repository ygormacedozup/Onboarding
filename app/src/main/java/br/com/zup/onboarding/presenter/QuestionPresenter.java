package br.com.zup.onboarding.presenter;

import br.com.zup.onboarding.contract.QuestionContract;

public class QuestionPresenter implements QuestionContract.Presenter {
    private QuestionContract.View view;

    public QuestionPresenter() {}

    @Override
    public void start(QuestionContract.View view) {
        this.view = view;
        this.view.setFragments();
    }

    @Override
    public void stop() {
        view = null;
    }

    @Override
    public void changeQuestion() {
        // Show question
    }
}
