package br.com.zup.onboarding.android.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.model.UserSessionManager;
import br.com.zup.onboarding.android.model.entity.User;
import br.com.zup.onboarding.android.view.register.RegisterActivity;
import br.com.zup.onboarding.android.viewmodel.home.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    private HomeViewModel viewModel;
    private ProgressBar loadingBar;
    private ImageView photoZupper;
    private Button btnExit, btnConfirm;
    private TextView textOnboarding, nameZupper, hiZupper, structureZup, cultureZup, technologyZup;
    private ImageView rocketOne, rocketTwo, rocketThree;
    private ImageView ballOne, ballTwo, ballThree;
    private ImageView lineOne, lineTwo;
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
    }

    private void observeViewModel() {
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
        setBackButtonClickListener();
        setContinueButtonClickListener();
        setUserPhoto();
        setUserName(user.getName());
    }

    private void setViews() {
        loadingBar = findViewById(R.id.loading_progress_bar);
        photoZupper = findViewById(R.id.home_logo_rocket);
        nameZupper = findViewById(R.id.home_txt_receive);
        btnExit = findViewById(R.id.home_button_exit);
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
        btnExit.setVisibility(informationVisibility);
        btnConfirm.setVisibility(informationVisibility);
        rocketOne.setVisibility(informationVisibility);
        rocketTwo.setVisibility(informationVisibility);
        rocketThree.setVisibility(informationVisibility);
        ballOne.setVisibility(informationVisibility);
        ballTwo.setVisibility(informationVisibility);
        ballThree.setVisibility(informationVisibility);
        lineOne.setVisibility(informationVisibility);
        lineTwo.setVisibility(informationVisibility);
    }

    private void setBackButtonClickListener() {
        btnExit.setOnClickListener(v -> {
            if (v.getId() == R.id.home_button_exit) {
                UserSessionManager manager = new UserSessionManager(this);
                authentication.signOut().addOnCompleteListener(this, task -> navigateToLogin());
            }
        });
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
        btnExit.setTypeface(Utils.getFont(this));
        structureZup.setTypeface(Utils.getFont(this));
        cultureZup.setTypeface(Utils.getFont(this));
        technologyZup.setTypeface(Utils.getFont(this));
        btnConfirm.setTypeface(Utils.getFont(this));
    }

    private void navigateToLogin() {
        Intent newIntent = new Intent(HomeActivity.this, RegisterActivity.class);
        startActivity(newIntent);
        Toast.makeText(HomeActivity.this, R.string.signOut, Toast.LENGTH_LONG).show();
    }

    private void navigateToQuestions() {
        Intent intent = new Intent(HomeActivity.this, QuestionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Não será permitido voltar a partir desta tela.
    }
}