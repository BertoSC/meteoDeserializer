package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDao implements Dao<Provincia, Integer>{
    private static final String PROVINCIAS_FILE = "concellosprovincia.json";
    private static final Type LISTPROVTYPE = new TypeToken<List<Provincia>>() {}.getType();
    private List<Provincia> provincias;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LISTPROVTYPE, new ProvinciaAdapter()).create();

    public ProvinciaDao(){
        provincias = getAll();
    }

    public ProvinciaDao (Reader r){
        List<Provincia> list = gson.fromJson(r, LISTPROVTYPE);
        provincias = list;
    }

    @Override
    public Provincia getById(Integer id) {
        return null;
    }

    @Override
    public Provincia getByName(String name) {
        for (Provincia p:provincias){
            if (p.getNombre().equals(name)){
                return p;
            } else {
                System.out.println("La provincia indicada no se encuentra en la lista");
            }
        }
        return new Provincia();
    }

    @Override
    public List<Provincia> getAll() {
        String prov = null;
        try {
            prov = Files.readString(Path.of(PROVINCIAS_FILE));
            List<Provincia> listaP = gson.fromJson(prov, LISTPROVTYPE);
            return listaP;
        } catch (IOException e) {
            System.err.println("Error en el proceso");
            return new ArrayList<>();
        }
    }
}
