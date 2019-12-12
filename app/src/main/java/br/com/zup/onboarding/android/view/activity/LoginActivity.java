package br.com.zup.onboarding.android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.contract.LoginContract;
import br.com.zup.onboarding.android.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;
    private int RC_SIGN_IN = 0;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        signInButton = findViewById(R.id.ac_login_gmail);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void setLoginButtonClickListener() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSignInClicked();
            }
        });
    }

    @Override
    public void startSignInIntent() {
        Intent intent = presenter.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public void navigateToHome(GoogleSignInAccount account) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("account", account);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            presenter.onSignInResultReceived(data);
        }
    }
}