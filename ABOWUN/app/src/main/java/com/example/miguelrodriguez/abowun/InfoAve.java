package com.example.miguelrodriguez.abowun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//import static com.example.miguelrodriguez.abowun.*;

/**
 * Created by Hogar on 27/10/2015.
 */
public class InfoAve extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_ave);

        Intent i = getIntent();
        String nombre = i.getStringExtra("ave");
        Ave ave = Ave.mostrarAve(nombre);

        if(ave!=null){
            TextView txtNombreComun= (TextView)findViewById(R.id.txtNombreComun);//mirar donde se va a poner el nombre
            txtNombreComun.setText(ave.getNombreComun());//poner el nombre

            TextView txtNombreCientifico= (TextView)findViewById(R.id.txtNombreCientifico);
            txtNombreCientifico.setText(ave.getNombreCientifico());

            TextView txtEspecie= (TextView)findViewById(R.id.txtEspecie);
            txtEspecie.setText(ave.getEspecie());

            TextView txtCaracteristicas= (TextView)findViewById(R.id.txtCaracteristicas);
            txtCaracteristicas.setText(ave.getCaracteristicas());

            TextView txtDatoCurioso= (TextView)findViewById(R.id.txtDatoCurioso);
            txtDatoCurioso.setText(ave.getDatoCurioso());
        }
    }
}
