package com.distribuidos.uagrm.android.entities;

public class MLocal {
    private int id;
    private int id_modelo;
    private String nombre;
    private String descripcion;
    private String estado;
    private String json;

    public MLocal() {
    }

    public MLocal(int id, int id_modelo, String nombre, String descripcion, String estado, String json) {
        this.id = id;
        this.id_modelo = id_modelo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.json = json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
