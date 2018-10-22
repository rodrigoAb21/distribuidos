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
import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.MLocal;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.RespAbierta;
import com.distribuidos.uagrm.android.helpers.GeneradorEncuesta;
import com.distribuidos.uagrm.android.helpers.TokenManager;

import com.google.gson.Gson;

import java.util.List;


public class FormularioActivity extends AppCompatActivity {

//
    TokenManager tokenManager;
    String json_local;
    Asignacion asignacion;
    GeneradorEncuesta generador;
    DBHelper dbHelper;
//    private static final String TAG = "FormularioActivity";
//    Ficha ficha;
//    private View view;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

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
//
//    private View getView() {
//        return view;
//    }
//
//    private void setView(View view) {
//        this.view = view;
//    }
//
//
    private void getModelo() {
        asignacion = new Gson().fromJson(json_local,Asignacion.class);
        generarVista(asignacion.getModelo());

    }


    private void generarVista(Modelo modelo){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        generador = new GeneradorEncuesta(linearLayout, getApplicationContext());
        generador.generarVista(modelo);
    }
//
//    private void getUltimaFicha(Ficha ficha){
//        if (ficha != null){
//            List<RespAbierta> abiertas = dbHelper.getRespAbiertas(ficha.getId());
//            if(abiertas.size() > 0){
//                cargarUltimo(abiertas);
//            }else{
//                Log.w("listaaaa", "Esta enviando vacio!!");
//            }
//        }
//    }
//
//
//    public void cargarUltimo(List<RespAbierta> abiertas){
//        for(RespAbierta abierta : abiertas){
////            int idDelEdit = getResources().getIdentifier(abierta.getId_view(), "id", getPackageName());
////            Log.w("listaaaa", ""+idDelEdit);
//
//
//            EditText editText = (EditText) getView().findViewWithTag(abierta.getTag());
//            editText.setText(abierta.getValor());
//        }
//     }
//

}
