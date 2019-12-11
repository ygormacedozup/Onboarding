package br.com.zup.onboarding.android.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.zup.onboarding.android.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
    }
}
