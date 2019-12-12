package br.com.zup.onboarding.android.presenter;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import br.com.zup.onboarding.android.contract.HomeContract;

public class HomePresenter implements HomeContract.Presenter {

    private GoogleSignInClient mGoogleSignInClient;
    private HomeContract.View view;
    private Activity activity;


    @Override
    public void start(HomeContract.View view) {
        this.view = view;
        view.setViews();
        view.setBackButtonClickListener();
        view.setContinueButtonClickListener();
    }

    @Override
    public void setGso(Activity activity) {
        this.activity = activity;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(activity);
        if (acct != null) {
            view.setText(acct.getDisplayName());
            view.setUserPhoto(acct.getPhotoUrl());
        }
    }

    @Override
    public void onBackButtonClicked() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        view.navigateToLogin();
                    }
                });
    }

    @Override
    public void onContinueButtonClicked() {
        view.navigateToQuestions();
    }
}
