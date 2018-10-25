package com.distribuidos.uagrm.android.helpers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.db.DBHelper;
import com.distribuidos.uagrm.android.entities.Campo;
import com.distribuidos.uagrm.android.entities.Cerrada;
import com.distribuidos.uagrm.android.entities.Encuesta;
import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.Opcion;
import com.distribuidos.uagrm.android.entities.Otro;
import com.distribuidos.uagrm.android.entities.Pregunta;
import com.distribuidos.uagrm.android.entities.RespAbierta;
import com.distribuidos.uagrm.android.entities.RespCerrada;
import com.distribuidos.uagrm.android.entities.RespOtro;


import java.util.ArrayList;
import java.util.List;

public class GeneradorEncuesta {
    LinearLayout linearLayout; 
    Context context;
    DBHelper dbHelper;
    View view;
    String ultimoTag;


    public GeneradorEncuesta(Context context, View view) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        this.view = view;
        this.linearLayout = (LinearLayout) this.view.findViewById(R.id.linear_layout);;

    }

    public void generarVista(Modelo modelo, int encuesta_id){
        for(Pregunta pregunta : modelo.getPreguntas()){

            CardView cardView = new CardView(context);
            cardView.setUseCompatPadding(true);
            cardView.setContentPadding(30,30,30,30);
            cardView.setElevation(8);
            cardView.setRadius(10);
            linearLayout.addView(cardView);

            LinearLayout linearLayoutRepetido = new LinearLayout(context);
            linearLayoutRepetido.setOrientation(LinearLayout.VERTICAL);
            cardView.addView(linearLayoutRepetido);

            //Escribimos el enunciado de la pregunta en negritas
            generarEnunciado(pregunta, linearLayoutRepetido);

            for (Cerrada cerrada : pregunta.getCerradas()){
                // Verificamos el tipo de seleccion

                generarTituloSeccion(cerrada, linearLayoutRepetido);

                if (cerrada.getTipoSeleccion().equals("Multiple")){
                    //Creamos el checkbox
                    generarOpcionesMultiples(cerrada.getOpciones(),pregunta.getId(), encuesta_id, linearLayoutRepetido);
                }else{

                    //Creamos el radioGroup
                    generarOpcionesUnicas(cerrada.getId(), cerrada.getOpciones(),pregunta.getId(), encuesta_id, linearLayoutRepetido);
                }

                generarOtros(cerrada.getOtros(),pregunta.getId(), encuesta_id, linearLayoutRepetido);
            }
            // Abiertas
            generarCampos(pregunta.getCampos(), pregunta.getId(), encuesta_id, linearLayoutRepetido);
        }
        agregarDivision(linearLayout);

    }

    private void generarTituloSeccion(Cerrada cerrada, LinearLayout linearLayout){
        TextView textView = new TextView(context);
        if (cerrada.isObligatoria() == 1){
            textView.setText("*" + cerrada.getEtiqueta());
        }else{
            textView.setText(cerrada.getEtiqueta());
        }
        textView.setTextSize(14);
        textView.setTextColor(Color.BLACK);
        linearLayout.addView(textView);

    }

    private void generarOpcionesMultiples(List<Opcion> opciones, int pregunta_id, int encuesta_id, LinearLayout linearLayout) {

        for (Opcion opcion : opciones){
            final CheckBox checkBox = new CheckBox(context);
            checkBox.setTag(encuesta_id + "-" + pregunta_id + "-" + opcion.getId() + "-1-");
            checkBox.setText(opcion.getTexto());
            checkBox.setTextSize(14);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    List<Integer> ids = getIds(checkBox.getTag().toString());

                    Ficha ficha = dbHelper.getFicha(ids.get(0), ids.get(1));
                    if (ficha == null){
                        ficha = new Ficha();
                        ficha.setEncuesta_id(ids.get(0));
                        ficha.setPregunta_id(ids.get(1));
                        ficha.setId((int) dbHelper.addFicha(ficha));
                    }

                    RespCerrada cerrada = dbHelper.getRespCerrada(checkBox.getTag().toString());
                    if (isChecked){
                        if (cerrada == null){
                            cerrada = new RespCerrada();
                            cerrada.setTag(checkBox.getTag().toString());
                            cerrada.setFicha_id(ficha.getId());
                            cerrada.setOpcion_id(ids.get(2));

                            dbHelper.addRespCerrada(cerrada);
                        }
                    }else {
                        dbHelper.deleteRespCerrada(checkBox.getTag().toString());
                    }
                }
            });


            linearLayout.addView(checkBox);
        }

    }


    private void generarOpcionesUnicas(int id, List<Opcion> opciones, final int pregunta_id, final int encuesta_id, LinearLayout linearLayout) {

        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setId(id);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        //Creamos los radioButtons
        for (final Opcion opcion : opciones){

            final RadioButton radioButton = new RadioButton(context);
            radioButton.setText(opcion.getTexto());
            radioButton.setTag(encuesta_id + "-" + pregunta_id + "-" + opcion.getId() + "-0-");
            radioButton.setTextSize(14);

            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    List<Integer> ids = getIds(radioButton.getTag().toString());

                    Ficha ficha = dbHelper.getFicha(ids.get(0), ids.get(1));
                    if (ficha == null){
                        ficha = new Ficha();
                        ficha.setEncuesta_id(ids.get(0));
                        ficha.setPregunta_id(ids.get(1));
                        ficha.setId((int) dbHelper.addFicha(ficha));
                    }

                    RespCerrada cerrada = dbHelper.getRespCerrada(radioButton.getTag().toString());
                    if (isChecked){
                        if (cerrada == null){
                            cerrada = new RespCerrada();
                            cerrada.setTag(radioButton.getTag().toString());
                            cerrada.setFicha_id(ficha.getId());
                            cerrada.setOpcion_id(ids.get(2));

                            dbHelper.addRespCerrada(cerrada);
                        }
                    }else {
                        dbHelper.deleteRespCerrada(radioButton.getTag().toString());
                    }
                }
            });

            radioGroup.addView(radioButton);


        }

        linearLayout.addView(radioGroup);

    }

    private void agregarDivision(LinearLayout linearLayout) {

        ImageView divider = new ImageView(context);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(1, 18, 1, 18);
        divider.setLayoutParams(lp);
        divider.setBackgroundColor(Color.WHITE);
        linearLayout.addView(divider);

    }

    private void generarEnunciado(Pregunta pregunta, LinearLayout linearLayout){

        TextView textView = new TextView(context);
        textView.setId(pregunta.getId());
        textView.setText(pregunta.getEnunciado());
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(16);
        linearLayout.addView(textView);

    }

    private void generarCampos(List<Campo> campos, int pregunta_id, int encuesta_id, LinearLayout linearLayout){

        for (final Campo campo : campos){

            //Agregando el editText que sera el input
            final EditText editText = new EditText(context);
            editText.setTag(encuesta_id + "-" + pregunta_id + "-" + campo.getId() + "-");


            editText.setTextSize(15);
            editText.setHint(campo.getEtiqueta());


            switch (campo.getDominio().getTipoDato()){
                case "Texto":
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case "Email":
                    editText.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;
                case "Entero":
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER|
                            InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                case "Decimal":
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER|
                            InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                case "Fecha":
                    editText.setInputType(InputType.TYPE_CLASS_DATETIME|
                            InputType.TYPE_DATETIME_VARIATION_DATE);
                    break;
            }

            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
//                       ID's = [encuesta, pregunta, campo]
                        List<Integer> ids = getIds(editText.getTag().toString());

                        Ficha ficha = dbHelper.getFicha(ids.get(0), ids.get(1));
                        if (ficha == null){
                            ficha = new Ficha();
                            ficha.setEncuesta_id(ids.get(0));
                            ficha.setPregunta_id(ids.get(1));
                            ficha.setId((int) dbHelper.addFicha(ficha));
                        }

                        RespAbierta respAbierta = dbHelper.getRespAbierta(editText.getTag().toString());
                        if (respAbierta != null){
                            if (!editText.getText().toString().trim().equals("")){
                                respAbierta.setValor(editText.getText().toString());
                                dbHelper.updateRespAbierta(respAbierta);
                            }else {
                                dbHelper.deleteRespAbierta(editText.getTag().toString());
                            }
                        }else {
                            if (!editText.getText().toString().trim().equals("")){
                                respAbierta = new RespAbierta();
                                respAbierta.setTag(editText.getTag().toString());
                                respAbierta.setValor(editText.getText().toString());
                                respAbierta.setFicha_id(ficha.getId());
                                respAbierta.setCampo_id(ids.get(2));
                                dbHelper.addRespAbierta(respAbierta);
                            }

                        }
                    }else{
                        ultimoTag = editText.getTag().toString();
                    }
                }
            });

            linearLayout.addView(editText);

        }

    }

    private void generarOtros(List<Otro> otros, int pregunta_id, int encuesta_id, LinearLayout linearLayout){
        for (final Otro otro : otros){

            //Agregando el editText que sera el input
            final EditText editText2 = new EditText(context);
            editText2.setTag(encuesta_id + "-" + pregunta_id + "-" + otro.getId() + "-");

            editText2.setTextSize(15);
            editText2.setHint(otro.getEtiqueta());
            switch (otro.getDominio().getTipoDato()){
                case "Otro":
                    editText2.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case "Email":
                    editText2.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;
                case "Entero":
                    editText2.setInputType(InputType.TYPE_CLASS_NUMBER|
                            InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                case "Decimal":
                    editText2.setInputType(InputType.TYPE_CLASS_NUMBER|
                            InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                case "Fecha":
                    editText2.setHint("dd/MM/YYYY");
                    editText2.setInputType(InputType.TYPE_CLASS_DATETIME|
                            InputType.TYPE_DATETIME_VARIATION_DATE);
                    break;
            }

            editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
//                       ID's = [encuesta, pregunta, otro]
                        List<Integer> ids = getIds(editText2.getTag().toString());

                        Ficha ficha = dbHelper.getFicha(ids.get(0), ids.get(1));
                        if (ficha == null){
                            ficha = new Ficha();
                            ficha.setEncuesta_id(ids.get(0));
                            ficha.setPregunta_id(ids.get(1));
                            ficha.setId((int) dbHelper.addFicha(ficha));
                        }

                        RespOtro respOtro = dbHelper.getRespOtro(editText2.getTag().toString());
                        if (respOtro != null){
                            if (!editText2.getText().toString().trim().equals("")){
                                respOtro.setValor(editText2.getText().toString());
                                dbHelper.updateRespOtro(respOtro);
                            }else {
                                dbHelper.deleteRespOtro(editText2.getTag().toString());
                            }
                        }else {
                            if (!editText2.getText().toString().trim().equals("")){
                                respOtro = new RespOtro();
                                respOtro.setTag(editText2.getTag().toString());
                                respOtro.setValor(editText2.getText().toString());
                                respOtro.setFicha_id(ficha.getId());
                                respOtro.setOtro_id(ids.get(2));
                                dbHelper.addRespOtro(respOtro);
                            }
                        }
                    }else{
                        ultimoTag = editText2.getTag().toString();
                    }
                }
            });

            linearLayout.addView(editText2);

        }
    }



    public void cargarUltimo(int encuesta_id){
        List<Ficha> fichas = dbHelper.getFichas(encuesta_id);

        for(Ficha ficha : fichas){
            List<RespAbierta> abiertas = dbHelper.getRespAbiertas(ficha.getId());
            List<RespOtro> otros = dbHelper.getRespOtros(ficha.getId());
            List<RespCerrada> cerradas = dbHelper.getRespCerradas(ficha.getId());


            for(RespAbierta abierta : abiertas){
                EditText editText = (EditText) view.findViewWithTag(abierta.getTag());
                editText.setText(abierta.getValor());
            }


            for(RespOtro respOtro : otros){

                EditText editText = (EditText) view.findViewWithTag(respOtro.getTag());
                editText.setText(respOtro.getValor());
            }


            for (RespCerrada cerrada : cerradas){
                List<Integer> list = getIds(cerrada.getTag());
                if (list.get(3) == 1){
                    CheckBox checkBox = (CheckBox) view.findViewWithTag(cerrada.getTag());
                    checkBox.setChecked(true);
                }else{
                    RadioButton radioButton = (RadioButton) view.findViewWithTag(cerrada.getTag());
                    radioButton.setChecked(true);
                }
             }

        }


     }



     public List<Integer> getIds(String tag){
         List<Integer> list = new ArrayList<>();
         String num = "";
         for (char c : tag.toCharArray()){
             if(c == '-') {
                 list.add(Integer.parseInt(num));
                 num = "";
             }else{
                 num = num + c;
             }
         }

         return list;
     }


     public void guardarUltimo(){
        if (ultimoTag != null){
            EditText et = (EditText) view.findViewWithTag(ultimoTag);
            et.clearFocus();
        }
     }






}
