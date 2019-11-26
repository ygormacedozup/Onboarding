package br.com.zup.onboarding.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.zup.onboarding.R;

public class ResultFragment extends Fragment {
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
        return view;
    }
}
