package br.com.zup.onboarding.android.model.entity;

import com.google.gson.annotations.SerializedName;

public class UserAlternative {
    @SerializedName("zupper")
    private UserAlternativeRequest user;

    @SerializedName("alternative")
    private Alternative alternative;

    public UserAlternative(UserAlternativeRequest user, Alternative alternative) {
        this.user = user;
        this.alternative = alternative;
    }
}
