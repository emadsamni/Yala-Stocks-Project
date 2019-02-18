package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.adapters.Coinadapter;
import com.example.myapplication.adapters.Goldadapter;
import com.example.myapplication.apiClasses.Api;
import com.example.myapplication.apiClasses.ApiClient;
import com.example.myapplication.apiClasses.ApiResponse;
import com.example.myapplication.models.Coin;
import com.example.myapplication.models.gold;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private  RecyclerView myRecyclerView;
    private Coinadapter mAdapter;
    private Goldadapter mAdapter2;
    private ArrayList<Coin> coinList;
    private  ArrayList<gold> goldList;
    ProgressDialog progressDialog;
    TextView textView;
    Toolbar toolbar ;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    showCoins();
                    return true;
                case R.id.navigation_dashboard:
                    showGold();
                    return true;

            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




        progressDialog =ProgressDialog.getInstance();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        myRecyclerView =(RecyclerView)this.findViewById(R.id.coinrec);
        coinList= new ArrayList<Coin>();
        goldList=new ArrayList<gold>();
        mAdapter =new Coinadapter(coinList,MainActivity.this);
        mAdapter2 =new Goldadapter(goldList,MainActivity.this);
        myRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager =new LinearLayoutManager( MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        progressDialog.show(this);
        getData();
    }

    public  void showCoins()
    {
       myRecyclerView.setAdapter(mAdapter);
       LinearLayoutManager layoutManager =new LinearLayoutManager( MainActivity.this);
       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       myRecyclerView.setLayoutManager(layoutManager);
    }

    public  void showGold()
    {
        myRecyclerView.setAdapter(mAdapter2);
        LinearLayoutManager layoutManager =new LinearLayoutManager( MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
    }
   private void getData() {
        Api apiService = ApiClient.getClient().create(Api.class);
        Call<ApiResponse<List<Coin>>> call = apiService.getCoins();
        call.enqueue(new Callback<ApiResponse<List<Coin>>>() {
         @Override
         public void onResponse(Call<ApiResponse<List<Coin>>> call, Response<ApiResponse<List<Coin>>> response) {
             List<Coin> temp = response.body().getData();
             for (int i =0;i<temp.size();i++)
             {
                 coinList.add(temp.get(i));
             }

             getGold();
         }
         @Override
         public void onFailure(Call<ApiResponse<List<Coin>>> call, Throwable t) {
             Toast.makeText(MainActivity.this, "Please Check Your Internet", Toast.LENGTH_SHORT).show();
         }
     });

    }
    public void getGold()
    {
        Api apiService = ApiClient.getClient().create(Api.class);
        apiService = ApiClient.getClient().create(Api.class);
        Call<ApiResponse<List<gold>>> call2 = apiService.getGold();
        call2.enqueue(new Callback<ApiResponse<List<gold>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<gold>>> call, Response<ApiResponse<List<gold>>> response) {
                List<gold> temp= response.body().getData();
                for (int i =0;i<temp.size();i++)
                {
                    goldList.add(temp.get(i));
                }
                progressDialog.cancel();
                showCoins();
            }
            @Override
            public void onFailure(Call<ApiResponse<List<gold>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please Check Your Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
