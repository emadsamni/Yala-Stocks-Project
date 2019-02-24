package com.mg.spstocks.api.classes;

import com.mg.spstocks.models.Coin;
import com.mg.spstocks.models.gold;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    String BASE_URL = "https://api.spstocks.com/";
    @GET("getCoins")
    Call<ApiResponse<List<Coin>>>  getCoins();

    @GET("getGolds")
    Call<ApiResponse<List<gold>>> getGold();

}
