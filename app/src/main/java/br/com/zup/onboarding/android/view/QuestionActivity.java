package br.com.zup.onboarding.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    private ProgressBar loadingBar;
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
        loadingBar = findViewById(R.id.loading_progress_bar);
    }

    private void initializeButtons() {
        answerButtons.add(findViewById(R.id.btn_first_answer));
        answerButtons.add(findViewById(R.id.btn_second_answer));
        answerButtons.add(findViewById(R.id.btn_third_answer));
        answerButtons.add(findViewById(R.id.btn_fourth_answer));
    }

    private void setLoading(boolean isLoading) {
        int loadingBarVisibility = isLoading ? View.VISIBLE : View.INVISIBLE;
        int informationVisibility = isLoading ? View.INVISIBLE : View.VISIBLE;

        loadingBar.setVisibility(loadingBarVisibility);

        questionNumber.setVisibility(informationVisibility);
        questionName.setVisibility(informationVisibility);
        for (Button button : answerButtons) button.setVisibility(informationVisibility);
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
        viewModel.getMaxQuestionsLiveData().observe(this, max -> viewModel.setMaxQuestions(max));
        viewModel.getQuestionLiveData().observe(this, this::showQuestion);
        viewModel.getQuestionNumberLiveData().observe(this, this::showQuestionNumber);
        viewModel.getIsLoadingLiveData().observe(this, this::setLoading);
    }

    private void showQuestionNumber(int number) {
        String template = "Questão " + number + ":";
        questionNumber.setText(template);
    }

    private void showQuestion(Question question) {
        if (question != null) {
            viewModel.stopLoading();
            questionName.setText(question.getDescription());

            for (int i = 0; i < answerButtons.size(); i++) {
                answerButtons.get(i).setText(question.getAlternatives().get(i).getDescription());
            }
        }
    }

    private void navigateToResult() {
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        startActivity(intent);
    }
}