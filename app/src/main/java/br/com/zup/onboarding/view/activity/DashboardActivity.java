package br.com.zup.onboarding.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.zup.onboarding.R;

public class DashboardActivity extends AppCompatActivity {
    private TextView txtEmail;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setLayout();
        setBtnNextClickListener();
    }

    private void setLayout() {
        txtEmail = findViewById(R.id.db_email_receive);
        btnNext = findViewById(R.id.db_continue_button);
    }

    private void setBtnNextClickListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ContentActivity.class);
                startActivity(intent);
            }
        });
    }
}
