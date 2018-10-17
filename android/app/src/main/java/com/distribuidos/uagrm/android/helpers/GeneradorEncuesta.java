package com.distribuidos.uagrm.android.helpers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.distribuidos.uagrm.android.entities.Campo;
import com.distribuidos.uagrm.android.entities.Cerrada;
import com.distribuidos.uagrm.android.entities.Modelo;
import com.distribuidos.uagrm.android.entities.Opcion;
import com.distribuidos.uagrm.android.entities.Pregunta;

import java.util.List;

public class GeneradorEncuesta {
    LinearLayout linearLayout; 
    Context context;

    public GeneradorEncuesta(LinearLayout linearLayout, Context context) {
        this.linearLayout = linearLayout;
        this.context = context;
    }

    public void generarVista(Modelo modelo){
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
                if (cerrada.getTipoSeleccion().equals("Multiple")){
                    //Creamos el checkbox
                    generarOpcionesMultiples(cerrada.getOpciones(), linearLayoutRepetido);
                }else{

                    //Creamos el radioGroup
                    generarOpcionesUnicas(cerrada.getId(), cerrada.getOpciones(), linearLayoutRepetido);
                }
            }
            generarCampos(pregunta.getCampos(), linearLayoutRepetido);

        }

        agregarDivision(linearLayout);

    }

    private void generarOpcionesUnicas(int id, List<Opcion> opciones, LinearLayout linearLayout) {

        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setId(id);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        //Creamos los radioButtons
        for (Opcion opcion : opciones){

            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(opcion.getId());
            radioButton.setText(opcion.getTexto());
            radioButton.setTextSize(14);
            radioGroup.addView(radioButton);


        }

        linearLayout.addView(radioGroup);

    }

    private void agregarDivision(LinearLayout linearLayout) {

        ImageView divider = new ImageView(context);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(1, 18, 1, 18);
        divider.setLayoutParams(lp);
        divider.setBackgroundColor(Color.WHITE);
        linearLayout.addView(divider);

    }

    private void generarOpcionesMultiples(List<Opcion> opciones, LinearLayout linearLayout) {

        for (Opcion opcion : opciones){
            CheckBox checkBox = new CheckBox(context);
            checkBox.setId(opcion.getId());
            checkBox.setText(opcion.getTexto());
            checkBox.setTextSize(14);
            linearLayout.addView(checkBox);
        }

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

    private void generarCampos(List<Campo> campos, LinearLayout linearLayout){

        for (Campo campo : campos){

            //Agregando el editText que sera el input
            EditText editText = new EditText(context);
            editText.setId(campo.getId());
            editText.setTextSize(15);

            switch (campo.getDominio().getTipoDato()){
                case "Texto":
                    editText.setHint(campo.getEtiqueta());
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case "Email":
                    editText.setHint(campo.getEtiqueta());
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;
                case "Entero":
                    editText.setHint(campo.getEtiqueta());
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                case "Decimal":
                    editText.setHint(campo.getEtiqueta());
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                case "Fecha":
                    editText.setHint("dd/MM/YYYY");
                    editText.setInputType(InputType.TYPE_CLASS_DATETIME|InputType.TYPE_DATETIME_VARIATION_DATE);
                    break;
            }

            linearLayout.addView(editText);

        }

    }


}
