package com.distribuidos.uagrm.android.entities;

public class RespCerrada {
    private int id;
    private String id_view;
    private int estado;
    private int id_ficha;

    public RespCerrada(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_view() {
        return id_view;
    }

    public void setId_view(String id_view) {
        this.id_view = id_view;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }
}
