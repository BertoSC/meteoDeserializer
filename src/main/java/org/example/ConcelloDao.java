package org.example;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class ConcelloDao implements Dao<Concello, Integer>{
    private static final String CONCELLOS_JSON = "concellos.json";
    private static final Type listType = new TypeToken<List<Concello>>() {}.getType();

    @Override
    public Concello getById(Integer id) {
        return null;
    }

    @Override
    public Concello getByName(String nomeConcello) {
        return null;
    }

    @Override
    public List<Concello> getAll() {
        return null;
    }
}
