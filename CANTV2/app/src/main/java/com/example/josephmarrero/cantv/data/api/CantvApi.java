package com.example.josephmarrero.cantv.data.api;

/**
 * Created by marre on 15/3/2017.
 */

import com.example.josephmarrero.cantv.data.api.model.Affiliate;
import com.example.josephmarrero.cantv.data.api.model.LoginBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CantvApi {

    // TODO: Cambiar host por "10.0.3.2" para Genymotion.
    // TODO: Cambiar host por "10.0.2.2" para AVD.
    // TODO: Cambiar host por IP de tu PC para dispositivo real.
    public static final String BASE_URL = "http://www.dev-opsjimp.com/api.cantv.com/v1/";

    @POST("affiliates/login")
    Call<Affiliate> login(@Body LoginBody loginBody);

}
