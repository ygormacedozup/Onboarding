package br.com.zup.onboarding.android.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.contract.HomeContract;
import br.com.zup.onboarding.android.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private ImageView photoZupper;
    private TextView nameZupper;
    private ImageView btnBack;
    private TextView textOnboarding;
    private TextView textHello;
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new HomePresenter();
        presenter.start(this);
        presenter.setGso(this);
    }

    @Override
    public void setUserPhoto(Uri userPhoto) {
        Glide.with(this).load(String.valueOf(userPhoto)).circleCrop().into(photoZupper);
    }

    @Override
    public void setBackButtonClickListener() {
        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if (v.getId() == R.id.home_back_btn) {
                    presenter.onBackButtonClicked();
                }
            }
        });
    }

    @Override
    public void setViews() {
        photoZupper = findViewById(R.id.home_logo_rocket);
        nameZupper = findViewById(R.id.home_txt_receive);
        btnBack = findViewById(R.id.home_back_btn);
        textOnboarding = findViewById(R.id.home_text_onboarding);
        textHello = findViewById(R.id.home_txt_hello);
    }

    @Override
    public void setText(String personName) {
        nameZupper.setTypeface(Utils.getFont(this));
        textOnboarding.setTypeface(Utils.getFont(this));
        textHello.setTypeface(Utils.getFont(this));
        nameZupper.setText(personName);
    }

    @Override
    public void navigateToLogin() {
        Intent newIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(newIntent);
        Toast.makeText(HomeActivity.this, R.string.signOut, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setContinueButtonClickListener() {
        Button continueQuestions = findViewById(R.id.db_continue_button);
        continueQuestions.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.onContinueButtonClicked();
            }
        });
    }

    @Override
    public void navigateToQuestions() {
        Intent intent = new Intent(HomeActivity.this, QuestionActivity.class);
        startActivity(intent);
    }
}