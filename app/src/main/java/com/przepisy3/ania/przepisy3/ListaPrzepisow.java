package com.przepisy3.ania.przepisy3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;


//connected with activity view
public class ListaPrzepisow extends AppCompatActivity {

    private ListView mListView = null;
    private AdapterPrzepisow  mArrayAdapter = null;
    public ArrayList<Przepisy> data;
    MenuItem searchItem;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //comment: change main activity header color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F03861"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        //comment: Change font of main action bar
        SpannableString s = new SpannableString("Przepisy");
        s.setSpan(new TypefaceSpan(this, "GreatVibes-Regular.otf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);

        //comment: other things
        setContentView(R.layout.activity_lista_przepisow);

      //  mPrzepisySearchResult=mPrzepisy;

        mListView = (ListView) findViewById(R.id.lista_przepisow);
        data = new ArrayList<Przepisy>();

        data.add(new Przepisy("Szarlotka", "szarlotka", "szarlotka-prod"));
        data.add(new Przepisy("Beza Pawlowa", "beza", "beza-prod"));
        data.add(new Przepisy("Z budyniem truskawkami pod beza", "budyniowe", "budyniowe-prod"));
        data.add(new Przepisy("Sernik z musem czekoladowym", "mus", "mus-prod"));
        data.add(new Przepisy("Babka", "babka", "babka-prod"));
        data.add(new Przepisy("Mufiny", "mufiny", "mufiny-prod"));
        data.add(new Przepisy("Slonecznikowiec", "slonecznikowiec", "slonecznikowiec-prod"));


        mArrayAdapter = new AdapterPrzepisow(this, data);
        mArrayAdapter.sort(new Comparator<Przepisy>() {
            @Override
            public int compare(Przepisy lhs, Przepisy rhs) {
                return lhs.mTytul.compareTo(rhs.mTytul);
            }
        });


        if(mListView!=null){
            mListView.setAdapter(mArrayAdapter);
        }

        //comment: action when click on row
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentOpisuPrzepisow = new Intent(ListaPrzepisow.this,OpisPrzepisu.class);
               // String clickedTitle = mPrzepisy[position].mTytul;//!
              //  String clickedIdentifier = mPrzepisy[position].mIdentyfikator;//!
              //  String clickedProdukty = mPrzepisy[position].mProdukty;//!
                intentOpisuPrzepisow.putExtra("identyfikator",data.get(position).getmIdentyfikator());
                intentOpisuPrzepisow.putExtra("produkty",data.get(position).getmProdukty());
                intentOpisuPrzepisow.putExtra("nazwa",data.get(position).getmTytul());
                startActivity(intentOpisuPrzepisow);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Wpisz nazwę");


        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                mArrayAdapter.filter(searchQuery.toString().trim());
                mListView.invalidate();
                return true;
            }
        });

        return true;
    }


}