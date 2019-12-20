package br.com.zup.onboarding.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import br.com.zup.onboarding.android.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

            TextView nameZuuper = findViewById(R.id.name_zuper_cnt);
            String personName = acct.getDisplayName();
            nameZuuper.setText(personName);

            nextQuestions();
            customSpinner();
        }
    }

    private void nextQuestions() {
        Button seeLater = findViewById(R.id.btn_content);
        seeLater.setOnClickListener(v -> {
            Intent intent = new Intent(ContentActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void customSpinner() {
        Spinner spinner = findViewById(R.id.spin_located);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.offices_zup, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }
}
