package br.com.zup.onboarding.android.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.onboarding.android.contract.QuestionContract;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.presenter.QuestionPresenter;
import br.com.zup.onboarding.android.view.fragment.QuestionFragment;
import br.com.zup.onboarding.android.view.fragment.ResultFragment;
import br.com.zup.onboarding.android.R;

public class QuestionActivity extends AppCompatActivity implements QuestionFragment.ChangeFragmentListener,
        ResultFragment.TryAgainListener, ResultFragment.SendAndFinalizeListener, QuestionContract.View {
    private QuestionContract.Presenter presenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Question> questions;
    private List<Fragment> fragments = new ArrayList<>();
    private int currentFragment = 0;
    private int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

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
        for (int i = 0; i < questions.size(); i++) {
            fragments.add(new QuestionFragment(i, questions.get(i), this));
        }
    }

    @Override
    public void showFragment() {
        if (currentFragment < fragments.size()) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.question_container, fragments.get(currentFragment));
            fragmentTransaction.commit();
        } else {
            showResult();
        }
    }

    @Override
    public void showNextQuestion() {
        currentFragment++;
        showFragment();
    }

    @Override
    public void showResult() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // Test
        fragmentTransaction.replace(R.id.question_container, new ResultFragment(questions.size(), correctAnswers, this, this));
        fragmentTransaction.commit();
    }

    @Override
    public void showResetQuestions() {
        correctAnswers = 0;
        currentFragment = 0;

        showFragment();
    }

    @Override
    public void changeFragment(boolean isCorrectAnswer) {
        if (isCorrectAnswer) {
            correctAnswers++;
        }

        presenter.changeQuestion();
    }

    @Override
    public void resetQuestions() {
        presenter.resetQuestions();
    }

    @Override
    public void sendQuestionResult() {
        // Send result
    }
}