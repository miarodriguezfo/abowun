package com.example.miguelrodriguez.abowun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.miguelrodriguez.abowun.*;

/**
 * Created by Hogar on 27/10/2015.
 */
public class ListaAves extends Activity{

    private ListView mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_aves);

        mList = (ListView) findViewById(R.id.listaAves);//en donde va a mostrar la lista
        String[] ingredientes = Ave.obtenerNombresDeLasSeleccionadas();// en donde va a guardar la lista localmente
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.lista_item, R.id.label, ingredientes);// para usar lo que sigue
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) { // que hacer cuando se selecciona un elemento de la lista
                // selected item
                String nombre = ((TextView) view).getText().toString();

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), InfoAve.class); // a donde lo manda
                // sending data to new activity
                i.putExtra("ave", nombre);
                startActivity(i); // mandarlo alla
            }

        });
    }
}
