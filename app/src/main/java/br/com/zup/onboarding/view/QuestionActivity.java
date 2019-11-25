package br.com.zup.onboarding.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import br.com.zup.onboarding.R;

public class QuestionActivity extends AppCompatActivity {
    private Button btnFirstAnswer;
    private Button btnSecondAnswer;
    private Button btnThirdAnswer;
    private Button btnFourthAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnFirstAnswer = findViewById(R.id.btn_first_answer);
        btnSecondAnswer = findViewById(R.id.btn_second_answer);
        btnThirdAnswer = findViewById(R.id.btn_third_answer);
        btnFourthAnswer = findViewById(R.id.btn_fourth_answer);

        btnFirstAnswer.setBackgroundResource(R.drawable.btn_correct_answer_selector);
        btnSecondAnswer.setBackgroundResource(R.drawable.btn_incorrect_answer_selector);
        btnThirdAnswer.setBackgroundResource(R.drawable.btn_incorrect_answer_selector);
        btnFourthAnswer.setBackgroundResource(R.drawable.btn_incorrect_answer_selector);
    }
}
