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

public class QuestionActivity extends AppCompatActivity {
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
        showQuestion();
    }

    private void setQuestions() {
        questions = new ArrayList<>();
        fragments = new ArrayList<>();

        /////////////Test////////////
        fragments.add(new ResultFragment(3, 2));
        /////////////////////////////

        List<String> answers = new ArrayList<>();
        answers.add("Resposta 1");
        answers.add("Resposta 2");
        answers.add("Resposta 3");

        questions.add(new Question("Questão 1", answers, 1));
        questions.add(new Question("Questão 2", answers, 2));
        questions.add(new Question("Questão 3", answers, 3));
    }

    private void addFragments() {
        for (Question question : questions) {
            fragments.add(new QuestionFragment(question));
        }
    }

    private void showQuestion() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_container, fragments.get(currentFragment));
        fragmentTransaction.commit();
    }
}