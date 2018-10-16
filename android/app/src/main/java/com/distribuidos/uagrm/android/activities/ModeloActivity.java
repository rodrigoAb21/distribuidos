package com.distribuidos.uagrm.android.activities;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.adapters.CabeceraAdapter;
import com.distribuidos.uagrm.android.entities.Cabecera;
import com.distribuidos.uagrm.android.helpers.TokenManager;
import com.distribuidos.uagrm.android.responses.CabeceraResponse;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;


public class ModeloActivity extends AppCompatActivity {

    private static final String TAG = "ModeloActivity";

    RecyclerView recyclerView;
    ApiService service;
    TokenManager tokenManager;
    Call<CabeceraResponse> call;
    List<Cabecera> listaCabeceras;
    Call<String> callLogout;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_logout:
                logout();
                return true;

            case R.id.item_menu_refresh:
                getModelos();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarComponentes(){

        CabeceraAdapter adapter = new CabeceraAdapter(listaCabeceras);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ModeloActivity.this, FormularioActivity.class);
                intent.putExtra("id", "" + listaCabeceras.get(recyclerView.getChildAdapterPosition(view)).getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

    }


    private void getModelos(){
        call = service.modelos();
        listaCabeceras = new ArrayList<>();
        call.enqueue(new Callback<CabeceraResponse>() {
            @Override
            public void onResponse(Call<CabeceraResponse> call, Response<CabeceraResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){
                    listaCabeceras = response.body().getData();
                    cargarComponentes();
                }else {
                    tokenManager.deleteToken();
                    startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
                    finish();

                }
            }

            @Override
            public void onFailure(Call<CabeceraResponse> call, Throwable t) {
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


    private void logout(){
        callLogout = service.logout();
        callLogout.enqueue(new Callback<String>() {
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


}