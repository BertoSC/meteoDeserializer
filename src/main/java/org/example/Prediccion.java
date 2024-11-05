package org.example;

import java.util.List;

public class Prediccion {
    private Concello concello;
    private List<PrediccionDia> listaPredDiaConcello;
    final static String BARRA = "-----------------------------------------";

    public Prediccion(){}

    public Prediccion(Concello concello, List<PrediccionDia> listaPredDiaConcello) {
        this.concello = concello;
        this.listaPredDiaConcello = listaPredDiaConcello;
    }

    public Concello getConcello() {
        return concello;
    }

    public void setConcello(Concello concello) {
        this.concello = concello;
    }

    public List<PrediccionDia> getListaPredDiaConcello() {
        return listaPredDiaConcello;
    }

    public void setListaPredDiaConcello(List<PrediccionDia> listaPredDiaConcello) {
        this.listaPredDiaConcello = listaPredDiaConcello;
    }

    @Override
    public String toString() {

            StringBuilder sb = new StringBuilder();
            sb.append(concello).append(System.lineSeparator())
                    .append(BARRA).append(System.lineSeparator());

            for (PrediccionDia dia : listaPredDiaConcello) {
                sb.append(dia).append(System.lineSeparator());
            }



            return sb.toString();

    }
}
