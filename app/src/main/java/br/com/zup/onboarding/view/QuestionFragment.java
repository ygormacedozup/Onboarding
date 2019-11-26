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
    private Question question;
    private final String CORRECT_ANSWER_TAG = "CORRECT";
    private View rootView;
    TextView questionNumber;
    TextView questionName;
    Button btnFirstAnswer;
    Button btnSecondAnswer;
    Button btnThirdAnswer;
    Button btnFourthAnswer;

    public QuestionFragment(Question question) {
        this.question = question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_question, container, false);
        rootView = view;

        setLayout();
        setCorrectAnswerTag();

        return view;
    }

    private void setLayout() {
        questionNumber = rootView.findViewById(R.id.question_number);
        questionName = rootView.findViewById(R.id.question_name);

        btnFirstAnswer = rootView.findViewById(R.id.btn_first_answer);
        btnSecondAnswer = rootView.findViewById(R.id.btn_second_answer);
        btnThirdAnswer = rootView.findViewById(R.id.btn_third_answer);
        btnFourthAnswer = rootView.findViewById(R.id.btn_fourth_answer);
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

}