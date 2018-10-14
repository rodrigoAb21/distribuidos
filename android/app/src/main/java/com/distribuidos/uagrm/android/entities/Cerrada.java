package com.distribuidos.uagrm.android.entities;

import java.util.List;

public class Cerrada {
    int id;
    String tipoSeleccion;
    int obligatoria;
    int pregunta_id;
    List<Opcion> opciones;

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

    public int isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(int obligatoria) {
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
}

