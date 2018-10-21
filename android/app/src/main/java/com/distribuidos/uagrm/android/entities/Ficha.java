package com.distribuidos.uagrm.android.entities;

public class Ficha {
    private int id;
    private int id_modelo_api;
    private int id_modelo_local;
    private String estado;

    public Ficha() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_modelo_api() {
        return id_modelo_api;
    }

    public void setId_modelo_api(int id_modelo_api) {
        this.id_modelo_api = id_modelo_api;
    }

    public int getId_modelo_local() {
        return id_modelo_local;
    }

    public void setId_modelo_local(int id_modelo_local) {
        this.id_modelo_local = id_modelo_local;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
