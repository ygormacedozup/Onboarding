package br.com.zup.onboarding.android;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private Retrofit retrofit;

    public RetrofitInitializer() {
        initialize();
    }

    private void initialize() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.TEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public QuestionService getQuestionService() {
        return retrofit.create(QuestionService.class);
    }
}
