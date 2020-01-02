package br.com.zup.onboarding.android.view.register.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.view.register.fragments.listeners.OnPodLocationCompletedListener;
import br.com.zup.onboarding.android.view.register.SpinnerAdapter;

public class PodLocationFragment extends Fragment {
    private Context context;
    private final OnPodLocationCompletedListener listener;
    private TextView nameText;
    private TextView continueText;
    private TextView pod;
    private TextView location;
    private TextView hiText;
    private Button registerButton;
    private Spinner podSpinner;
    private Spinner locationSpinner;
    private String userName;

    public PodLocationFragment(OnPodLocationCompletedListener listener, String userName) {
        this.listener = listener;
        this.userName = userName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pod_location, container, false);
        context = getContext();

        setViews(view);
        setFonts(userName);
        setRegisterButtonClickListener();
        setPodSpinner(view);
        setLocationSpinner(view);

        return view;
    }

    private void setFonts(String personName) {
        nameText.setText(personName);
        nameText.setTypeface(Utils.getFont(context));
        continueText.setTypeface(Utils.getFont(context));
        pod.setTypeface(Utils.getFont(context));
        location.setTypeface(Utils.getFont(context));
        hiText.setTypeface(Utils.getFont(context));
        registerButton.setTypeface(Utils.getFont(context));
    }

    private void setViews(View view) {
        hiText = view.findViewById(R.id.hi_text);
        nameText = view.findViewById(R.id.name_text);
        continueText = view.findViewById(R.id.inform_pod_location_text);
        pod = view.findViewById(R.id.pod);
        location = view.findViewById(R.id.location);
        registerButton = view.findViewById(R.id.register_button);
    }

    private void setRegisterButtonClickListener() {
        registerButton.setOnClickListener(v -> {
            String pod = (String) podSpinner.getSelectedItem();
            String location = (String) locationSpinner.getSelectedItem();

            listener.onCompleted(pod, location);
        });
    }

    private void setPodSpinner(View view) {
        podSpinner = view.findViewById(R.id.pod_spinner);
        List<String> pods = Arrays.asList(context.getResources().getStringArray(R.array.pods_zup));

        SpinnerAdapter adapter = new SpinnerAdapter(context, R.layout.spinner_item, pods);
        podSpinner.setAdapter(adapter);
    }

    private void setLocationSpinner(View view) {
        locationSpinner = view.findViewById(R.id.location_spinner);
        List<String> locations = Arrays.asList(context.getResources().getStringArray(R.array.locations_zup));

        SpinnerAdapter adapter = new SpinnerAdapter(context, R.layout.spinner_item, locations);
        locationSpinner.setAdapter(adapter);
    }
}