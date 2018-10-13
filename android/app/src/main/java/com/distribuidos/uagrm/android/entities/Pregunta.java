package com.distribuidos.uagrm.android.entities;

import java.util.ArrayList;

public class Pregunta {
    private int id;
    private String enunciado;
    private int modelo_id;
    private ArrayList<Cerrada> cerradas;
    private ArrayList<Campo> campos;


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

    public ArrayList<Cerrada> getCerradas() {
        return cerradas;
    }

    public void setCerradas(ArrayList<Cerrada> cerradas) {
        this.cerradas = cerradas;
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }
}
