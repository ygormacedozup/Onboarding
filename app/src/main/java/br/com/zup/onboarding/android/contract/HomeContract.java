package br.com.zup.onboarding.android.contract;

import android.app.Activity;
import android.net.Uri;

public interface HomeContract {
    interface View {
        void setUserPhoto(Uri userPhoto);
        void setBackButtonClickListener();
        void setContinueButtonClickListener();
        void setViews();
        void setText(String personName);
        void navigateToQuestions();
        void navigateToLogin();
    }

    interface Presenter {
        void start(View view);
        void setGso(Activity activity);
        void onBackButtonClicked();
        void onContinueButtonClicked();
    }
}
