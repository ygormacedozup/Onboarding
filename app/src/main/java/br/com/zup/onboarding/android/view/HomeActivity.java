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
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    private ImageView photoZupper;
    private Button btnBack;
    private TextView textOnboarding, nameZupper, hiZupper, structureZup, cultureZup, technologyZup ;
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
        hiZupper = findViewById(R.id.home_txt_hello);
        structureZup = findViewById(R.id.home_structure_txt);
        cultureZup = findViewById(R.id.home_culture_txt);
        technologyZup = findViewById(R.id.home_technology_txt);
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
        nameZupper.setTypeface(Utils.getFont(this));
        hiZupper.setTypeface(Utils.getFont(this));
        textOnboarding.setTypeface(Utils.getFont(this));
        btnBack.setTypeface(Utils.getFont(this));
        structureZup.setTypeface(Utils.getFont(this));
        cultureZup.setTypeface(Utils.getFont(this));
        technologyZup.setTypeface(Utils.getFont(this));
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