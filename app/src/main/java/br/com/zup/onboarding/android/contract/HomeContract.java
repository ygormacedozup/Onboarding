package br.com.zup.onboarding.android.contract;

import android.app.Activity;
import android.net.Uri;

import java.util.List;

import br.com.zup.onboarding.android.model.entity.User;

public interface HomeContract {
    interface View {
        void setUserPhoto(Uri userPhoto);
        void setBackButtonClickListener();
        void setContinueButtonClickListener();
        void setViews();
        void setText(String personName);
        void navigateToQuestions(User user);
        void navigateToLogin();
    }

    interface Presenter {
        void start(View view);
        void setGso(Activity activity);
        void loadUser();
        void onBackButtonClicked();
        void onConfirmButtonClicked();
    }

    /*interface Repository {
        void getUser(OnRequestFinishedListener listener);
    }

    interface OnRequestFinishedListener {
        void onRequestFinished(User user);
        void onRequestFailed(Throwable throwable);
    }*/
}
