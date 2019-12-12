package br.com.zup.onboarding.android.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;

public class HomeActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private ImageView photoZupper;
    private TextView nameZupper;
    private ImageView btnBack;
    private TextView textOnboarding;
    private TextView textHello;
    private String personName;
    private Uri personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setGso();
        nextQuestions();
        setViews();
        setUserPhoto();
        setBackButtonClickListenner();
        setText();
    }

    private void setGso() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personPhoto = acct.getPhotoUrl();
        }
    }

    private void setUserPhoto() {
        Glide.with(this).load(String.valueOf(personPhoto)).circleCrop().into(photoZupper);
    }

    private void setBackButtonClickListenner() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.home_back_btn) {
                    signOut();
                }
            }
        });
    }

    private void setViews() {
        photoZupper = findViewById(R.id.home_logo_rocket);
        nameZupper = findViewById(R.id.home_txt_receive);
        btnBack = findViewById(R.id.home_back_btn);
        textOnboarding = findViewById(R.id.home_text_onboarding);
        textHello = findViewById(R.id.home_txt_hello);
    }

    private void setText() {
        nameZupper.setTypeface(Utils.getFont(this));
        textOnboarding.setTypeface(Utils.getFont(this));
        textHello.setTypeface(Utils.getFont(this));
        nameZupper.setText(personName);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent newIntent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(newIntent);
                        Toast.makeText(HomeActivity.this, R.string.signOut, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void nextQuestions() {
        Button seeLater = findViewById(R.id.db_continue_button);
        seeLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}