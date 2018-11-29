package com.distribuidos.uagrm.android.entities;

public class Encuesta {
    int id;
    String fecha;
    String estado;
    double longitud;
    double latitud;
    int asignacion_id;

    public Encuesta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAsignacion_id() {
        return asignacion_id;
    }

    public void setAsignacion_id(int asignacion_id) {
        this.asignacion_id = asignacion_id;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
