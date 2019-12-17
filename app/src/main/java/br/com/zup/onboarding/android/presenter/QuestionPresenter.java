package br.com.zup.onboarding.android.presenter;

import java.util.List;

import br.com.zup.onboarding.android.contract.QuestionContract;
import br.com.zup.onboarding.android.contract.QuestionContract.Presenter;
import br.com.zup.onboarding.android.model.entity.Question;
//import br.com.zup.onboarding.android.model.repository.QuestionRepository;
import br.com.zup.onboarding.android.model.UserRepository;

public class QuestionPresenter implements Presenter/*, QuestionContract.OnRequestFinishedListener */{
    private QuestionContract.View view;
    //private QuestionContract.Repository repository;
    private List<Question> questions;

    private UserRepository repository;

    /*public QuestionPresenter(List<Question> questions) {
        repository = new QuestionRepository();
        this.questions = questions;
    }*/

    public QuestionPresenter() {
        repository = new UserRepository();
    }

    @Override
    public void start(QuestionContract.View view) {
        this.view = view;

        view.setFragments();
        view.setQuestions(questions);
        view.showFragment();
    }

    @Override
    public void stop() {
        view = null;
    }

    @Override
    public void loadQuestions() {
        //repository.getQuestions(this);
        /*questions = repository.getQuestions();
        view.setQuestions(questions);*/
    }

    /*@Override
    public void onRequestFinished(List<Question> questions) {
        //view.setQuestions(questions);
        //view.setQuestions(questions);
    }

    @Override
    public void onRequestFailed(Throwable throwable) {
        throwable.printStackTrace();
    }*/

    @Override
    public void onQuestionsLoaded() {
        //view.setFragments();
        //view.showFragment();
    }

    @Override
    public void onQuestionsFinalized(int correctAnswers) {
        view.showResult(correctAnswers);
    }
}
