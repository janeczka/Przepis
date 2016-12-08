package com.przepisy3.ania.przepisy3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ania on 2016-05-17.
 */
public class AdapterPrzepisow  extends ArrayAdapter<Przepisy> {
    Context mContext;
    //int mResourceId;
    //Przepisy mPrzepisy[]=null;
    //Przepisy mPrzepisyWynikSearcha[]=null;
    //Przepisy mTemporaryPrzepisy[]=null;
    static List<Przepisy> mData;
    static ArrayList<Przepisy> arrayList;


    public AdapterPrzepisow(Context context, ArrayList<Przepisy> data) {
        super(context, R.layout.lista_szablon);
        this.mData = data;
        this.mContext = context;
        arrayList=new ArrayList<>();
        arrayList.addAll(data);
    }

    @Override
    public int getCount() {return  mData.size();}

    @Override
    public Przepisy getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        View row = convertView;
        PlaceHolder placeHolder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.lista_szablon, null);
            placeHolder = new PlaceHolder();

            placeHolder.tytulPrzepisu = (TextView) row.findViewById(R.id.row_text);
            placeHolder.miniaturkaPrzepisu = (ImageView) row.findViewById(R.id.row_icon); //Obrazek

            row.setTag(placeHolder);


        } else {
            placeHolder = (PlaceHolder) row.getTag();
        }


        Przepisy przepis = mData.get(position);




        placeHolder.tytulPrzepisu.setText(przepis.mTytul);

        //Comment: Assign apropriate image by string name
        int id = mContext.getResources().getIdentifier(przepis.mIdentyfikator, "drawable", mContext.getPackageName());
        placeHolder.miniaturkaPrzepisu.setImageResource(id);
        return row;
    }

    private class PlaceHolder {
        TextView tytulPrzepisu;
        ImageView miniaturkaPrzepisu;
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mData.clear();
        if (charText.length() == 0) {
            mData.addAll(arrayList);
        } else {
            for (Przepisy przepisyDetails : arrayList) {
                if (charText.length() != 0 && przepisyDetails.getmTytul().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mData.add(przepisyDetails);

                }
            }}

        notifyDataSetChanged();

    }




}
