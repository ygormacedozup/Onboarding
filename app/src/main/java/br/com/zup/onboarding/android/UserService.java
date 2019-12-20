package br.com.zup.onboarding.android;

import br.com.zup.onboarding.android.model.entity.User;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserService {
    @GET("/zupper/thalles.freitas@zup.com.br")
    Observable<User> getUser();

//    @GET("/podandproject")
//    Observable<> getUserPodProject();
}
