package com.distribuidos.uagrm.android.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
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

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.adapters.EncuestaAdapter;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.Area;
import com.distribuidos.uagrm.android.entities.AsignacionLocal;
import com.distribuidos.uagrm.android.entities.Encuesta;
import com.distribuidos.uagrm.android.entities.EncuestaAPI;
import com.distribuidos.uagrm.android.entities.Encuestas;
import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.FichaAPI;
import com.distribuidos.uagrm.android.entities.Punto;
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
    List<Encuesta> finalizadas;
    RecyclerView recyclerView;
    ApiService service;
    retrofit2.Call<Void> call;
    Area area;


    private LocationManager locationManager;
    private LocationListener listener;



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

        iniciandoUbi();

        verUbicacion();

        dbHelper = new DBHelper(getApplicationContext());
        asignacionLocal = dbHelper.getAsignacion(id_local);
        area = new Gson().fromJson(asignacionLocal.getPuntos(), Area.class);
        cargarComponentes();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 10:
                verUbicacion();
                break;
            default:
                break;
        }
    }

    private void iniciandoUbi(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
    }



    private void cargarComponentes(){
        encuestas = dbHelper.getEncuestas(asignacionLocal.getAsignacion_id());

        EncuestaAdapter adapter = new EncuestaAdapter(encuestas);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_encuestas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarEncuesta(encuestas.get(recyclerView.getChildAdapterPosition(view)).getId());
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

    @SuppressLint("MissingPermission")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_btn_add:
                //noinspection MissingPermission
                Location pos = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (pos != null){
                    Log.w("UBICACION", pos.getLatitude() + "," + pos.getLongitude());
                    if (valido(area, pos)){
                        mostrarEncuesta(0, pos);
                    }else{
                        Toast.makeText(this, "Necesita estar dentro del area asignada", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                    Toast.makeText(this, "Nada choco", Toast.LENGTH_LONG).show();

                break;
            case R.id.menu_btn_area:
                Intent intento = new Intent(getApplicationContext(), AreaActivity.class);
                intento.putExtra("area", asignacionLocal.getPuntos());
                startActivity(intento);

                break;

            case R.id.menu_btn_enviar:
                enviar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean valido(Area areaAsignada, Location pos) {
        boolean inside = false;

        double x = pos.getLatitude();
        double y = pos.getLongitude();

        for (int i = 0,j = areaAsignada.getPuntos().size() - 1; i < areaAsignada.getPuntos().size(); j = i++) {
            double xi = areaAsignada.getPuntos().get(i).getLatitud();
            double yi = areaAsignada.getPuntos().get(i).getLongitud();

            double xj = areaAsignada.getPuntos().get(j).getLatitud();
            double yj = areaAsignada.getPuntos().get(j).getLongitud();

            boolean inter = ((yi > y) != (yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
            if(inter) inside = !inside;
        }

        return inside;
    }


    private void mostrarEncuesta(int encuesta_id, Location pos){
        Intent intent = new Intent(EncuestaActivity.this, FormularioActivity.class);
        intent.putExtra("json_local", asignacionLocal.getJson());
        intent.putExtra("id_local", id_local);
        intent.putExtra("encuesta_id", encuesta_id);
        intent.putExtra("latitud", pos.getLatitude());
        intent.putExtra("longitud", pos.getLongitude());
        startActivity(intent);
    }

    private void mostrarEncuesta(int encuesta_id){
        Intent intent = new Intent(EncuestaActivity.this, FormularioActivity.class);
        intent.putExtra("json_local", asignacionLocal.getJson());
        intent.putExtra("id_local", id_local);
        intent.putExtra("encuesta_id", encuesta_id);
        startActivity(intent);
    }



    private Encuestas getEncuestasFinalizadas(){
        finalizadas = dbHelper.getEncuestasFinalizadas(asignacionLocal.getAsignacion_id());
        List<EncuestaAPI> encuestasAPI = new ArrayList<>();

        for (Encuesta enc : finalizadas){
            EncuestaAPI encuestaAPI = new EncuestaAPI();
            encuestaAPI.setId(enc.getId());
            encuestaAPI.setFecha(enc.getFecha());
            encuestaAPI.setEstado(enc.getEstado());
            encuestaAPI.setLatitud(enc.getLatitud());
            encuestaAPI.setLongitud(enc.getLongitud());
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

        return ee;
    }


    private void enviar(){


        call = service.enviarEncuestas(getEncuestasFinalizadas());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                Log.w("RESPUESTA", response.toString());
                actualizarEstados();
                cargarComponentes();
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                Log.w("SSS_ERROR", t.getMessage());
            }
        });

    }

    private void actualizarEstados(){
        for (Encuesta encuesta : finalizadas){
            dbHelper.updateEncuesta(encuesta.getId(), "Enviada");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }

    private void verUbicacion() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won'textView execute IF permissions are not allowed, because in the line above there is return statement.
        locationManager.requestLocationUpdates("gps", 2000, 0, listener);


    }

}
