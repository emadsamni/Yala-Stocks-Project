package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.Goldadapter;
import com.example.myapplication.models.gold;

import java.util.List;

public class GoldFragment extends Fragment {
    List<gold> goldList;
    private RecyclerView myRecyclerView;
    private Goldadapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.gold_fragment, null);
        goldList= ((MainActivity)getActivity()).getGoldList();
        myRecyclerView =(RecyclerView) view.findViewById(R.id.goldrec);
        mAdapter =new Goldadapter(goldList,getActivity());
        myRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);

        return view;

    }
}
