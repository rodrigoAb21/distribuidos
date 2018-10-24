package com.distribuidos.uagrm.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.adapters.EncuestaAdapter;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.Asignacion;
import com.distribuidos.uagrm.android.entities.AsignacionLocal;
import com.distribuidos.uagrm.android.entities.Encuesta;
import com.distribuidos.uagrm.android.entities.EncuestaAPI;
import com.distribuidos.uagrm.android.entities.Encuestas;
import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.FichaAPI;
import com.distribuidos.uagrm.android.helpers.TokenManager;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


public class EncuestaActivity extends AppCompatActivity {

    TokenManager tokenManager;

    int id_local;
    AsignacionLocal asignacionLocal;
    DBHelper dbHelper;
    List<Encuesta> encuestas;
    RecyclerView recyclerView;
    ApiService service;
    retrofit2.Call<Void> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);


        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(EncuestaActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
          id_local = bundle.getInt("id_local");
        }else {
            finish();
        }

        dbHelper = new DBHelper(getApplicationContext());
        asignacionLocal = dbHelper.getAsignacion(id_local);
        cargarComponentes();
    }


    private void cargarComponentes(){
        encuestas = dbHelper.getEncuestas(asignacionLocal.getAsignacion_id());

        EncuestaAdapter adapter = new EncuestaAdapter(encuestas);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_encuestas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_encuestas, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_btn_add:
                nuevaEncuesta();
                break;

            case R.id.menu_btn_enviar:
                enviar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void nuevaEncuesta(){
        Intent intent = new Intent(EncuestaActivity.this, FormularioActivity.class);
        intent.putExtra("json_local", asignacionLocal.getJson());
        intent.putExtra("id_local", id_local);
        startActivity(intent);
    }



    private Encuestas getEncuestasFinalizadas(){
        List<EncuestaAPI> encuestasAPI = new ArrayList<>();

        for (Encuesta enc : encuestas){
            EncuestaAPI encuestaAPI = new EncuestaAPI();
            encuestaAPI.setId(enc.getId());
            encuestaAPI.setFecha(enc.getFecha());
            encuestaAPI.setEstado(enc.getEstado());
            encuestaAPI.setAsignacion_id(enc.getAsignacion_id());

            List<FichaAPI> fichasAPI = new ArrayList<>();

            List<Ficha> fichas = dbHelper.getFichas(enc.getId());

            for (Ficha ficha : fichas){
                FichaAPI fichaAPI = new FichaAPI();
                fichaAPI.setId(ficha.getId());
                fichaAPI.setEncuesta_id(ficha.getEncuesta_id());
                fichaAPI.setPregunta_id(ficha.getPregunta_id());
                fichaAPI.setRespAbiertas(dbHelper.getRespAbiertas(ficha.getId()));
                fichaAPI.setRespCerradas(dbHelper.getRespCerradas(ficha.getId()));
                fichaAPI.setRespOtros(dbHelper.getRespOtros(ficha.getId()));

                fichasAPI.add(fichaAPI);
            }

            encuestaAPI.setFichas(fichasAPI);

            encuestasAPI.add(encuestaAPI);

        }

        Encuestas ee = new Encuestas();
        ee.setEncuestas(encuestasAPI);
//        Log.w("GSON_API", "" + new Gson().toJson(ee));

        return ee;
    }


    private void enviar(){


        call = service.enviarEncuestas(getEncuestasFinalizadas());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                Log.w("SSS", response.toString() );
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                Log.w("SSS_ERROR", t.getMessage());
            }
        });

    }





}
