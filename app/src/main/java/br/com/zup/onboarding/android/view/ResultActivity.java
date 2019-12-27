package br.com.zup.onboarding.android.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;

public class ResultActivity extends AppCompatActivity {
    private Button btnSendAndFinalize;
    private ImageView gif;
    private TextView thanksForResults, moreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setViews();
        setFonts();
        setGif();
        setSendAndFinalizeClickListener();
    }

    private void setFonts() {
        thanksForResults.setTypeface(Utils.getFont(this));
        btnSendAndFinalize.setTypeface(Utils.getFont(this));
        moreInfo.setTypeface(Utils.getFont(this));
    }

    private void setViews() {
        btnSendAndFinalize = findViewById(R.id.button_send_finalle_results);
        gif = findViewById(R.id.result_gif);
        thanksForResults = findViewById(R.id.thanks_for_result);
        moreInfo = findViewById(R.id.more_info_results);
    }

    private void setGif() {
        Drawable gifDrawable = ContextCompat.getDrawable(this, R.drawable.result);
        Glide.with(this).load(gifDrawable).into(gif);
    }

    private void setSendAndFinalizeClickListener() {
        btnSendAndFinalize.setOnClickListener(v -> {
            Toast.makeText(this, "Resultado enviado!", Toast.LENGTH_LONG).show();
        });
    }
}