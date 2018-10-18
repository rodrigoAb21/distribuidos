package com.distribuidos.uagrm.android.entities;

public class RespAbierta {
    private int id;
    private String id_view;
    private String valor;
    private int id_ficha;


    public RespAbierta(){

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }
}


