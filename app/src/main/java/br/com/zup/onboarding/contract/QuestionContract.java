package br.com.zup.onboarding.contract;

public interface QuestionContract {
    interface View {
        void showFragment(int index);
        void setFragments();
        void showResult();
    }

    interface Presenter {
        void start(View view);
        void stop();
        void changeQuestion();
    }
}