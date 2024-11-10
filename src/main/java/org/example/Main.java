package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static final String URL = "https://servizos.meteogalicia.gal/mgrss/predicion/" +
            "jsonPredConcellos.action?idConc=15078&request_locale=gl";

    public static void main(String[] args) {

        // creamos tipos personalizados para usar los adaptadores de las listas de objetos
        Type listType = new TypeToken<List<Concello>>(){}.getType();
        Type listProvType = new TypeToken<List<Provincia>> (){}.getType();

        // creamos el Gson con GsonBuilder y registramos todos los adaptadores necesarios para los ejercicios
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(PrediccionDia.class, new PrediccionDiaDeserializer())
                .registerTypeAdapter(Prediccion.class, new PrediccionDeserializer())
                .registerTypeAdapter(listType, new ConcelloAdapter())
                .registerTypeAdapter(listProvType, new ProvinciaAdapter())
                .create();

/*       // PARA EL EJ DE LEER DESDE EL ARCHIVO
        try {
            String meteo = Files.readString(Path.of("C:\\Users\\a23albertogc\\Desktop\\AD\\meteoDeserializer\\src\\main\\java\\org\\example\\meteo.json"));
            Prediccion p = gson.fromJson(meteo, Prediccion.class);
            System.out.println(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
        // PARA COMPROBAR QUE EL DESERIALIZER FUNCIONA
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URI(URL).toURL().openConnection().getInputStream()))) {

            Prediccion p = gson.fromJson(br, Prediccion.class);
            System.out.println(p);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // PARA COMPROBAR QUE EL READER FUNCIONA
        try (JsonReader jr = new JsonReader(new InputStreamReader(new URI(URL).toURL().openConnection().getInputStream()))) {
            PrediccionReaderParser prp = new PrediccionReaderParser();
            Prediccion p2 = prp.prediccionParser(jr);
            System.out.println(p2);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // DESERIALIZAMOS LA LISTA DE CONCELLOS
        try {
            String s = Files.readString(Path.of("concellos.json"));
            List <Concello> listaConcello = gson.fromJson(s, listType);
            for (Concello c:listaConcello){
                System.out.println(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // DESERIALIZAMOS LA LISTA DE PROVINCIAS
        try {
            String prov = Files.readString(Path.of("concellosprovincia.json"));
            List<Provincia> prueba = gson.fromJson(prov, listProvType);
            for (Provincia p: prueba) {
                System.out.println(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }
}
