package br.com.zup.onboarding.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.gms.common.SignInButton;
import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.viewmodel.login.LoginResultEvent;
import br.com.zup.onboarding.android.viewmodel.login.LoginState;
import br.com.zup.onboarding.android.viewmodel.login.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel viewModel;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.ac_login_gmail);
        TextView onboardingText = findViewById(R.id.ac_text_login);
        onboardingText.setTypeface(Utils.getFont(this));

        setViewModel();
        observeViewModel();
        setLoginButtonClickListener();

    }

    private void setViewModel() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        GoogleAuthentication authentication = new GoogleAuthentication(this);
        viewModel.setAuthentication(authentication);

        LoginResultEvent loginResultEvent = this::startActivityForResult;
        viewModel.setLoginResultEvent(loginResultEvent);
    }

    private void observeViewModel() {
        viewModel.getStateLiveData().observe(this, state -> {
            if (state == LoginState.SUCCESS) navigateToHome();
            if (state == LoginState.ERROR) showErrorMessage();
        });
    }

    private void setLoginButtonClickListener() {
        signInButton.setOnClickListener(v -> viewModel.startSignInIntent());
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
        startActivity(intent);
    }

    private void showErrorMessage() {
        String errorMessage = "Por favor, faça o login com email zup!";
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int RC_SIGN_IN = 0;
        if (requestCode == RC_SIGN_IN) {
            viewModel.onSignInResultReceived(data);
        }
    }
}