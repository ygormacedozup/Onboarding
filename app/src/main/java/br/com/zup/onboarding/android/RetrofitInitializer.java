package br.com.zup.onboarding.android;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private Retrofit retrofit;
//    private Retrofit testRetrofitContent;

    public RetrofitInitializer() {
        initialize();
//        projectAndPod();
    }

    private void initialize() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


//    private void projectAndPod() {
//        testRetrofitContent = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL_TEST)
//                .addCallAdapterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory)
//                .build();
//    }
    /*public QuestionService getQuestionService() {
        return retrofit.create(QuestionService.class);
    }*/

    public UserService getUserService() {
        return retrofit.create(UserService.class);
    }
//
//    public UserService getUserContants() {
//        return testRetrofitContent.callAdapter(UserService.class);
//    }
}
