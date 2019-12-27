package br.com.zup.onboarding.android;

import br.com.zup.onboarding.android.model.entity.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("/zupper/thalles.freitas@zup.com.br")
    Observable<User> getUser();

//    @GET("/podandproject")
//    Observable<> getUserPodProject();

    /*@FormUrlEncoded
    @POST("/zupper")
    Single<User> saveUser(@Body User user);*/

    @FormUrlEncoded
    @POST("/user")
    Observable<User> saveUser(@Body User user);
}
