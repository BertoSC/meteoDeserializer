package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class AppMeteoMain {

    public AppMeteoMain(){
        ejecutarAPP();
    }

    public void ejecutarAPP(){
        Type listType = new TypeToken<List<Concello>>() {}.getType();
        Type listProvType = new TypeToken<List<Provincia>>() {}.getType();

        System.out.println("PREDICCIONES METEOROLÓGICAS DE CONCELLOS DE GALICIA");
        System.out.println();
        System.out.println("Introduce el ID del Concello que quieres consultar");

        Scanner sc = new Scanner(System.in);
        //int idConcello = 15078; // ID de ejemplo para Santiago de Compostela
        int idConcello = sc.nextInt();
        String json = null;
        Boolean existe = comprobarIdConcello(idConcello);

        if (existe) {
            System.out.println();
            System.out.println("PREDICIÓN METEOROLÓGICA:");
            System.out.println();
            json = obtenerPrediccionJson(idConcello);
        }

        if (json != null) {
            Gson gson = construirGsonBuilder();
            Prediccion p = gson.fromJson(json, Prediccion.class);
            System.out.println(p);

        } else {
            System.out.println("No se pudo obtener la predicción.");
        }


    }

    public static String obtenerPrediccionJson(int idConcello) {
        String urlAcceso = "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=" + idConcello;
        try {
            URI uri = new URI(urlAcceso);
            HttpURLConnection conexion = (HttpURLConnection) uri.toURL().openConnection();
            conexion.setRequestMethod("GET");
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (var br = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
                    String linea = null;
                    StringBuilder prediccionJson = new StringBuilder();
                    while ((linea = br.readLine()) != null) {
                        prediccionJson.append(linea);
                    }
                    return prediccionJson.toString();
                }
            } else {
                System.out.println("Error en la petición: " + conexion.getResponseCode());
                return null;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Gson construirGsonBuilder(){
        Type listType = new TypeToken<List<Concello>>() {}.getType();
        Type listProvType = new TypeToken<List<Provincia>>() {}.getType();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(PrediccionDia.class, new PrediccionDiaDeserializer())
                .registerTypeAdapter(Prediccion.class, new PrediccionDeserializer())
                .registerTypeAdapter(listType, new ConcelloAdapter())
                .registerTypeAdapter(listProvType, new ProvinciaAdapter())
                .create();
        return gson;
    }

    private static Boolean comprobarIdConcello(int idConcello) {
        Type listType = new TypeToken<List<Concello>>() {}.getType();
        Type listProvType = new TypeToken<List<Provincia>>() {}.getType();
        Gson gson = construirGsonBuilder();

        try {
            String s = Files.readString(Path.of("concellos.json"));
            List<Concello> listaConcello = gson.fromJson(s, listType);

            for (Concello c : listaConcello) {
                if (c.getIdConcello() == idConcello) {
                    return true;
                }
            }
            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new AppMeteoMain();

    }
}
