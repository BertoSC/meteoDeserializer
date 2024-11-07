package org.example;

import java.util.List;

public class Provincia {
    String nombre;
    List<Concello> listaConcello;

    public Provincia(){}

    public Provincia(String nombre, List<Concello> listaConcello) {
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

    @Override
    public String toString() {
        return "Provincia{" +
                "nombre='" + nombre + '\'' +
                ", listaConcello=" + listaConcello +
                '}';
    }
}
