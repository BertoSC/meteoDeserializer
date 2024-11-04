package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(PrediccionDia.class, new PrediccionDiaDeserializer())
                .registerTypeAdapter(Prediccion.class, new PrediccionDeserializer())
                .create();

        try {
            String meteo = Files.readString(Path.of("C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\AD\\Meteogalicia\\src\\main\\java\\org\\example\\meteo.json"));
            Prediccion p = gson.fromJson(meteo, Prediccion.class);
            System.out.println(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}