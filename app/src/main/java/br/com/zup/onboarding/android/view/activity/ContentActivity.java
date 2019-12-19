package br.com.zup.onboarding.android.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;

public class ContentActivity extends AppCompatActivity {

    private TextView nameZupper, continueTxt,podZup, projectZup,locatedZup,hiTxt;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

            TextView nameZuuper = findViewById(R.id.name_zuper_cnt);
            String personName = acct.getDisplayName();
            nameZuuper.setText(personName);
            setViews();
            setFonts(personName);
            nextQuestions();
            customSpinner();
            setViews();
        }
    }

    private void setFonts(String personName) {
        nameZupper.setText(personName);
        nameZupper.setTypeface(Utils.getFont(this));
        continueTxt.setTypeface(Utils.getFont(this));
        podZup.setTypeface(Utils.getFont(this));
        projectZup.setTypeface(Utils.getFont(this));
        locatedZup.setTypeface(Utils.getFont(this));
        hiTxt.setTypeface(Utils.getFont(this));
        confirmBtn.setTypeface(Utils.getFont(this));
    }

    private void setViews() {

        hiTxt = findViewById(R.id.ctn_hi_txt);
        nameZupper = findViewById(R.id.name_zuper_cnt);
        continueTxt = findViewById(R.id.continues_txt_cnt);
        podZup = findViewById(R.id.pod_txt_view);
        projectZup = findViewById(R.id.project_txt_view);
        locatedZup = findViewById(R.id.located_edit_txt);
        confirmBtn = findViewById(R.id.btn_content);
    }

    private void nextQuestions() {
        confirmBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ContentActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void customSpinner() {

        Spinner locatedSpinner = findViewById(R.id.located_spi);
        ArrayAdapter locatedAdapter = ArrayAdapter.createFromResource(this, R.array.offices_zup, android.R.layout.simple_list_item_checked);
        locatedAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        locatedSpinner.setAdapter(locatedAdapter);

        Spinner podSpinner = findViewById(R.id.pod_spi);
        ArrayAdapter podAdapter = ArrayAdapter.createFromResource(this, R.array.pod_zup, android.R.layout.simple_list_item_checked);
        podAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        podSpinner.setAdapter(podAdapter);

        Spinner projectSpinner = findViewById(R.id.project_spi);
        ArrayAdapter projectAdapter = ArrayAdapter.createFromResource(this,R.array.project_zup, android.R.layout.simple_list_item_checked);
        projectAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        projectSpinner.setAdapter(projectAdapter);

    }
}
