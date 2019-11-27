package br.com.zup.onboarding.view;

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
    TextView questionNumber;
    TextView questionName;
    Button btnFirstAnswer;
    Button btnSecondAnswer;
    Button btnThirdAnswer;
    Button btnFourthAnswer;

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
        setCorrectAnswerClickListener();

        return view;
    }

    private void setLayout() {
        questionNumber = rootView.findViewById(R.id.question_number);
        questionName = rootView.findViewById(R.id.question_name);

        btnFirstAnswer = rootView.findViewById(R.id.btn_first_answer);
        btnSecondAnswer = rootView.findViewById(R.id.btn_second_answer);
        btnThirdAnswer = rootView.findViewById(R.id.btn_third_answer);
        btnFourthAnswer = rootView.findViewById(R.id.btn_fourth_answer);

        questionNumber.setText("Questão " + questionIndex + ":");
        questionName.setText(question.getQuestion());

        btnFirstAnswer.setText(question.getAnswers().get(0));
        btnSecondAnswer.setText(question.getAnswers().get(1));
        btnThirdAnswer.setText(question.getAnswers().get(2));
        btnFourthAnswer.setText(question.getAnswers().get(3));
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

    private void setCorrectAnswerClickListener() {
        rootView.findViewWithTag(CORRECT_ANSWER_TAG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.changeFragment();
            }
        });
    }

    interface ChangeFragmentListener {
        void changeFragment();
    }
}