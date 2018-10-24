package com.distribuidos.uagrm.android.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.distribuidos.uagrm.android.entities.AccessToken;
import com.distribuidos.uagrm.android.entities.Encuestas;
import com.distribuidos.uagrm.android.responses.AsignacionResponse;


import java.util.List;

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("username") String username, @Field("password") String password);

    @POST("logout")
    Call<String> logout();


    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("asignaciones")
    Call<AsignacionResponse> asignaciones();

    @POST("encuestas")
    Call<Void> enviarEncuestas(@Body Encuestas encuestas);



}
