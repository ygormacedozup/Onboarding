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

public class ResultFragment extends Fragment {
    private View view;
    private final String RESULT_NUMBER_SEPARATOR = "/";
    private int numberOfQuestions;
    private int correctAnswers;
    private TryAgainListener listener;
    private Button btnTryAgain;

    public ResultFragment(int numberOfQuestions, int correctAnswers, TryAgainListener listener) {
        this.numberOfQuestions = numberOfQuestions;
        this.correctAnswers = correctAnswers;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_result, null, false);
        this.view = view;

        TextView resultNumber = view.findViewById(R.id.result_number);
        resultNumber.setText(correctAnswers + RESULT_NUMBER_SEPARATOR + numberOfQuestions);

        setTryAgainButton();

        return view;
    }

    private void setTryAgainButton() {
        btnTryAgain = view.findViewById(R.id.try_again);
        setTryAgainClickListener();
    }

    private void setTryAgainClickListener() {
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.resetQuestions();
            }
        });
    }

    public interface TryAgainListener {
        void resetQuestions();
    }
}
