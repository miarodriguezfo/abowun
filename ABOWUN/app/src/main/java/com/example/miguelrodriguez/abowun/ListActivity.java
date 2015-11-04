package com.example.miguelrodriguez.abowun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Bird> birds;
    private ArrayList<String> res;
    private RecyclerView rv;
    private ViewGroup linearLayoutDetails;
    private static final int DURATION = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Resultados");


        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        res=(ArrayList<String>)getIntent().getExtras().get("respuesta");




        initializeData();
        initializeAdapter();

    }

    private void initializeData() {
        birds = new ArrayList<>();
        String n;
        Iterator<String> indexIterator = res.iterator();
        while (indexIterator.hasNext()) {
            n = indexIterator.next();

            birds.add(new Bird(
                    getResources().getString(getResources().getIdentifier("nombre_comun_" + n, "string", getPackageName())),
                    getResources().getString(getResources().getIdentifier("nombre_cientifico_" + n, "string", getPackageName())),
                    getResources().getIdentifier("photo_specie_" + n, "drawable", getPackageName()),
                    getResources().getString(getResources().getIdentifier("test", "string", getPackageName()))));
        }

    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(birds);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + rv.getChildPosition(v));
                toggleDetails(v);
            }
        });
        rv.setAdapter(adapter);

    }

    public void toggleDetails(View view) {
        System.out.println("holaaaaaaaaaaaaaaaaa");
        linearLayoutDetails = (ViewGroup) view.findViewById(R.id.linearLayoutDetails);
        if (linearLayoutDetails.getVisibility() == View.GONE) {
            ExpandAndCollapseViewUtil.expand(linearLayoutDetails, DURATION);
        } else {
            ExpandAndCollapseViewUtil.collapse(linearLayoutDetails, DURATION);
        }
    }
}
