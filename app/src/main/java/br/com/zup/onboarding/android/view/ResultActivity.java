package br.com.zup.onboarding.android.view;

import android.content.Intent;
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
import br.com.zup.onboarding.android.model.entity.FinishedStep;
import br.com.zup.onboarding.android.model.entity.User;
import br.com.zup.onboarding.android.viewmodel.ResultViewModel;

public class ResultActivity extends AppCompatActivity {
    private Button btnFinalize;
    private ImageView gif;
    private TextView thanksForResults, moreInfo, peopleResults, textResultOne, textResultTwo, scoreResults;
    private ResultViewModel resultViewModel;
    private FinishedStep finishedStep;

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
        setFinalizeButtonClickListener();
    }

    private void setViewModel() {
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        UserSessionManager manager = new UserSessionManager(this);
        resultViewModel.setUserSessionManager(manager);
    }

    private void observeViewModel() {
        resultViewModel.getFinishedStepLiveData().observe(this, finishedStepResponse -> {
            Log.e("finishedStep", finishedStepResponse.toString());
            showResult(finishedStepResponse);
        });
    }

    private void showResult(FinishedStep finishedStep) {
        peopleResults.setText(finishedStep.getZupper().getName());
        scoreResults.setText(String.valueOf(finishedStep.getPercentageScore()));
    }

    private void setFonts() {
        thanksForResults.setTypeface(Utils.getFont(this));
        btnFinalize.setTypeface(Utils.getFont(this));
        moreInfo.setTypeface(Utils.getFont(this));
        peopleResults.setTypeface(Utils.getFont(this));
        scoreResults.setTypeface(Utils.getFont(this));
        textResultOne.setTypeface(Utils.getFont(this));
        textResultTwo.setTypeface(Utils.getFont(this));
    }

    private void setViews() {
        btnFinalize = findViewById(R.id.button_send_finalle_results);
        gif = findViewById(R.id.result_gif);
        thanksForResults = findViewById(R.id.thanks_for_result);
        moreInfo = findViewById(R.id.more_info_results);
        peopleResults = findViewById(R.id.people_for_result);
        scoreResults = findViewById(R.id.score_result);
        textResultOne = findViewById(R.id.score_text_result_one);
        textResultTwo = findViewById(R.id.score_text_result_two);
    }

    private void setGif() {
        Drawable gifDrawable = ContextCompat.getDrawable(this, R.drawable.result);
        Glide.with(this).load(gifDrawable).into(gif);
    }

    private void setFinalizeButtonClickListener() {
        btnFinalize.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}