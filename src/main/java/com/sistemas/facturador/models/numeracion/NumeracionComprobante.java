package com.sistemas.facturador.models.numeracion;


public class NumeracionComprobante {
    private String serie;
    private String correlativo;

    public NumeracionComprobante() {}

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }
}
