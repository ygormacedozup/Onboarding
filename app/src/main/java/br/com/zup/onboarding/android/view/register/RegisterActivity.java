package br.com.zup.onboarding.android.view.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import br.com.zup.onboarding.android.GoogleAuthentication;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.model.UserSessionManager;
import br.com.zup.onboarding.android.view.HomeActivity;
import br.com.zup.onboarding.android.view.register.fragments.LoginFragment;
import br.com.zup.onboarding.android.view.register.fragments.PodLocationFragment;
import br.com.zup.onboarding.android.view.register.fragments.listeners.OnLoginCompletedListener;
import br.com.zup.onboarding.android.view.register.fragments.listeners.OnPodLocationCompletedListener;
import br.com.zup.onboarding.android.viewmodel.register.LoginResultEvent;
import br.com.zup.onboarding.android.viewmodel.register.RegisterState;
import br.com.zup.onboarding.android.viewmodel.register.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel viewModel;
    private OnLoginCompletedListener loginListener;
    private OnPodLocationCompletedListener podLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setFragmentListeners();
        showLoginFragment();

        setViewModel();
        observeViewModel();
    }

    private void setFragmentListeners() {
        loginListener = () -> viewModel.startSignInIntent();
        podLocationListener = (pod, location) -> viewModel.saveUser(pod, location);
    }

    private void showLoginFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, new LoginFragment(loginListener), null);
        transaction.commit();
    }

    private void showPodLocationFragment(String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, new PodLocationFragment(podLocationListener, name), null);
        transaction.commit();
    }

    private void setViewModel() {
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        GoogleAuthentication authentication = new GoogleAuthentication(this);
        viewModel.setAuthentication(authentication);

        UserSessionManager manager = new UserSessionManager(this);
        viewModel.setUserSessionManager(manager);

        LoginResultEvent loginResultEvent = this::startActivityForResult;
        viewModel.setLoginResultEvent(loginResultEvent);
    }

    private void observeViewModel() {
        viewModel.getStateLiveData().observe(this, state -> {
            if (state == RegisterState.ALREADY_LOGGED) navigateToHome();
            if (state == RegisterState.SIGN_IN_SUCCESS) showPodLocationFragment(viewModel.getUserName());
            if (state == RegisterState.SIGN_IN_ERROR) showErrorMessage();
            if (state == RegisterState.REGISTER_SUCCESS) navigateToHome();
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
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