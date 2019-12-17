package br.com.zup.onboarding.android.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.presenter.QuestionViewModel;
import br.com.zup.onboarding.android.view.fragment.QuestionFragment;
import br.com.zup.onboarding.android.view.fragment.ResultFragment;

public class QuestionActivity extends AppCompatActivity implements ResultFragment.SendAndFinalizeListener,
        QuestionFragment.OnQuestionsFinalizedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment questionFragment;
    private Fragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        QuestionViewModel viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        viewModel.getQuestionLiveData().observe(this, questions -> {
            setFragments(questions);
            showQuestions();
        });
    }

    private void setFragments(List<Question> questions) {
        questionFragment = new QuestionFragment(questions, this);
        resultFragment = new ResultFragment(this);
    }

    private void showQuestions() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_container, questionFragment);
        fragmentTransaction.commit();
    }

    private void showResult() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_container, resultFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void sendQuestionResult() {
        // Send result
        Toast.makeText(this, "Resultado enviado!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinalized(int correctAnswers) {
        showResult();
    }
}