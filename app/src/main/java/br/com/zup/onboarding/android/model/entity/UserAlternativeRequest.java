package br.com.zup.onboarding.android.model.entity;

import com.google.gson.annotations.SerializedName;

public class UserAlternativeRequest {
    @SerializedName("id")
    private int id;

    public UserAlternativeRequest(int id) {
        this.id = id;
    }
}
