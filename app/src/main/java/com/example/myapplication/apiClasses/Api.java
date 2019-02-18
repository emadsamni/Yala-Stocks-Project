package com.example.myapplication.apiClasses;

import com.example.myapplication.models.Coin;
import com.example.myapplication.models.gold;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    String BASE_URL = "https://api2.yala-shop.com/";
    @GET("getCoins")
    Call<ApiResponse<List<Coin>>>  getCoins();

    @GET("getGolds")
    Call<ApiResponse<List<gold>>> getGold();
}
