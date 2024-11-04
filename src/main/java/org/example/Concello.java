package org.example;

public class Concello {
    private int idConcello;
    private String nome;

    public Concello(){}

    public Concello(int idConcello, String nome) {
        this.idConcello = idConcello;
        this.nome = nome;
    }

    public int getIdConcello() {
        return idConcello;
    }

    public void setIdConcello(int idConcello) {
        this.idConcello = idConcello;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Concello{" +
                "idConcello=" + idConcello +
                ", nome='" + nome + '\'' +
                '}';
    }
}
