package br.com.zup.onboarding;

import br.com.zup.onboarding.model.entity.Step;
import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionService {
    @GET("/step")
    Call<Step> getStep();
}
