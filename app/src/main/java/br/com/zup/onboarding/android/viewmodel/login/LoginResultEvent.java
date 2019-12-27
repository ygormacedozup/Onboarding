package br.com.zup.onboarding.android.viewmodel.login;

import android.content.Intent;

public interface LoginResultEvent {
    void onResult(Intent intent, int requestCode);
}
