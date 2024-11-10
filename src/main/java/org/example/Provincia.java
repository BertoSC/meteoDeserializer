package org.example;

import java.util.List;

public class Provincia {
    TipoProvincia tipoProvincia;
    String nombre;
    List<Concello> listaConcello;

    public Provincia(){}

    public Provincia(TipoProvincia tp, String nombre, List<Concello> listaConcello) {
        this.nombre = nombre;
        this.listaConcello = listaConcello;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Concello> getListaConcello() {
        return listaConcello;
    }

    public void setListaConcello(List<Concello> listaConcello) {
        this.listaConcello = listaConcello;
    }

    public void añadirConcello(Concello c){
        listaConcello.add(c);
    }

    public TipoProvincia getTipoProvincia() {
        return tipoProvincia;
    }

    public void setTipoProvincia(TipoProvincia tipoProvincia) {
        this.tipoProvincia = tipoProvincia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre+":"+System.lineSeparator());
        for (Concello c:listaConcello){
            sb.append(c+System.lineSeparator());
        }

        return  sb.toString();
    }
}
