package com.distribuidos.uagrm.android.entities;

public class Campo {
    private int id;
    private String etiqueta;
    private boolean obligatorio;
    private boolean varios;
    private int pregunta_id;
    private int dominio_id;
    private Dominio dominio;

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

    public boolean isObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public boolean isVarios() {
        return varios;
    }

    public void setVarios(boolean varios) {
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
