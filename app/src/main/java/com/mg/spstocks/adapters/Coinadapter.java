package com.mg.spstocks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mg.spstocks.R;
import com.mg.spstocks.models.Coin;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import static com.mg.spstocks.R.*;

public class Coinadapter extends RecyclerView.Adapter<Coinadapter.MyViewHolder>{

      private LayoutInflater inflater;
      List<Coin> data = Collections.emptyList();
      Context context;

    public Coinadapter(List<Coin> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= inflater.inflate(layout.coins_item,viewGroup ,false);
       MyViewHolder holder= new MyViewHolder(view);
       return holder;

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
       Coin current= data.get(i);
        myViewHolder.coinName.setText(current.getCoin_name());
        myViewHolder.newCoinSell.setText(current.getLog().get(0).getSell()+" S.P");
        myViewHolder.newCoinBuy.setText(  current.getLog().get(0).getBuy()+" S.P");
        myViewHolder.oldCoinSell.setText(current.getLog().get(1).getSell()+" S.P");
        myViewHolder.oldCoinBuy.setText(  current.getLog().get(1).getBuy()+" S.P");
        if (current.getLog().get(0).getSell() > current.getLog().get(1).getSell())
        {
            myViewHolder.sellChangeIcon.setImageResource(drawable.ic_trending_up_black_24dp);
        }
        else
        {
            if (current.getLog().get(0).getSell() < current.getLog().get(1).getSell())
            {
                myViewHolder.sellChangeIcon.setImageResource(drawable.ic_trending_down_seondary_24dp);
            }
            else
            {
                myViewHolder.sellChangeIcon.setImageResource(drawable.ic_trending_flat_black_24dp);
            }

        }
        if (current.getLog().get(0).getBuy() > current.getLog().get(1).getBuy())
        {
            myViewHolder.buyChangeIcon.setImageResource(drawable.ic_trending_up_black_24dp);
        }
        else
        {
            if (current.getLog().get(0).getBuy() < current.getLog().get(1).getBuy())
            {
                myViewHolder.buyChangeIcon.setImageResource(drawable.ic_trending_down_seondary_24dp);
            }
            else
            {
                myViewHolder.buyChangeIcon.setImageResource(drawable.ic_trending_flat_black_24dp);
            }

        }

        String path= "https://api2.yala-shop.com/public/image/"+ current.getImage();
        path = path.replaceAll("\\\\", "/");
        Picasso.with(context).load(path).into(myViewHolder.coinIcon);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView coinIcon;
        ImageView sellChangeIcon;
        ImageView buyChangeIcon;
        TextView  newCoinBuy;
        TextView  oldCoinBuy;
        TextView newCoinSell;
        TextView oldCoinSell;
        TextView coinName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             coinIcon= (ImageView) itemView.findViewById(id.coin_id);
             sellChangeIcon= (ImageView) itemView.findViewById(id.sellchange);
             buyChangeIcon= (ImageView) itemView.findViewById(id.buyChange);
             newCoinBuy   =(TextView) itemView.findViewById(id.new_coin_buy);
             oldCoinBuy= (TextView) itemView.findViewById(id.old_coin_buy);
             newCoinSell = (TextView) itemView.findViewById(id.new_coin_sell);
             oldCoinSell = (TextView) itemView.findViewById(id.old_coin_sell);
             coinName = (TextView)itemView.findViewById(id.coin_name);

        }
    }
}
