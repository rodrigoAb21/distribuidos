package com.distribuidos.uagrm.android.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.distribuidos.uagrm.android.entities.AccessToken;
import com.distribuidos.uagrm.android.responses.ModeloResponse;
import com.distribuidos.uagrm.android.responses.CabeceraResponse;

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("username") String username, @Field("password") String password);

    @POST("logout")
    Call<String> logout();


    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("modelos")
    Call<CabeceraResponse> modelos();

    @GET("modelos/{id}")
    Call<ModeloResponse> modelo(@Path("id") String id);


}
