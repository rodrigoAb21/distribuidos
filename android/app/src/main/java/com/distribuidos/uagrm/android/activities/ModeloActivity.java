package com.distribuidos.uagrm.android.activities;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.entities.Campo;
import com.distribuidos.uagrm.android.entities.Cerrada;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.Modelo_cabecera;
import com.distribuidos.uagrm.android.entities.Opcion;
import com.distribuidos.uagrm.android.entities.Pregunta;
import com.distribuidos.uagrm.android.helpers.TokenManager;
import com.distribuidos.uagrm.android.adapters.ModeloAdapter;
import com.distribuidos.uagrm.android.responses.Modelo1Response;
import com.distribuidos.uagrm.android.responses.ModeloResponse;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;


public class ModeloActivity extends AppCompatActivity {

    private static final String TAG = "ModeloActivity";

    RecyclerView recyclerView;
    Button btn_logout;

    ApiService service;
    TokenManager tokenManager;
    Call<ModeloResponse> call;

    Call<String> c2;
    List<Modelo_cabecera> listaModelos;

    Call<Modelo1Response> modeloCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelo);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        //getModelos();

        getModelo();


    }

    private void getModelo() {
        modeloCall = service.modelo();
        modeloCall.enqueue(new Callback<Modelo1Response>() {
            @Override
            public void onResponse(Call<Modelo1Response> call, Response<Modelo1Response> response) {
                Log.w(TAG, "onResponse: " + response );
                if (response.isSuccessful()){
                    Modelo modelo = response.body().getData();
                    generarVista(modelo);
                }
            }

            @Override
            public void onFailure(Call<Modelo1Response> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    private void cargarComponentes(){
        /*

        ModeloAdapter adapter = new ModeloAdapter(listaModelos);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        */

    }


    private void getModelos(){
        listaModelos = new ArrayList<>();
        call = service.modelos();
        call.enqueue(new Callback<ModeloResponse>() {
            @Override
            public void onResponse(Call<ModeloResponse> call, Response<ModeloResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){
                    listaModelos = response.body().getData();
                    cargarComponentes();
                }else {
                    tokenManager.deleteToken();
                    startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
                    finish();

                }
            }

            @Override
            public void onFailure(Call<ModeloResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    private void logout(){
        c2 = service.logout();
        c2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.w("log-out", "onResponse: " + response );
                if (response.isSuccessful()){
                    tokenManager.deleteToken();
                    startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w("log-out", "onFailure: " + t.getMessage() );
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }


    private void generarVista(Modelo modelo){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        for(Pregunta pregunta : modelo.getPreguntas()){

            //Escribimos el enunciado de la pregunta en negritas
            TextView textView = new TextView(getApplicationContext());
            textView.setId(pregunta.getId());
            textView.setText(pregunta.getEnunciado());
            textView.setTypeface(null, Typeface.BOLD);
            linearLayout.addView(textView);

            for (Cerrada cerrada : pregunta.getCerradas()){

                // Verificamos el tipo de seleccion
                if (cerrada.getTipoSeleccion().equals("Multiple")){

                    //Creamos el checkbox
                    for (Opcion opcion : cerrada.getOpciones()){

                        CheckBox checkBox = new CheckBox(getApplicationContext());
                        checkBox.setId(opcion.getId());
                        checkBox.setText(opcion.getTexto());
                        linearLayout.addView(checkBox);

                    }

                }else{

                    //Creamos el radioGroup
                    RadioGroup radioGroup = new RadioGroup(getApplicationContext());
                    radioGroup.setId(cerrada.getId());
                    radioGroup.setOrientation(RadioGroup.VERTICAL);

                    //Creamos los radioButtons
                    for (Opcion opcion : cerrada.getOpciones()){

                        RadioButton radioButton = new RadioButton(getApplicationContext());
                        radioButton.setId(opcion.getId());
                        radioButton.setText(opcion.getTexto());
                        radioGroup.addView(radioButton);

                    }

                    linearLayout.addView(radioGroup);

                }

            }
            for (Campo campo : pregunta.getCampos()){
                // Agregando la etiqueta del campo
                textView = new TextView(getApplicationContext());
                textView.setId(campo.getId());
                textView.setText(campo.getEtiqueta());
                linearLayout.addView(textView);

                //Agregando el editText que sera el input
                EditText editText = new EditText(getApplicationContext());
                editText.setId(campo.getId());
                linearLayout.addView(editText);

            }

            ImageView divider = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams lp =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(1, 50, 1, 50);
            divider.setLayoutParams(lp);
            divider.setBackgroundColor(Color.WHITE);
            linearLayout.addView(divider);



        }

    }
}