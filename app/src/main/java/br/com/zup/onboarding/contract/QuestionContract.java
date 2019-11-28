package br.com.zup.onboarding.contract;

import java.util.List;

import br.com.zup.onboarding.model.Question;

public interface QuestionContract {
    interface View {
        void setFragments();
        void showFragment();
        void showNextQuestion();
        void showResult();
        void showResetQuestions();
    }

    interface Presenter {
        void start(View view);
        void stop();
        List<Question> loadQuestions();
        void changeQuestion();
        void resetQuestions();
    }

    interface Repository {
        List<Question> getQuestions();
    }
}