package com.distribuidos.uagrm.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.helpers.GeneradorEncuesta;
import com.distribuidos.uagrm.android.helpers.TokenManager;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;
import com.distribuidos.uagrm.android.responses.ModeloResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioActivity extends AppCompatActivity {

    ApiService service;
    TokenManager tokenManager;
    Call<ModeloResponse> call;
    String modelo_id;
    GeneradorEncuesta generador;
    private static final String TAG = "FormularioActivity";


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
          modelo_id = bundle.getString("id");
        }else {
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
        getModelo();
    }


    private void getModelo() {
        call = service.modelo(modelo_id);
        call.enqueue(new Callback<ModeloResponse>() {
            @Override
            public void onResponse(Call<ModeloResponse> call, Response<ModeloResponse> response) {
                Log.w(TAG, "onResponse: " + response );
                if (response.isSuccessful()){
                    Modelo modelo = response.body().getData();
                    generarVista(modelo);
                }
            }

            @Override
            public void onFailure(Call<ModeloResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }


    private void generarVista(Modelo modelo){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        generador = new GeneradorEncuesta(linearLayout, getApplicationContext());
        generador.generarVista(modelo);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }



}
