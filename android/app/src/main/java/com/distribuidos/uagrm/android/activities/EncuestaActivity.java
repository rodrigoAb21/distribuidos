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
import com.distribuidos.uagrm.android.adapters.EncuestaAdapter;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.Asignacion;
import com.distribuidos.uagrm.android.entities.AsignacionLocal;
import com.distribuidos.uagrm.android.entities.Encuesta;
import com.distribuidos.uagrm.android.helpers.TokenManager;

import java.util.List;


public class EncuestaActivity extends AppCompatActivity {

    TokenManager tokenManager;

    int id_local;
    AsignacionLocal asignacionLocal;
    DBHelper dbHelper;
    List<Encuesta> encuestas;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);


        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(EncuestaActivity.this, LoginActivity.class));
            finish();
        }


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


}
