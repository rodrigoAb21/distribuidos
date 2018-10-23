package com.distribuidos.uagrm.android.entities;

import java.util.List;

public class Encuestas {
    List<EncuestaAPI> encuestas;

    public Encuestas(){}

    public List<EncuestaAPI> getEncuestas() {
        return encuestas;
    }

    public void setEncuestas(List<EncuestaAPI> encuestas) {
        this.encuestas = encuestas;
    }
}
