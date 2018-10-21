package com.distribuidos.uagrm.android.responses;

import com.distribuidos.uagrm.android.entities.Modelo;

public class ModeloResponse {
    Modelo data;

    public ModeloResponse(Modelo data) {
        this.data = data;
    }

    public ModeloResponse() {
    }

    public Modelo getData() {
        return data;
    }

    public void setData(Modelo data) {
        this.data = data;
    }
}
