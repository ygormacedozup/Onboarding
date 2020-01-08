package br.com.zup.onboarding.android.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;
import br.com.zup.onboarding.android.model.UserSessionManager;
import br.com.zup.onboarding.android.viewmodel.ResultViewModel;

public class ResultActivity extends AppCompatActivity {
    private Button btnSendAndFinalize;
    private ImageView gif;
    private TextView thanksForResults, moreInfo, peopleResult, porcentScoreResult, textResultOne, textResultTwo;
    private ResultViewModel resultViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setViews();
        setLayout();
        setViewModel();
        observeViewModel();
    }

    private void setLayout() {
        setFonts();
        setGif();
    }

    private void setViewModel() {
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        UserSessionManager manager = new UserSessionManager(this);
        resultViewModel.setUserSessionManager(manager);
    }

    private void observeViewModel() {
        resultViewModel.getFinishedStepLiveData().observe(this, finishedStep -> {
            Log.e("finishedStep", finishedStep.toString());
        });
    }

    private void setFonts() {
        thanksForResults.setTypeface(Utils.getFont(this));
        btnSendAndFinalize.setTypeface(Utils.getFont(this));
        moreInfo.setTypeface(Utils.getFont(this));
        peopleResult.setTypeface(Utils.getFont(this));
        porcentScoreResult.setTypeface(Utils.getFont(this));
        textResultOne.setTypeface(Utils.getFont(this));
        textResultTwo.setTypeface(Utils.getFont(this));
    }

    private void setViews() {
        btnSendAndFinalize = findViewById(R.id.button_send_finalle_results);
        gif = findViewById(R.id.result_gif);
        thanksForResults = findViewById(R.id.thanks_for_result);
        moreInfo = findViewById(R.id.more_info_results);
        peopleResult = findViewById(R.id.people_for_result);
        porcentScoreResult = findViewById(R.id.score_result_porcent);
        textResultOne = findViewById(R.id.score_text_result_one);
        textResultTwo = findViewById(R.id.score_text_result_two);
    }

    private void setGif() {
        Drawable gifDrawable = ContextCompat.getDrawable(this, R.drawable.pontuacao_boa);
        Glide.with(this).load(gifDrawable).into(gif);
    }
}