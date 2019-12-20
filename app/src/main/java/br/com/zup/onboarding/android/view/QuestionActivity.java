package br.com.zup.onboarding.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.viewmodel.QuestionViewModel;

public class QuestionActivity extends AppCompatActivity {
    private QuestionViewModel viewModel;
    private TextView questionNumber;
    private TextView questionName;
    private List<Button> answerButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        initializeTextViews();
        initializeButtons();
        setViewModel();
        setClickListeners();
    }

    private void initializeTextViews() {
        questionNumber = findViewById(R.id.question_number);
        questionName = findViewById(R.id.question_name);
    }

    private void initializeButtons() {
        answerButtons.add(findViewById(R.id.btn_first_answer));
        answerButtons.add(findViewById(R.id.btn_second_answer));
        answerButtons.add(findViewById(R.id.btn_third_answer));
        answerButtons.add(findViewById(R.id.btn_fourth_answer));
    }

    private void setClickListeners() {
        for (Button button : answerButtons) {
            button.setOnClickListener(v -> {
                if (viewModel.isFinalized()) {
                    navigateToResult();
                } else {
                    viewModel.updateQuestion();
                    viewModel.getQuestionLiveData().removeObservers(this);
                    setViewModel();
                }
            });
        }
    }

    private void setViewModel() {
        viewModel.getQuestionLiveData().observe(this, this::showQuestion);
        viewModel.getQuestionNumberLiveData().observe(this, this::showQuestionNumber);


        viewModel.getMaxQuestionsLiveData().observe(this, integer -> viewModel.setMaxQuestions(integer));
    }

    private void showQuestionNumber(int number) {
        String template = "Questão " + number + ":";
        questionNumber.setText(template);
    }

    private void showQuestion(Question question) {
        questionName.setText(question.getDescription());

        for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setText(question.getAlternatives().get(i).getDescription());
        }
    }

    private void navigateToResult() {
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        startActivity(intent);
    }
}