package com.distribuidos.uagrm.android.entities;

import java.util.List;

public class Pregunta {
    int id;
    String enunciado;
    int modelo_id;
    List<Cerrada> cerradas;
    List<Campo> campos;


    public Pregunta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getModelo_id() {
        return modelo_id;
    }

    public void setModelo_id(int modelo_id) {
        this.modelo_id = modelo_id;
    }

    public List<Cerrada> getCerradas() {
        return cerradas;
    }

    public void setCerradas(List<Cerrada> cerradas) {
        this.cerradas = cerradas;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }
}
