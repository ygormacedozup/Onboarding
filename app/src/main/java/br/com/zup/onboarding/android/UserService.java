package br.com.zup.onboarding.android;

import br.com.zup.onboarding.android.model.entity.User;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    /*@GET("/zupper/thalles.freitas@zup.com.br")
    Call<User> getUser();*/

    /*@GET("/zupper/thalles.freitas@zup.com.br")
    Flowable<User> getUser();*/

    @GET("/zupper/thalles.freitas@zup.com.br")
    Observable<User> getUser();
}
