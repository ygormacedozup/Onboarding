package br.com.zup.onboarding.android.viewmodel.register;

import android.content.Intent;

public interface LoginResultEvent {
    void onResult(Intent intent, int requestCode);
}
