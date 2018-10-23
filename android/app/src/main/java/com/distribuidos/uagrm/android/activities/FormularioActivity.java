package com.distribuidos.uagrm.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.Asignacion;
import com.distribuidos.uagrm.android.entities.Encuesta;
import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.MLocal;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.RespAbierta;
import com.distribuidos.uagrm.android.helpers.GeneradorEncuesta;
import com.distribuidos.uagrm.android.helpers.TokenManager;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FormularioActivity extends AppCompatActivity {

//
    TokenManager tokenManager;
    String json_local;
    Asignacion asignacion;
    GeneradorEncuesta generador;
    DBHelper dbHelper;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.activity_formulario, null));
        setContentView(getView());

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(FormularioActivity.this, LoginActivity.class));
            finish();
        }

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
          json_local = bundle.getString("json_local");
        }else {
            finish();
        }

        dbHelper = new DBHelper(getApplicationContext());

        getModelo();

    }

    private View getView() {
        return view;
    }

    private void setView(View view) {
        this.view = view;
    }


    private void getModelo() {
        asignacion = new Gson().fromJson(json_local,Asignacion.class);
        generarVista(asignacion.getModelo());

    }


    private void generarVista(Modelo modelo){
        generador = new GeneradorEncuesta(getApplicationContext(), getView());
        generador.generarVista(modelo);
        Encuesta encuesta = dbHelper.getLastEncuesta(asignacion.getId());

        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        String fecha = df.format(date);

        if (encuesta == null){
            encuesta = new Encuesta();
            encuesta.setFecha(fecha);
            encuesta.setEstado("En proceso");
            encuesta.setAsignacion_id(asignacion.getId());

            long id = dbHelper.addEncuesta(encuesta);
            encuesta.setId((int) id);
        }
    }

}
