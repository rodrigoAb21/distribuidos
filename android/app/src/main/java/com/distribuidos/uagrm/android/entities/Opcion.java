package com.distribuidos.uagrm.android.entities;

public class Opcion {
    private int id;
    private String texto;
    private int cerrada_id;

    public Opcion(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCerrada_id() {
        return cerrada_id;
    }

    public void setCerrada_id(int cerrada_id) {
        this.cerrada_id = cerrada_id;
    }
}
