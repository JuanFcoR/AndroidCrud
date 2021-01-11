package com.example.androidcrud.Interfaces;

import com.example.androidcrud.Models.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyApiService {

    @DELETE("api/Cerveza/{param}/{criterio}")
    public Call<respuesta> getAvanzado(@Path("param") String param, String criteriol);

    @GET("api/Cerveza/")
    public Call<respuesta> getCervezas();

    @GET("api/Cerveza/{Id}")
    public Call<cerveza> find(@Path("Id") String Id);

    @POST("api/Cerveza")
    public Call<respuesta> save(@Body cerveza cerv);

    @PUT("api/Cerveza")
    public Call<respuesta> edit(@Body cerveza cerv);

    @DELETE("api/Cerveza/{Id}")
    public Call<respuesta> delete(@Path("Id") String Id);
    
}
