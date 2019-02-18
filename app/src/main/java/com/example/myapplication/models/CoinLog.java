package com.example.myapplication.models;

public class CoinLog {
    private  int  buy;
    private  int sell;

    public CoinLog(int buy, int sell) {
        this.buy = buy;
        this.sell = sell;
    }

    public int getBuy() {
        return buy;
    }

    public int getSell() {
        return sell;
    }
}
