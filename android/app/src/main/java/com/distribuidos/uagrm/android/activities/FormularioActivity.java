package com.distribuidos.uagrm.android.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.entities.Campo;
import com.distribuidos.uagrm.android.entities.Cerrada;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.Opcion;
import com.distribuidos.uagrm.android.entities.Pregunta;
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
                    Log.w(TAG, "Soy un mensaje de todo ok" );
                    Modelo modelo = response.body().getData();
                    Log.w(TAG, "Asigne el modelo parseado a la variable" );
                    generarVista(modelo);
                    Log.w(TAG, "Supuestamente me mostre!" );
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }



}
