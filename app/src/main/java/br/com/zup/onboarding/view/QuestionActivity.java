package br.com.zup.onboarding.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.onboarding.R;
import br.com.zup.onboarding.model.Question;

public class QuestionActivity extends AppCompatActivity implements QuestionFragment.ChangeFragmentListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Question> questions;
    private List<Fragment> fragments;
    private int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setQuestions();
        addFragments();
        showQuestion(currentFragment);
    }

    private void setQuestions() {
        questions = new ArrayList<>();
        fragments = new ArrayList<>();

        /* fragments.add(new ResultFragment(3, 2)); */

        List<String> answers = new ArrayList<>();
        answers.add("Feijão");
        answers.add("Arroz");
        answers.add("Frango");
        answers.add("Ovo");

        questions.add(new Question("Qual time você torce?", answers, 1));
        questions.add(new Question("Quem é Jesus?", answers, 2));
        questions.add(new Question("Por que o céu é azul?", answers, 3));
    }

    private void addFragments() {
        for (int i = 0; i < questions.size(); i++) {
            fragments.add(new QuestionFragment(i, questions.get(i), this));
        }
    }

    private void showQuestion(int index) {
        if (currentFragment < fragments.size()) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.question_container, fragments.get(index));
            fragmentTransaction.commit();
        } else {
            showResult();
        }
    }

    private void showResult() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // Test
        fragmentTransaction.replace(R.id.question_container, new ResultFragment(3, 2));
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment() {
        currentFragment++;
        showQuestion(currentFragment);
    }
}