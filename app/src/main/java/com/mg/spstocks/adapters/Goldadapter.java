package com.mg.spstocks.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mg.spstocks.models.gold;
import com.mg.spstocks.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class Goldadapter extends RecyclerView.Adapter<Goldadapter.MyViewHolder>{
    @NonNull
    private LayoutInflater inflater;
    List<gold> data = Collections.emptyList();
    Context context;

    public Goldadapter(List<gold> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override

    public Goldadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.gold_item,viewGroup ,false);
        Goldadapter.MyViewHolder holder= new Goldadapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Goldadapter.MyViewHolder myViewHolder, int i) {
        gold current= data.get(i);
        myViewHolder.goldName.setText(current.getGold_name());
        myViewHolder.newGoldPrice.setText(current.getLog().get(0).getGold_price()+" "+ current.getGold_type().getName()+" ");
        myViewHolder.oldGoldPrice.setText(current.getLog().get(1).getGold_price()+" "+ current.getGold_type().getName()+" ");
       if (current.getLog().get(0).getGold_price() > current.getLog().get(1).getGold_price())
        {
            myViewHolder.goldChange.setImageResource(R.drawable.ic_trending_up_black_24dp);
        }
        else
        {
            if (current.getLog().get(0).getGold_price() < current.getLog().get(1).getGold_price())
            {
                myViewHolder.goldChange.setImageResource(R.drawable.ic_trending_down_seondary_24dp);
            }
            else
            {
                myViewHolder.goldChange.setImageResource(R.drawable.ic_trending_flat_black_24dp);
            }

        }
        String path= "https://api.spstocks.com/image/"+current.getImage();
        path = path.replaceAll("\\\\", "/");
        Picasso.with(context).load(path).into(myViewHolder.goldIcon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView goldIcon;
        ImageView goldChange;
        TextView  goldName;
        TextView  oldGoldPrice;
        TextView  newGoldPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            goldIcon= (ImageView) itemView.findViewById(R.id.gold_icon);
            goldChange= (ImageView) itemView.findViewById(R.id.goldchange);
            goldName =(TextView) itemView.findViewById(R.id.gold_name);
            oldGoldPrice = (TextView) itemView.findViewById(R.id.old_gold_price);
            newGoldPrice = (TextView) itemView.findViewById(R.id.new_gold_price);

        }
    }
}
