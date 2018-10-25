package com.distribuidos.uagrm.android.entities;

public class AsignacionLocal {
    int id;
    int asignacion_id;
    String modelo;
    String area;
    String hora_inicio;
    String hora_final;
    int cantidad;
    String descripcion;
    String json;

    public AsignacionLocal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsignacion_id() {
        return asignacion_id;
    }

    public void setAsignacion_id(int asignacion_id) {
        this.asignacion_id = asignacion_id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }
}
