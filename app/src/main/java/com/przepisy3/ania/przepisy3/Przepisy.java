package com.przepisy3.ania.przepisy3;

/**
 * Created by Ania on 2016-05-17.
 */
public class Przepisy {

    public String mTytul;
    public String mIdentyfikator;
    public String mProdukty;

    public Przepisy(String tytul, String identyfikator, String produkty) {
        this.mTytul = tytul;
        this.mIdentyfikator = identyfikator;
        this.mProdukty = produkty;
    }

    public String getmTytul(){
        return mTytul;
    }

    public String getmIdentyfikator(){
        return mIdentyfikator;
    }

    public String getmProdukty(){
        return mProdukty;
    }
}
