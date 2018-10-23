package com.distribuidos.uagrm.android.entities;

public class RespOtro {
    private int id;
    private String tag;
    private String valor;
    private int ficha_id;
    private int otro_id;

    public RespOtro() {
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

    public int getOtro_id() {
        return otro_id;
    }

    public void setOtro_id(int otro_id) {
        this.otro_id = otro_id;
    }
}
