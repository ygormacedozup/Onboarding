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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;

public class HomeActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView photoZupper = findViewById(R.id.db_logo_foguete);
        TextView nameZuuper = findViewById(R.id.db_txt_receive);
        nameZuuper.setTypeface(Utils.getFont(this));
//        ImageView btnBack = findViewById(R.id.back_btn);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.back_btn) {
//                    signOut();
//                }
//            }
//        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();

            nameZuuper.setText(personName);

            Glide.with(this).load(String.valueOf(personPhoto)).circleCrop().into(photoZupper);

            nextQuestions();
        }
    }

//    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Intent newIntent = new Intent(HomeActivity.this, LoginActivity.class);
//                        startActivity(newIntent);
//                        Toast.makeText(HomeActivity.this, R.string.signOut, Toast.LENGTH_LONG).show();
//                    }
//                });
//    }

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
