package br.com.zup.onboarding.android;

import br.com.zup.onboarding.android.model.entity.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("/zupper/caique.silva@zup.com.br")
    Observable<User> getUser();

    @GET("/zupper/{email}")
    Observable<User> getUserByEmail(@Path("email") String email);

    @POST("/zupper")
    Observable<User> saveUser(@Body User user);
}
