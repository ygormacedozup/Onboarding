package br.com.zup.onboarding.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.zup.onboarding.R;
import br.com.zup.onboarding.model.Question;

public class QuestionFragment extends Fragment {
    private int questionIndex;
    private Question question;
    private final String CORRECT_ANSWER_TAG = "CORRECT";
    private View rootView;
    private ChangeFragmentListener listener;
    private TextView questionNumber;
    private TextView questionName;
    private Button btnFirstAnswer;
    private Button btnSecondAnswer;
    private Button btnThirdAnswer;
    private Button btnFourthAnswer;

    public QuestionFragment(int questionIndex, Question question, ChangeFragmentListener listener) {
        this.questionIndex = questionIndex;
        this.question = question;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_question, container, false);
        rootView = view;

        setLayout();
        setCorrectAnswerTag();
        setAnswerBackground();

        setAnswersClickListeners();

        return view;
    }

    private void initializeTextViews() {
        questionNumber = rootView.findViewById(R.id.question_number);
        questionName = rootView.findViewById(R.id.question_name);
    }

    private void initializeButtons() {
        btnFirstAnswer = rootView.findViewById(R.id.btn_first_answer);
        btnSecondAnswer = rootView.findViewById(R.id.btn_second_answer);
        btnThirdAnswer = rootView.findViewById(R.id.btn_third_answer);
        btnFourthAnswer = rootView.findViewById(R.id.btn_fourth_answer);
    }

    private void setTextViews() {
        questionNumber.setText("Questão " + (questionIndex + 1) + ":");
        questionName.setText(question.getQuestion());
    }

    private void setButtons() {
        btnFirstAnswer.setText(question.getAnswers().get(0));
        btnSecondAnswer.setText(question.getAnswers().get(1));
        btnThirdAnswer.setText(question.getAnswers().get(2));
        btnFourthAnswer.setText(question.getAnswers().get(3));
    }

    private void setLayout() {
        initializeTextViews();
        initializeButtons();
        setTextViews();
        setButtons();
    }

    private void setCorrectAnswerTag() {
        switch (question.getCorrectAnswer()) {
            case 1:
                btnFirstAnswer.setTag(CORRECT_ANSWER_TAG);
                break;
            case 2:
                btnSecondAnswer.setTag(CORRECT_ANSWER_TAG);
                break;
            case 3:
                btnThirdAnswer.setTag(CORRECT_ANSWER_TAG);
                break;
            case 4:
                btnFourthAnswer.setTag(CORRECT_ANSWER_TAG);
                break;
            default:
                break;
        }
    }

    private void setAnswerBackground() {
        int btnFirstAnswerBackground = (btnFirstAnswer.getTag() != null) ? R.drawable.btn_correct_answer_selector : R.drawable.btn_incorrect_answer_selector;
        btnFirstAnswer.setBackgroundResource(btnFirstAnswerBackground);

        int btnSecondAnswerBackground = (btnSecondAnswer.getTag() != null) ? R.drawable.btn_correct_answer_selector : R.drawable.btn_incorrect_answer_selector;
        btnSecondAnswer.setBackgroundResource(btnSecondAnswerBackground);

        int btnThirdAnswerBackground = (btnThirdAnswer.getTag() != null) ? R.drawable.btn_correct_answer_selector : R.drawable.btn_incorrect_answer_selector;
        btnThirdAnswer.setBackgroundResource(btnThirdAnswerBackground);

        int btnFourthAnswerBackground = (btnFourthAnswer.getTag() != null) ? R.drawable.btn_correct_answer_selector : R.drawable.btn_incorrect_answer_selector;
        btnFourthAnswer.setBackgroundResource(btnFourthAnswerBackground);
    }

    private void setAnswersClickListeners() {
        setCorrectAnswerClickListener();

        setFirstAnswerClickListener();
        setSecondAnswerClickListener();
        setThirdAnswerClickListener();
        setFourthAnswerClickListener();
    }

    private void setCorrectAnswerClickListener() {
        View correctAnswerView = rootView.findViewWithTag(CORRECT_ANSWER_TAG);

        removeAnswerDefaultClickListener(correctAnswerView);

        correctAnswerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.changeFragment(true);
            }
        });
    }

    private void setFirstAnswerClickListener() {
        if (!btnFirstAnswer.hasOnClickListeners()) {
            btnFirstAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.changeFragment(false);
                }
            });
        }
    }

    private void setSecondAnswerClickListener() {
        if (!btnSecondAnswer.hasOnClickListeners()) {
            btnSecondAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.changeFragment(false);
                }
            });
        }
    }

    private void setThirdAnswerClickListener() {
        if (!btnThirdAnswer.hasOnClickListeners()) {
            btnThirdAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.changeFragment(false);
                }
            });
        }
    }

    private void setFourthAnswerClickListener() {
        if (!btnFourthAnswer.hasOnClickListeners()) {
            btnFourthAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.changeFragment(false);
                }
            });
        }
    }

    private void removeAnswerDefaultClickListener(View correctAnswerView) {
        // Remove default click listener
        if (correctAnswerView.hasOnClickListeners()) {
            correctAnswerView.setOnClickListener(null);
        }
    }

    public interface ChangeFragmentListener {
        void changeFragment(boolean isCorrectAnswer);
    }
}