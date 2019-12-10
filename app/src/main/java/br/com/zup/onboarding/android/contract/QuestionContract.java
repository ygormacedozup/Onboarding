package br.com.zup.onboarding.android.contract;

import java.util.List;

import br.com.zup.onboarding.android.model.entity.Question;

public interface QuestionContract {
    interface View {
        void setQuestions(List<Question> questions);
        void setFragments();
        void showFragment();
        void showNextQuestion();
        void showResult();
    }

    interface Presenter {
        void start(View view);
        void stop();
        void loadQuestions();
        void onQuestionsLoaded();
        void changeQuestion();
    }

    interface Repository {
        void getQuestions(OnRequestFinishedListener listener);
    }

    interface OnRequestFinishedListener {
        void onRequestFinished(List<Question> questions);
        void onRequestFailed(Throwable throwable);
    }
}