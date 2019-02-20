package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.Fragments.CoinFragment;
import com.example.myapplication.Fragments.GoldFragment;
import com.example.myapplication.Fragments.TransferFragment;
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

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView mTextMessage;
    private  RecyclerView myRecyclerView;
    private Coinadapter mAdapter;
    private Goldadapter mAdapter2;
    public ArrayList<Coin> coinList;
    public   ArrayList<gold> goldList;
    ProgressDialog progressDialog;
    TextView textView;
    Toolbar toolbar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressDialog =ProgressDialog.getInstance();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        coinList= new ArrayList<Coin>();
        goldList=new ArrayList<gold>();
        progressDialog.show(this);
        getData();
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

                CoinFragment fragment = new CoinFragment();
                loadfragment(fragment);
            }
            @Override
            public void onFailure(Call<ApiResponse<List<gold>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please Check Your Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<gold> getGoldList() {  return  goldList;
    }

    public List<Coin> getCoinList() { return  coinList;
    }
    private  boolean loadfragment(Fragment fragment)
    {
        if (fragment  != null )
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fargment_container,fragment).commit();
                 return true;
        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment= null;
        switch (menuItem.getItemId())
        {
            case R.id.navigation_coins:
                fragment = new CoinFragment();

                break;
            case R.id.navigation_gold:
                fragment = new GoldFragment();

                break;
            case R.id.navigation_tranfer:
                fragment = new TransferFragment();
                break;
        }
        return loadfragment(fragment);
    }
}
