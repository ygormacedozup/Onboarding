package br.com.zup.onboarding.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.zup.onboarding.R;

public class ResultFragment extends Fragment {
    private final String RESULT_NUMBER_SEPARATOR = "/";
    private int numberOfQuestions;
    private int correctAnswers;

    public ResultFragment(int numberOfQuestions, int correctAnswers) {
        this.numberOfQuestions = numberOfQuestions;
        this.correctAnswers = correctAnswers;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_result, null, false);

        TextView resultNumber = view.findViewById(R.id.result_number);
        resultNumber.setText(correctAnswers + RESULT_NUMBER_SEPARATOR + numberOfQuestions);

        return view;
    }
}
