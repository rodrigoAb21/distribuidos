package com.distribuidos.uagrm.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.Asignacion;
import com.distribuidos.uagrm.android.entities.Campo;
import com.distribuidos.uagrm.android.entities.Cerrada;
import com.distribuidos.uagrm.android.entities.Encuesta;
import com.distribuidos.uagrm.android.entities.EncuestaAPI;
import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.FichaAPI;
import com.distribuidos.uagrm.android.entities.MLocal;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.Opcion;
import com.distribuidos.uagrm.android.entities.Otro;
import com.distribuidos.uagrm.android.entities.Pregunta;
import com.distribuidos.uagrm.android.entities.RespAbierta;
import com.distribuidos.uagrm.android.entities.RespCerrada;
import com.distribuidos.uagrm.android.entities.RespOtro;
import com.distribuidos.uagrm.android.helpers.GeneradorEncuesta;
import com.distribuidos.uagrm.android.helpers.TokenManager;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FormularioActivity extends AppCompatActivity {

//
    TokenManager tokenManager;
    String json_local;
    int id_local;
    Asignacion asignacion;
    GeneradorEncuesta generador;
    DBHelper dbHelper;
    private View view;
    int encuesta_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.activity_formulario, null));
        setContentView(getView());

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(FormularioActivity.this, LoginActivity.class));
            finish();
        }

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            json_local = bundle.getString("json_local");
            id_local = bundle.getInt("id_local");
            this.encuesta_id = bundle.getInt("encuesta_id");
        }else {
            finish();
        }

        dbHelper = new DBHelper(getApplicationContext());


        getModelo();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.encuesta_id == 0){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_formulario, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.menu_btn_save:
                guardar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardar() {
        generador.guardarUltimo();

        if (cumpleObligatorios(asignacion.getModelo(), getEncuestaRespuesta(asignacion.getId()))){
            Encuesta encuesta2 = dbHelper.getLastEncuesta(asignacion.getId());
            dbHelper.updateEncuesta(encuesta2.getId(), "Finalizada");

            Intent intent = new Intent(FormularioActivity.this, EncuestaActivity.class);
            intent.putExtra("id_local", id_local);
            startActivity(intent);
            finish();
        }

    }


    private View getView() {
        return view;
    }

    private void setView(View view) {
        this.view = view;
    }


    private void getModelo() {
        asignacion = new Gson().fromJson(json_local,Asignacion.class);
        generarVista(asignacion.getModelo(), this.encuesta_id);

    }


    private void generarVista(Modelo modelo, int id_encuesta){
        generador = new GeneradorEncuesta(getApplicationContext(), getView());
            Encuesta encuesta;
        if (id_encuesta == 0){
             encuesta = dbHelper.getLastEncuesta(asignacion.getId());
            if (encuesta == null){
                encuesta = new Encuesta();

                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                String fecha = df.format(date);

                encuesta.setFecha(fecha);
                encuesta.setEstado("En proceso");
                encuesta.setAsignacion_id(asignacion.getId());

                long id = dbHelper.addEncuesta(encuesta);
                encuesta.setId((int) id);
            }
            generador.generarVista(modelo, encuesta.getId());
            generador.cargarUltimo(encuesta.getId());
        } else {
            generador.generarVistaBloqueada(modelo, id_encuesta);
            generador.cargarUltimo(id_encuesta);
        }


    }


    private EncuestaAPI getEncuestaRespuesta(int asignacion_id){
        EncuestaAPI encuestaAPI = new EncuestaAPI();
        Encuesta enc = dbHelper.getLastEncuesta(asignacion_id);

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

        return encuestaAPI;
    }

    private boolean cumpleCerrada(List<Integer> opciones, List<Integer> otros, FichaAPI fichaAPI){
        // primero revisamos las opciones

        List<Integer> respOpc = new ArrayList<>();
        for (RespCerrada respCerrada : fichaAPI.getRespCerradas()){
            respOpc.add(respCerrada.getOpcion_id());
        }

        for (int opc : opciones){
            if (respOpc.contains(opc))
                return true;
        }

        // Si no pillo revisamos los otros
        List<Integer> respOtro = new ArrayList<>();
        for (RespOtro resp : fichaAPI.getRespOtros()){
            respOtro.add(resp.getOtro_id());
        }

        for (int otr : otros){
            if (respOtro.contains(otr))
                return true;
        }

        return false;
    }

    private boolean cumpleAbiertas(List<Integer> camposObli, FichaAPI fichaAPI){
        if (camposObli.size() == 0)
            return true;

        if (camposObli.size() > 0 && fichaAPI == null)
            return false;

        List<Integer> campos = new ArrayList<>();
        for (RespAbierta abierta : fichaAPI.getRespAbiertas()){
            campos.add(abierta.getCampo_id());
        }

        for (int campo : camposObli){
            if (!campos.contains(campo))
                return false;
        }

        return true;
    }

    private List<Integer> obtenerIdsCampos(List<Campo> campos){
        List<Integer> ids = new ArrayList<>();

        for (Campo campo : campos){
            if (campo.getObligatorio() == 1)
                ids.add(campo.getId());
        }

        return ids;
    }

    private List<Integer> obtenerIdsOpciones(Cerrada cerrada){
        List<Integer> ids = new ArrayList<>();

        for (Opcion opcion : cerrada.getOpciones()){
            ids.add(opcion.getId());
        }

        return ids;
    }

    private List<Integer> obtenerIdsOtros(Cerrada cerrada){
        List<Integer> ids = new ArrayList<>();

        for (Otro otro : cerrada.getOtros()){
            ids.add(otro.getId());
        }

        return ids;
    }

    private boolean cumpleObligatorios(Modelo modelo, EncuestaAPI encuestaAPI){
        for (Pregunta pregunta : modelo.getPreguntas()){
            FichaAPI fichaAPI = getFichaAPI(encuestaAPI.getFichas(), pregunta.getId());
            for (Cerrada cerrada : pregunta.getCerradas()){
                if (cerrada.isObligatoria() == 1){
                    if (fichaAPI == null){
                        Toast.makeText(this, "Por favor revise la pregunta: " + pregunta.getEnunciado(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if (!cumpleCerrada(obtenerIdsOpciones(cerrada), obtenerIdsOtros(cerrada), fichaAPI)){
                        Toast.makeText(this, "Por favor revise la pregunta: " + pregunta.getEnunciado(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            if(!cumpleAbiertas(obtenerIdsCampos(pregunta.getCampos()), fichaAPI)){
                Toast.makeText(this, "Por favor revise la pregunta: " + pregunta.getEnunciado(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


    private FichaAPI getFichaAPI(List<FichaAPI> fichaAPIS, int pregunta_id){
        for (FichaAPI fichaAPI : fichaAPIS){
            if (fichaAPI.getPregunta_id() == pregunta_id)
                return fichaAPI;
        }
        return null;
    }







}
