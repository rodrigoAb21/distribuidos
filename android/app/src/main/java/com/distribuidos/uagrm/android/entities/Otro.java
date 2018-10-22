package com.distribuidos.uagrm.android.entities;

class Otro {
    int id;
    String etiqueta;
    int cerrada_id;
    Dominio dominio;

    public Otro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getCerrada_id() {
        return cerrada_id;
    }

    public void setCerrada_id(int cerrada_id) {
        this.cerrada_id = cerrada_id;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }
}
