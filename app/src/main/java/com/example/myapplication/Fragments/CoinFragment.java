package com.example.myapplication.Fragments;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.Coinadapter;
import com.example.myapplication.models.Coin;

import java.util.List;

public class CoinFragment extends Fragment {
    private static final String TAG = "CoinFragment";
    List<Coin> coinList;
    private RecyclerView myRecyclerView;
    private Coinadapter mAdapter;

    public CoinFragment() {
    }

    @Nullable
    @Override

    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.coins_fragment, container,false);
        Log.d("TSTS","entered");
        coinList= ((MainActivity)getActivity()).getCoinList();
        myRecyclerView =(RecyclerView) view.findViewById(R.id.coinrec);
        mAdapter =new Coinadapter(coinList,getActivity());
        myRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);

        return view;

    }
}
