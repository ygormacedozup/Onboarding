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
import br.com.zup.onboarding.android.R;

public class ResultFragment extends Fragment {
    private View view;
    private final String RESULT_NUMBER_SEPARATOR = "/";
    private int numberOfQuestions;
    private int correctAnswers;
    private SendAndFinalizeListener sendAndFinalizeListener;
    private Button btnSendAndFinalize;

    public ResultFragment(int numberOfQuestions, int correctAnswers, SendAndFinalizeListener sendAndFinalizeListener) {
        this.numberOfQuestions = numberOfQuestions;
        this.correctAnswers = correctAnswers;
        this.sendAndFinalizeListener = sendAndFinalizeListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_result, container, false);
        this.view = view;

        TextView resultNumber = view.findViewById(R.id.result_number);
        resultNumber.setText(correctAnswers + RESULT_NUMBER_SEPARATOR + numberOfQuestions);

        setSendAndFinalizeButton();

        return view;
    }

    private void setSendAndFinalizeButton() {
        btnSendAndFinalize = view.findViewById(R.id.send_and_finalize);
        setSendAndFinalizeClickListener();
    }

    private void setSendAndFinalizeClickListener() {
        btnSendAndFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAndFinalizeListener.sendQuestionResult();
            }
        });
    }

    public interface SendAndFinalizeListener {
        void sendQuestionResult();
    }
}
