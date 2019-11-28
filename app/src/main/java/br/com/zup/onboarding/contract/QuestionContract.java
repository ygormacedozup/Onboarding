package br.com.zup.onboarding.contract;

import java.util.List;

import br.com.zup.onboarding.model.Question;

public interface QuestionContract {
    interface View {
        void setFragments();
        void showFragment();
        void showNextQuestion();
        void showResult();
    }

    interface Presenter {
        void start(View view);
        void stop();
        List<Question> loadQuestions();
        void changeQuestion();
    }

    interface Repository {
        List<Question> getQuestions();
    }
}