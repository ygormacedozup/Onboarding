package br.com.zup.onboarding.android.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.model.UserSessionManager;
import br.com.zup.onboarding.android.model.entity.User;
import br.com.zup.onboarding.android.viewmodel.home.HomeState;
import br.com.zup.onboarding.android.viewmodel.home.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    private HomeViewModel viewModel;
    private ProgressBar loadingBar;
    private Button btnConfirm;
    private TextView textOnboarding, nameZupper, hiZupper, structureZup, cultureZup, technologyZup, textFinish;
    private ImageView rocketOne, rocketTwo, rocketThree, ballOne, ballTwo, ballThree, lineOne, lineTwo, photoZupper;
    private User user;
    private Uri userPhoto;
    private GoogleAuthentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        authentication = new GoogleAuthentication(this);

        setViews();
        setViewModel();
        observeViewModel();
    }

    private void setViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.setAuthentication(authentication);

        UserSessionManager manager = new UserSessionManager(this);
        viewModel.setUserSessionManager(manager);
    }

    private void observeViewModel() {
        viewModel.getStateLiveData().observe(this, state -> {
            if (state == HomeState.FIRST_STEP_COMPLETED) setStateFirstStepCompleted();
            if (state == HomeState.SECOND_STEP_COMPLETED) setStateSecondStepCompleted();
            if (state == HomeState.ALL_STEPS_COMPLETED) setStateAllStepsCompleted();

        });

        viewModel.getUserLiveData().observe(this, userResponse -> {
            user = userResponse;
            setLayout();
            if (userPhoto != null) {
                viewModel.stopLoading();
            }
        });

        viewModel.getUserPhotoLiveData().observe(this, photoResponse -> {
            userPhoto = photoResponse;
            if (user != null) {
                viewModel.stopLoading();
            }
        });

        viewModel.getIsLoadingLiveData().observe(this, this::setLoading);
    }

    private void setLayout() {
        setContinueButtonClickListener();
        setUserPhoto();
        setUserName(user.getName());
    }

    private void setViews() {
        loadingBar = findViewById(R.id.loading_progress_bar);
        photoZupper = findViewById(R.id.home_logo_rocket);
        nameZupper = findViewById(R.id.home_txt_receive);
        textOnboarding = findViewById(R.id.home_text_onboarding);
        hiZupper = findViewById(R.id.home_txt_hello);
        structureZup = findViewById(R.id.home_structure_txt);
        cultureZup = findViewById(R.id.home_culture_txt);
        technologyZup = findViewById(R.id.home_technology_txt);
        btnConfirm = findViewById(R.id.home_confirm_button);
        rocketOne = findViewById(R.id.db_rocket_one);
        rocketTwo = findViewById(R.id.db_rocket_two);
        rocketThree = findViewById(R.id.db_rocket_three);
        ballOne = findViewById(R.id.db_ball_one);
        ballTwo = findViewById(R.id.db_ball_two);
        ballThree = findViewById(R.id.db_ball_three);
        lineOne = findViewById(R.id.db_line_green);
        lineTwo = findViewById(R.id.db_line_grey);
        textFinish = findViewById(R.id.text_finish);
    }

    private void setLoading(boolean isLoading) {
        int loadingBarVisibility = isLoading ? View.VISIBLE : View.INVISIBLE;
        int informationVisibility = isLoading ? View.INVISIBLE : View.VISIBLE;

        loadingBar.setVisibility(loadingBarVisibility);

        photoZupper.setVisibility(informationVisibility);
        nameZupper.setVisibility(informationVisibility);
        textOnboarding.setVisibility(informationVisibility);
        hiZupper.setVisibility(informationVisibility);
        structureZup.setVisibility(informationVisibility);
        cultureZup.setVisibility(informationVisibility);
        technologyZup.setVisibility(informationVisibility);
        btnConfirm.setVisibility(informationVisibility);
        rocketOne.setVisibility(informationVisibility);
        rocketTwo.setVisibility(informationVisibility);
        rocketThree.setVisibility(informationVisibility);
        ballOne.setVisibility(informationVisibility);
        ballTwo.setVisibility(informationVisibility);
        ballThree.setVisibility(informationVisibility);
        lineOne.setVisibility(informationVisibility);
        lineTwo.setVisibility(informationVisibility);
        textFinish.setVisibility(informationVisibility);
    }

    private void setContinueButtonClickListener() {
        Button continueQuestions = findViewById(R.id.home_confirm_button);
        continueQuestions.setOnClickListener(v -> navigateToQuestions());
    }

    private void setUserPhoto() {
        if (userPhoto != null) {
            Glide.with(this).load(String.valueOf(userPhoto)).circleCrop().into(photoZupper);
        }
    }

    private void setUserName(String personName) {
        nameZupper.setText(personName);
        nameZupper.setTypeface(Utils.getFont(this));
        hiZupper.setTypeface(Utils.getFont(this));
        textOnboarding.setTypeface(Utils.getFont(this));
        structureZup.setTypeface(Utils.getFont(this));
        cultureZup.setTypeface(Utils.getFont(this));
        technologyZup.setTypeface(Utils.getFont(this));
        btnConfirm.setTypeface(Utils.getFont(this));
        textFinish.setTypeface(Utils.getFont(this));
    }

    private void navigateToQuestions() {
        Intent intent = new Intent(HomeActivity.this, QuestionActivity.class);
        startActivity(intent);
    }

    private void setStateFirstStepCompleted() {
        rocketOne.setImageResource(R.drawable.rocket_checked);
        ballOne.setBackgroundResource(R.drawable.ball_checked);
        lineOne.setBackgroundResource(R.drawable.line_checked);

        textFinish.setVisibility(View.GONE);
    }

    private void setStateSecondStepCompleted() {
        rocketOne.setImageResource(R.drawable.rocket_checked);
        rocketTwo.setImageResource(R.drawable.rocket_checked);

        ballOne.setBackgroundResource(R.drawable.ball_checked);
        ballTwo.setBackgroundResource(R.drawable.ball_checked);

        lineOne.setBackgroundResource(R.drawable.line_checked);
        lineTwo.setBackgroundResource(R.drawable.line_checked);

        textFinish.setVisibility(View.GONE);
    }

    private void setStateAllStepsCompleted() {
        rocketOne.setImageResource(R.drawable.rocket_checked);
        rocketTwo.setImageResource(R.drawable.rocket_checked);
        rocketThree.setImageResource(R.drawable.rocket_checked);

        ballOne.setBackgroundResource(R.drawable.ball_checked);
        ballTwo.setBackgroundResource(R.drawable.ball_checked);
        ballThree.setBackgroundResource(R.drawable.ball_checked);

        lineOne.setBackgroundResource(R.drawable.line_checked);
        lineTwo.setBackgroundResource(R.drawable.line_checked);

        btnConfirm.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        // Não será permitido voltar a partir desta tela.
    }
}