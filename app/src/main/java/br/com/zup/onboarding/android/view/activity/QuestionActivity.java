package br.com.zup.onboarding.android.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.contract.QuestionContract;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.model.entity.User;
import br.com.zup.onboarding.android.presenter.QuestionPresenter;
import br.com.zup.onboarding.android.view.fragment.QuestionFragment;
import br.com.zup.onboarding.android.view.fragment.ResultFragment;

public class QuestionActivity extends AppCompatActivity implements ResultFragment.SendAndFinalizeListener,
        QuestionContract.View, QuestionFragment.OnQuestionsFinalizedListener {
    private QuestionContract.Presenter presenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Question> questions;
    private Fragment questionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        /*Intent intent = getIntent();
        questions = ((User) intent.getSerializableExtra("question")).getStep().getQuestions();*/

        //presenter = new QuestionPresenter(questions);

        presenter = new QuestionPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(this);
        presenter.loadQuestions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Override
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        presenter.onQuestionsLoaded();
    }

    @Override
    public void setFragments() {
        questionFragment = new QuestionFragment(questions, this);
    }

    @Override
    public void showFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_container, questionFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showResult(int correctAnswers) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_container, new ResultFragment(this));
        fragmentTransaction.commit();
    }

    @Override
    public void sendQuestionResult() {
        // Send result
    }

    @Override
    public void onFinalized(int correctAnswers) {
        presenter.onQuestionsFinalized(correctAnswers);
    }
}