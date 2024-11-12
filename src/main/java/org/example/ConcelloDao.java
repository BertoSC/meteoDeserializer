package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConcelloDao implements Dao<Concello, Integer>{
    private static final String CONCELLOS_JSON = "concellos.json";
    private static final Type LISTTYPE = new TypeToken<List<Concello>>() {}.getType();
    private List<Concello> listaConcellos;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LISTTYPE, new ConcelloAdapter()).create();

    public ConcelloDao(){
        try {
            String s = Files.readString(Path.of(CONCELLOS_JSON));
            List <Concello> listaConcello = gson.fromJson(s, LISTTYPE);
            listaConcellos=listaConcello;

        } catch (IOException e) {
            System.err.println("Se ha producido un error inesperado");

        }

    }

    public ConcelloDao(Reader r){
        List <Concello> listaConcello = gson.fromJson(r, LISTTYPE);
        listaConcellos= listaConcello;
    }

    public List<Concello> getListaConcellos() {
        return listaConcellos;
    }

    @Override
    public Concello getById(Integer id) {
        for (Concello c: listaConcellos){
            if (c.getIdConcello()==id){
                return c;
            }
        }
        return new Concello();
    }

    @Override
    public Concello getByName(String nomeConcello) {
        for (Concello c: listaConcellos){
           if (c.getNome().equals(nomeConcello)){
               return c;
           }
        }
        return new Concello();
    }

    @Override
    public List<Concello> getAll() {
        try {
            String s = Files.readString(Path.of(CONCELLOS_JSON));
            List <Concello> listaConcello = gson.fromJson(s, LISTTYPE);
            return listaConcello;

        } catch (IOException e) {
            System.err.println("Se ha producido un error inesperado");
            return new ArrayList<>();
        }
    }
}