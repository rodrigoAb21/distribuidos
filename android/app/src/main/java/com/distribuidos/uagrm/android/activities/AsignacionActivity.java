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

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.adapters.AsignacionAdapter;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.Asignacion;
import com.distribuidos.uagrm.android.entities.AsignacionLocal;
import com.distribuidos.uagrm.android.helpers.TokenManager;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;
import com.distribuidos.uagrm.android.responses.AsignacionResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsignacionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Call<String> call_logout;
    Call<AsignacionResponse> call_asignacion;
    List<AsignacionLocal> asignacionesLocales;
    List<Asignacion> asignaciones;
    ApiService service;
    TokenManager tokenManager;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(AsignacionActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        dbHelper = new DBHelper(getApplicationContext());

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
                break;

            case R.id.item_menu_sync:
                getAsignaciones();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void cargarComponentes(){
        asignacionesLocales = dbHelper.getAsignaciones();

        AsignacionAdapter adapter = new AsignacionAdapter(asignacionesLocales);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_asignaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AsignacionActivity.this, EncuestaActivity.class);
                intent.putExtra("id_local", asignacionesLocales.get(recyclerView.getChildAdapterPosition(view)).getId());
                intent.putExtra("json_local", asignacionesLocales.get(recyclerView.getChildAdapterPosition(view)).getJson());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void getAsignaciones(){
        call_asignacion = service.asignaciones();
        asignaciones = new ArrayList<>();
        call_asignacion.enqueue(new Callback<AsignacionResponse>() {
            @Override
            public void onResponse(Call<AsignacionResponse> call, Response<AsignacionResponse> response) {
                Log.w("llego ok: ", ""+response);
                if (response.isSuccessful()){
                    asignaciones = response.body().getData();
                    sincronizar(asignaciones);
                }else{
                    tokenManager.deleteToken();
                    startActivity(new Intent(AsignacionActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AsignacionResponse> call, Throwable t) {
                Log.w("onFailure: ", t.getMessage());
            }
        });
    }

    private void sincronizar(List<Asignacion>  apiList) {
        asignacionesLocales = dbHelper.getAsignaciones();
        List<Integer> idsLocal = new ArrayList<>();
        for (AsignacionLocal asignacionLocal: asignacionesLocales){
            idsLocal.add(asignacionLocal.getAsignacion_id());
        }

        eliminar(apiList, idsLocal);
        agregar(apiList, idsLocal);

        cargarComponentes();
    }

    private void agregar(List<Asignacion> apiList, List<Integer> idsLocal) {
        List<Integer> idsApi = new ArrayList<>();
        for (Asignacion asignacion : apiList){
            idsApi.add(asignacion.getId());
        }
        for (int i = 0; i < idsApi.size(); i++){
            if (!idsLocal.contains(idsApi.get(i))){
                AsignacionLocal asignacionLocal = new AsignacionLocal();

                asignacionLocal.setAsignacion_id(apiList.get(i).getId());
                asignacionLocal.setModelo(apiList.get(i).getModelo().getNombre());
                asignacionLocal.setArea(apiList.get(i).getArea().getNombre());
                asignacionLocal.setCantidad(apiList.get(i).getCantidad());
                asignacionLocal.setDescripcion(apiList.get(i).getModelo().getDescripcion());
                asignacionLocal.setJson("" + new Gson().toJson(apiList.get(i)));

                dbHelper.addAsignacion(asignacionLocal);
            }

        }
    }

    private void eliminar(List<Asignacion> apiList, List<Integer> idsLocal) {
        List<Integer> idsApi = new ArrayList<>();
        for (Asignacion asignacion : apiList){
            idsApi.add(asignacion.getId());
        }

        for (Integer i : idsLocal){
            if (!idsApi.contains(i)){
                dbHelper.deleteAsignacion(i);
            }
        }
    }

    private void logout(){
        call_logout = service.logout();
        call_logout.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                tokenManager.deleteToken();
                startActivity(new Intent(AsignacionActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w("Log-out FAIL", t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call_asignacion != null){
            call_asignacion.cancel();
            call_asignacion = null;
        }
    }
}
