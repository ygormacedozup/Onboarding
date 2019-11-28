package br.com.zup.onboarding.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.onboarding.R;
import br.com.zup.onboarding.contract.QuestionContract;
import br.com.zup.onboarding.model.Question;
import br.com.zup.onboarding.presenter.QuestionPresenter;
import br.com.zup.onboarding.view.fragments.QuestionFragment;
import br.com.zup.onboarding.view.fragments.ResultFragment;

public class QuestionActivity extends AppCompatActivity implements QuestionFragment.ChangeFragmentListener,
        ResultFragment.TryAgainListener, QuestionContract.View {
    private QuestionContract.Presenter presenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Question> questions;
    private List<Fragment> fragments = new ArrayList<>();
    private int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        presenter = new QuestionPresenter();
        questions = presenter.loadQuestions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(this);
        questions = presenter.loadQuestions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stop();
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
        fragmentTransaction.replace(R.id.question_container, new ResultFragment(3, 2, this));
        fragmentTransaction.commit();
    }

    @Override
    public void showResetQuestions() {
        currentFragment = 0;
        showFragment();
    }

    @Override
    public void changeFragment() {
        presenter.changeQuestion();
    }

    @Override
    public void resetQuestions() {
        presenter.resetQuestions();
    }
}