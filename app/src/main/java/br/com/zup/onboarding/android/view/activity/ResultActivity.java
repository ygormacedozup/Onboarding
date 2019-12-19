package br.com.zup.onboarding.android.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import br.com.zup.onboarding.android.R;

public class ResultActivity extends AppCompatActivity {
    private Button btnSendAndFinalize;
    private ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setViews();
        setGif();
        setSendAndFinalizeClickListener();
    }

    private void setViews() {
        btnSendAndFinalize = findViewById(R.id.send_and_finalize);
        gif = findViewById(R.id.result_gif);
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