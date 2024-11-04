package org.example;

import java.util.List;

public class PrediccionDia {
    private String dataPredicion; // Guádala para que la ponga mejor como LocalDate
    private int nivelAviso;
    private int tMax;
    private int tMin;
    private int uvMax;
    private List<VariableFranxa> listaVariableFranxa;

    public PrediccionDia(){}

    public PrediccionDia(String dataPredicion, int nivelAviso, int tMax, int tMin, int uvMax, List<VariableFranxa> listaVariableFranxa) {
        this.dataPredicion = dataPredicion;
        this.nivelAviso = nivelAviso;
        this.tMax = tMax;
        this.tMin = tMin;
        this.uvMax = uvMax;
        this.listaVariableFranxa = listaVariableFranxa;
    }

    public String getDataPredicion() {
        return dataPredicion;
    }

    public void setDataPredicion(String dataPredicion) {
        this.dataPredicion = dataPredicion;
    }

    public int getNivelAviso() {
        return nivelAviso;
    }

    public void setNivelAviso(int nivelAviso) {
        this.nivelAviso = nivelAviso;
    }

    public int gettMax() {
        return tMax;
    }

    public void settMax(int tMax) {
        this.tMax = tMax;
    }

    public int gettMin() {
        return tMin;
    }

    public void settMin(int tMin) {
        this.tMin = tMin;
    }

    public int getUvMax() {
        return uvMax;
    }

    public void setUvMaz(int uvMax) {
        this.uvMax = uvMax;
    }

    public List<VariableFranxa> getListaVariableFranxa() {
        return listaVariableFranxa;
    }

    public void setListaVariableFranxa(List<VariableFranxa> listaVariableFranxa) {
        this.listaVariableFranxa = listaVariableFranxa;
    }

    @Override
    public String toString() {
        return "PrediccionDia{" +
                "dataPredicion='" + dataPredicion + '\'' +
                ", nivelAviso=" + nivelAviso +
                ", tMax=" + tMax +
                ", tMin=" + tMin +
                ", uvMax=" + uvMax +
                ", listaVariableFranxa=" + listaVariableFranxa +
                '}';
    }
}
