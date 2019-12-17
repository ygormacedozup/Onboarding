package br.com.zup.onboarding.android.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.model.entity.Question;

public class QuestionFragment extends Fragment {
    private View rootView;
    private TextView questionNumber;
    private TextView questionName;
    private List<Button> answerButtons;
    private List<Question> questions;
    private OnQuestionsFinalizedListener finalizedListener;
    private int currentQuestion = 0;
    private int correctAnswers = 0;

    public QuestionFragment(List<Question> questions, OnQuestionsFinalizedListener finalizedListener) {
        this.questions = questions;
        this.finalizedListener = finalizedListener;
        answerButtons = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_question, container, false);
        rootView = view;

        initializeTextViews();
        initializeButtons();
        showCurrentQuestion();

        setAnswersClickListeners();

        return view;
    }

    private void initializeTextViews() {
        questionNumber = rootView.findViewById(R.id.question_number);
        questionName = rootView.findViewById(R.id.question_name);
    }

    private void initializeButtons() {
        answerButtons.add((Button) rootView.findViewById(R.id.btn_first_answer));
        answerButtons.add((Button) rootView.findViewById(R.id.btn_second_answer));
        answerButtons.add((Button) rootView.findViewById(R.id.btn_third_answer));
        answerButtons.add((Button) rootView.findViewById(R.id.btn_fourth_answer));
    }

    private void setTextViews(Question question) {
        questionNumber.setText("Questão " + (currentQuestion + 1) + ":");
        questionName.setText(question.getDescription());
    }

    private void setButtons(Question question) {
        for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setText(question.getAlternatives().get(i).getDescription());
        }
    }

    private void setAnswersClickListeners() {
        /*Question question = questions.get(currentQuestion);

        for (Alternative alternative : question.getAlternatives()) {
            if (alternative.isCorrect()) {
                int correctAnswer = question.getAlternatives().indexOf(alternative);
                answerButtons.get(correctAnswer).setTag(CORRECT_ANSWER_TAG);
            }
        }

        for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setOnClickListener(getAnswerClickListener());
        }*/

        for (Button button : answerButtons) {
            button.setOnClickListener(getAnswerClickListener());
        }
    }

    private void removePreviousCorrectAnswer() {
        /*for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setTag(null);
            answerButtons.get(i).setOnClickListener(null);
        }*/
    }

    private void setAnswerBackground() {
        /*for (Button button : answerButtons) {
            int background = (button.getTag() != null) ? R.drawable.btn_correct_answer_selector
                    : R.drawable.btn_incorrect_answer_selector;
            button.setBackgroundResource(background);
        }*/

        for (Button button : answerButtons) {
            button.setBackgroundResource(R.drawable.btn_answer_selector);
        }
    }

    /*private View.OnClickListener getAnswerClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == rootView.findViewWithTag(CORRECT_ANSWER_TAG)) {
                    correctAnswers++;
                }

                if (isFinalized()) {
                    finalizedListener.onFinalized(correctAnswers);
                } else {
                    currentQuestion++;
                    showCurrentQuestion();
                }
            }
        };
    }*/

    private View.OnClickListener getAnswerClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinalized()) {
                    finalizedListener.onFinalized(correctAnswers);
                } else {
                    currentQuestion++;
                    showCurrentQuestion();
                }
            }
        };
    }

    private void showCurrentQuestion() {
        Question question = questions.get(currentQuestion);
        setTextViews(question);
        setButtons(question);

        /*removePreviousCorrectAnswer();
        setAnswersClickListeners();
        setAnswerBackground();*/
    }

    private boolean isFinalized() {
        return currentQuestion == (questions.size() - 1);
    }

    public interface OnQuestionsFinalizedListener {
        void onFinalized(int correctAnswers);
    }
}