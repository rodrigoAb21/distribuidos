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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.adapters.ModeloAdapter;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.MLocal;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.helpers.TokenManager;
import com.distribuidos.uagrm.android.responses.CabeceraResponse;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;
import com.distribuidos.uagrm.android.responses.ModelosResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class ModeloActivity extends AppCompatActivity {

    private static final String TAG = "ModeloActivity";

    RecyclerView recyclerView;
    ApiService service;
    TokenManager tokenManager;
    Call<ModelosResponse> call;
    List<MLocal> mLocals;
    List<Modelo> listaModelos;
    Call<String> callLogout;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelo);

        dbHelper = new DBHelper(getApplicationContext());
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
        cargarComponentes();

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
        mLocals = dbHelper.getModelos();
        ModeloAdapter adapter = new ModeloAdapter(mLocals);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ModeloActivity.this, FormularioActivity.class);
                Log.w("ID_ERROR", "IdsLocalRexyler: " + mLocals.get(recyclerView.getChildAdapterPosition(view)).getId_modelo());
                intent.putExtra("id", mLocals.get(recyclerView.getChildAdapterPosition(view)).getId_modelo());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

    }


    private void getModelos(){
        call = service.modelos();
        listaModelos = new ArrayList<>();
        call.enqueue(new Callback<ModelosResponse>() {
            @Override
            public void onResponse(Call<ModelosResponse> call, Response<ModelosResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){
                    listaModelos = response.body().getData();
                    actualizar(listaModelos);
                }else {
                    tokenManager.deleteToken();
                    startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ModelosResponse> call, Throwable t) {
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

    private void actualizar(List<Modelo> apiList){
        mLocals = dbHelper.getModelos();
        List<Integer> idsLocal = new ArrayList<>();
        for (MLocal mLocal : mLocals){
            if (mLocal.getEstado().equals("Activo")){
                Log.w("ID_ERROR", "IdsLocal: " + mLocal.getId_modelo() );
                idsLocal.add(mLocal.getId_modelo());

            }
        }

        eliminar(apiList, idsLocal);
        agregar(apiList, idsLocal);

        mLocals = dbHelper.getModelos();
        Log.w("BDLocal", "Cantidad: " + mLocals.size() );
    }

    private void agregar(List<Modelo> apiList, List<Integer> idsLocal) {
        List<Integer> idsApi = new ArrayList<>();
        for (Modelo modelo : apiList){
            idsApi.add(modelo.getId());
        }
        for (int i = 0; i < idsApi.size(); i++){
            if (!idsLocal.contains(idsApi.get(i))){
                MLocal mLocal = new MLocal();
                mLocal.setId_modelo(apiList.get(i).getId());
                mLocal.setNombre(apiList.get(i).getNombre());
                mLocal.setDescripcion(apiList.get(i).getDescripcion());
                mLocal.setEstado("Activo");
                mLocal.setJson("" + new Gson().toJson(apiList.get(i)));

                dbHelper.addModelo(mLocal);
                Log.w("BDLocal", "Se agrego a: 1" );
            }

        }
    }

    private void eliminar(List<Modelo> apiList, List<Integer> idsLocal) {
        List<Integer> idsApi = new ArrayList<>();
        for (Modelo modelo : apiList){
            idsApi.add(modelo.getId());
        }
        for (Integer i : idsLocal){
            if (!idsApi.contains(i)){
                dbHelper.deleteModelo(i);
                Log.w("BDLocal", "Se elimino a: 1" );
            }
        }
    }


}