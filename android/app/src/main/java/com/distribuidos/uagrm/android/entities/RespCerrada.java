package com.distribuidos.uagrm.android.entities;

public class RespCerrada {
    private int id;
    private String tag;
    private int ficha_id;
    private int opcion_id;

    public RespCerrada(){

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

    public int getFicha_id() {
        return ficha_id;
    }

    public void setFicha_id(int ficha_id) {
        this.ficha_id = ficha_id;
    }

    public int getOpcion_id() {
        return opcion_id;
    }

    public void setOpcion_id(int opcion_id) {
        this.opcion_id = opcion_id;
    }
}
