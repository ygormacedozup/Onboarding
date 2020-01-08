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
    private TextView thanksForResults, moreInfo, peopleResults;
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
        peopleResults.setTypeface(Utils.getFont(this));
    }

    private void setViews() {
        btnSendAndFinalize = findViewById(R.id.button_send_finalle_results);
        gif = findViewById(R.id.result_gif);
        thanksForResults = findViewById(R.id.thanks_for_result);
        moreInfo = findViewById(R.id.more_info_results);
        peopleResults = findViewById(R.id.people_for_result);
    }

    private void setGif() {
        Drawable gifDrawable = ContextCompat.getDrawable(this, R.drawable.result);
        Glide.with(this).load(gifDrawable).into(gif);
    }
}