package br.com.zup.onboarding.android.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    private ImageView photoZupper;
    private TextView nameZupper;
    private ImageView btnBack;
    private TextView textOnboarding;
    private TextView textHello;

    //private User user;
    private GoogleAuthentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        authentication = new GoogleAuthentication(this);

        setViewModel();
        setLayout();
    }

    private void setViewModel() {
        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getUserLiveData().observe(this, userResponse -> {
            //user = userResponse;
            //setUserName(user.getName());
        });
    }

    private void setLayout() {
        setViews();
        setBackButtonClickListener();
        setContinueButtonClickListener();
        setUserPhoto(authentication.getUserPhoto());
        setUserName(authentication.getUserName());
    }

    private void setViews() {
        photoZupper = findViewById(R.id.home_logo_rocket);
        nameZupper = findViewById(R.id.home_txt_receive);
        btnBack = findViewById(R.id.home_back_btn);
        textOnboarding = findViewById(R.id.home_text_onboarding);
        textHello = findViewById(R.id.home_txt_hello);
    }

    private void setBackButtonClickListener() {
        btnBack.setOnClickListener(v -> {
            if (v.getId() == R.id.home_back_btn) {
                authentication.signOut().addOnCompleteListener(this, task -> navigateToLogin());
            }
        });
    }

    private void setContinueButtonClickListener() {
        Button continueQuestions = findViewById(R.id.db_confirm_button);
        continueQuestions.setOnClickListener(v -> navigateToQuestions());
    }

    private void setUserPhoto(Uri userPhoto) {
        Glide.with(this).load(String.valueOf(userPhoto)).circleCrop().into(photoZupper);
    }

    private void setUserName(String personName) {
        nameZupper.setText(personName);
    }

    private void navigateToLogin() {
        Intent newIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(newIntent);
        Toast.makeText(HomeActivity.this, R.string.signOut, Toast.LENGTH_LONG).show();
    }

    private void navigateToQuestions() {
        Intent intent = new Intent(HomeActivity.this, QuestionActivity.class);
        startActivity(intent);
    }
}