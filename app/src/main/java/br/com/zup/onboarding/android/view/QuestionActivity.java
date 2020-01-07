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
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.model.entity.Question;
import br.com.zup.onboarding.android.viewmodel.QuestionViewModel;

public class QuestionActivity extends AppCompatActivity {
    private QuestionViewModel viewModel;
    private Question question;
    private TextView questionNumber, questionName;
    private final List<Button> answerButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        initializeViews();
        initializeButtons();
        setViewModel();
        setClickListeners();
        setFonts();
    }

    private void setFonts() {
        questionName.setTypeface(Utils.getFont(this));
        questionNumber.setTypeface(Utils.getFont(this));
        for (Button button : answerButtons) {
            button.setTypeface(Utils.getFont(this));
        }
    }

    private void initializeViews() {
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
        for (int i = 0; i < answerButtons.size(); i++) {
            final int index = i;
            answerButtons.get(i).setOnClickListener(v -> {
                if (viewModel.isFinalized()) {
                    int alternativeId = question.getAlternatives().get(index).getId();
                    viewModel.saveAlternative(alternativeId);


                    viewModel.finishStep();


                    QuestionActivity.this.navigateToResult();
                } else {
                    int alternativeId = question.getAlternatives().get(index).getId();
                    viewModel.saveAlternative(alternativeId);
                    viewModel.updateQuestion();
                    viewModel.getQuestionLiveData().removeObservers(QuestionActivity.this);
                    QuestionActivity.this.setViewModel();
                }
            });
        }
    }

    private void setViewModel() {
        viewModel.getMaxQuestionsLiveData().observe(this, max -> viewModel.setMaxQuestions(max));
        viewModel.getQuestionLiveData().observe(this, this::showQuestion);
        viewModel.getQuestionNumberLiveData().observe(this, this::showQuestionNumber);
    }

    private void showQuestionNumber(int number) {
        String template = "Questão " + number + ":";
        questionNumber.setText(template);
    }

    private void showQuestion(Question questionResponse) {
        this.question = questionResponse;
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