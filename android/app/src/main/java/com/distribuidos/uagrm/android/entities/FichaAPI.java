package com.distribuidos.uagrm.android.entities;

import java.util.List;

public class FichaAPI {
    int id;
    int encuesta_id;
    int pregunta_id;
    List<RespAbierta> respAbiertas;
    List<RespCerrada> respCerradas;
    List<RespOtro> respOtros;

    public FichaAPI() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEncuesta_id() {
        return encuesta_id;
    }

    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
    }

    public int getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(int pregunta_id) {
        this.pregunta_id = pregunta_id;
    }

    public List<RespAbierta> getRespAbiertas() {
        return respAbiertas;
    }

    public void setRespAbiertas(List<RespAbierta> respAbiertas) {
        this.respAbiertas = respAbiertas;
    }

    public List<RespCerrada> getRespCerradas() {
        return respCerradas;
    }

    public void setRespCerradas(List<RespCerrada> respCerradas) {
        this.respCerradas = respCerradas;
    }

    public List<RespOtro> getRespOtros() {
        return respOtros;
    }

    public void setRespOtros(List<RespOtro> respOtros) {
        this.respOtros = respOtros;
    }
}
