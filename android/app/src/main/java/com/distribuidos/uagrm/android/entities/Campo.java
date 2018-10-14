package com.distribuidos.uagrm.android.entities;

public class Campo {
    int id;
    String etiqueta;
    int obligatorio;
    int varios;
    int pregunta_id;
    int dominio_id;
    Dominio dominio;

    public Campo() {
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

    public int getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(int obligatorio) {
        this.obligatorio = obligatorio;
    }

    public int getVarios() {
        return varios;
    }

    public void setVarios(int varios) {
        this.varios = varios;
    }

    public int getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(int pregunta_id) {
        this.pregunta_id = pregunta_id;
    }

    public int getDominio_id() {
        return dominio_id;
    }

    public void setDominio_id(int dominio_id) {
        this.dominio_id = dominio_id;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }
}
