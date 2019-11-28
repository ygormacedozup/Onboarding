package br.com.zup.onboarding.presenter;

import java.util.List;

import br.com.zup.onboarding.QuestionRepository;
import br.com.zup.onboarding.contract.QuestionContract;
import br.com.zup.onboarding.model.Question;

public class QuestionPresenter implements QuestionContract.Presenter {
    private QuestionContract.Repository repository = new QuestionRepository();
    private QuestionContract.View view;

    public QuestionPresenter() {
    }

    @Override
    public void start(QuestionContract.View view) {
        this.view = view;
        this.view.setFragments();
        this.view.showFragment();
    }

    @Override
    public void stop() {
        view = null;
    }

    @Override
    public List<Question> loadQuestions() {
        return repository.getQuestions();
    }

    @Override
    public void changeQuestion() {
        view.showNextQuestion();
    }
}
