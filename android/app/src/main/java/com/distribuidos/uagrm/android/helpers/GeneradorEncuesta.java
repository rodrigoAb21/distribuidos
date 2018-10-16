package com.distribuidos.uagrm.android.helpers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
            //Escribimos el enunciado de la pregunta en negritas
            generarEnunciado(pregunta);
            for (Cerrada cerrada : pregunta.getCerradas()){
                // Verificamos el tipo de seleccion
                if (cerrada.getTipoSeleccion().equals("Multiple")){
                    //Creamos el checkbox
                    generarOpcionesMultiples(cerrada.getOpciones());
                }else{

                    //Creamos el radioGroup
                    generarOpcionesUnicas(cerrada.getId(), cerrada.getOpciones());
                }
            }
            generarCampos(pregunta.getCampos());
            agregarDivision();
        }

    }

    private void generarOpcionesUnicas(int id, List<Opcion> opciones) {

        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setId(id);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        //Creamos los radioButtons
        for (Opcion opcion : opciones){

            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(opcion.getId());
            radioButton.setText(opcion.getTexto());
            radioGroup.addView(radioButton);

        }

        linearLayout.addView(radioGroup);

    }

    private void generarOpcionesMultiples(List<Opcion> opciones) {

        for (Opcion opcion : opciones){
            CheckBox checkBox = new CheckBox(context);
            checkBox.setId(opcion.getId());
            checkBox.setText(opcion.getTexto());
            linearLayout.addView(checkBox);
        }

    }

    private void agregarDivision() {

        ImageView divider = new ImageView(context);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(1, 50, 1, 50);
        divider.setLayoutParams(lp);
        divider.setBackgroundColor(Color.WHITE);
        linearLayout.addView(divider);

    }


    private void generarEnunciado(Pregunta pregunta){

        TextView textView = new TextView(context);
        textView.setId(pregunta.getId());
        textView.setText(pregunta.getEnunciado());
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLACK);
        linearLayout.addView(textView);

    }

    private void generarCampos(List<Campo> campos){

        for (Campo campo : campos){
            // Agregando la etiqueta del campo
            TextView textView = new TextView(context);
            textView.setId(campo.getId());
            textView.setText(campo.getEtiqueta());
            linearLayout.addView(textView);

            //Agregando el editText que sera el input
            EditText editText = new EditText(context);
            editText.setId(campo.getId());

            switch (campo.getDominio().getTipoDato()){
                case "Texto":
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case "Email":
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;
                case "Entero":
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                case "Decimal":
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
