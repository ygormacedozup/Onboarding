package br.com.zup.onboarding.android.view.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import br.com.zup.onboarding.android.R;

public class ResultFragment extends Fragment {
    private Context context;
    private SendAndFinalizeListener sendAndFinalizeListener;
    private Button btnSendAndFinalize;

    public ResultFragment(SendAndFinalizeListener sendAndFinalizeListener) {
        this.sendAndFinalizeListener = sendAndFinalizeListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_result, container, false);

        setSendAndFinalizeButton(view);
        setGif(view);

        return view;
    }

    private void setGif(View view) {
        ImageView gif = view.findViewById(R.id.result_gif);
        Drawable gifDrawable = ContextCompat.getDrawable(context, R.drawable.result);
        Glide.with(this).load(gifDrawable).into(gif);
    }

    private void setSendAndFinalizeButton(View view) {
        btnSendAndFinalize = view.findViewById(R.id.send_and_finalize);
        setSendAndFinalizeClickListener();
    }

    private void setSendAndFinalizeClickListener() {
        btnSendAndFinalize.setOnClickListener(v -> {
            sendAndFinalizeListener.sendQuestionResult();
        });
    }

    public interface SendAndFinalizeListener {
        void sendQuestionResult();
    }
}
