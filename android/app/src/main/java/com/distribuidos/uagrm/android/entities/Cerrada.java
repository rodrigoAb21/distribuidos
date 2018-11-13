package com.distribuidos.uagrm.android.entities;

import java.util.List;

public class Cerrada {
    int id;
    String etiqueta;
    String tipoSeleccion;
    boolean obligatoria;
    int pregunta_id;
    List<Opcion> opciones;
    List<Otro> otros;

    public Cerrada(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoSeleccion() {
        return tipoSeleccion;
    }

    public void setTipoSeleccion(String tipoSeleccion) {
        this.tipoSeleccion = tipoSeleccion;
    }

    public boolean isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public int getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(int pregunta_id) {
        this.pregunta_id = pregunta_id;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }


    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public List<Otro> getOtros() {
        return otros;
    }

    public void setOtros(List<Otro> otros) {
        this.otros = otros;
    }
}

