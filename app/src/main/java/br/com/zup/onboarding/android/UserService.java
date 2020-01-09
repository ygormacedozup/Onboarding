package br.com.zup.onboarding.android;

import br.com.zup.onboarding.android.model.entity.FinishedStep;
import br.com.zup.onboarding.android.model.entity.User;
import br.com.zup.onboarding.android.model.entity.UserAlternative;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("/zupper/{email}")
    Observable<Response<User>> getUserByEmail(@Path("email") String email);

    @POST("/zupper")
    Observable<User> saveUser(@Body User user);

    @POST("/answer")
    Observable<User> saveAlternative(@Body UserAlternative userAlternative);

    @POST("/zupper/finish")
    Observable<FinishedStep> finishStep(@Body User user);
}