package br.com.zup.onboarding.android.view.register.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.SignInButton;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.view.register.fragments.listeners.OnLoginCompletedListener;

public class LoginFragment extends Fragment {
    private Context context;
    private final OnLoginCompletedListener listener;
    private SignInButton signInButton;

    public LoginFragment(OnLoginCompletedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = getContext();

        setViews(view);
        setSignInButtonClickListener();

        return view;
    }

    private void setViews(View view) {
        signInButton = view.findViewById(R.id.sign_in_button);
        TextView onboardingText = view.findViewById(R.id.onboarding_text);
        onboardingText.setTypeface(Utils.getFont(context));
    }

    private void setSignInButtonClickListener() {
        signInButton.setOnClickListener(v -> listener.onCompleted());
    }
}
