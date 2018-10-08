package com.distribuidos.uagrm.android;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.distribuidos.uagrm.android.adapters.ModeloAdapter;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.ModeloResponse;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

public class ModeloActivity extends AppCompatActivity {

    private static final String TAG = "ModeloActivity";

    RecyclerView recyclerView;

    ApiService service;
    TokenManager tokenManager;
    Call<ModeloResponse> call;

    List<Modelo> listaModelos;


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

        getModelos();



    }

    private void cargarComponentes(){
        ModeloAdapter adapter = new ModeloAdapter(listaModelos);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }
}