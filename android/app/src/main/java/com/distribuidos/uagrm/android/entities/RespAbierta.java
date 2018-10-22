package com.distribuidos.uagrm.android.entities;

public class RespAbierta {
    private int id;
    private String tag;
    private String valor;
    private int ficha_id;
    private int campo_id;


    public RespAbierta(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getFicha_id() {
        return ficha_id;
    }

    public void setFicha_id(int ficha_id) {
        this.ficha_id = ficha_id;
    }

    public int getCampo_id() {
        return campo_id;
    }

    public void setCampo_id(int campo_id) {
        this.campo_id = campo_id;
    }
}


