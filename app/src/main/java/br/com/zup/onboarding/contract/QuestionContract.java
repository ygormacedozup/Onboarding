package br.com.zup.onboarding.contract;

import java.util.List;

import br.com.zup.onboarding.model.entity.Question;

public interface QuestionContract {
    interface View {
        void setQuestions(List<Question> questions);
        void setFragments();
        void showFragment();
        void showNextQuestion();
        void showResult();
        void showResetQuestions();
    }

    interface Presenter {
        void start(View view);
        void stop();
        void loadQuestions();
        void onQuestionsLoaded();
        void changeQuestion();
        void resetQuestions();
    }
}