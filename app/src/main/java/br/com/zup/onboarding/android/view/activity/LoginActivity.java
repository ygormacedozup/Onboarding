package br.com.zup.onboarding.android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.model.entity.User;

public class LoginActivity extends AppCompatActivity {
    private int RC_SIGN_IN = 0;
    private SignInButton signInButton;

    private GoogleAuthentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.ac_login_gmail);
        authentication = new GoogleAuthentication(this);

        setLoginButtonClickListener();
    }

    private void setLoginButtonClickListener() {
        signInButton.setOnClickListener(v -> startSignInIntent());
    }

    private void startSignInIntent() {
        Intent intent = authentication.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    private void onSignInResultReceived(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            User user = new User();

            user.setId(1);
            user.setName(account.getDisplayName());
            user.setEmail(account.getEmail());

            // Save user

            navigateToHome();
        } catch (ApiException e) {
            String errorMessage = "Por favor, faça o login com email zup!";
            showErrorMessage(errorMessage);
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void showErrorMessage(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            onSignInResultReceived(data);
        }
    }
}