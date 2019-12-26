package br.com.zup.onboarding.android;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private Retrofit retrofit;
    private final int TIMEOUT_IN_SECONDS = 60;

    public RetrofitInitializer() {
        OkHttpClient client = getClient();
        retrofit = getRetrofit(client);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    private Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public UserService getUserService() {
        return retrofit.create(UserService.class);
    }
}
