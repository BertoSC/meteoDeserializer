package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static final String URL = "https://servizos.meteogalicia.gal/mgrss/predicion/" +
            "jsonPredConcellos.action?idConc=15078&request_locale=gl";

    public static void main(String[] args) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(PrediccionDia.class, new PrediccionDiaDeserializer())
                .registerTypeAdapter(Prediccion.class, new PrediccionDeserializer())
                .create();
/*
        try {
            String meteo = Files.readString(Path.of("C:\\Users\\a23albertogc\\Desktop\\AD\\meteoDeserializer\\src\\main\\java\\org\\example\\meteo.json"));
            Prediccion p = gson.fromJson(meteo, Prediccion.class);
            System.out.println(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
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


    }
}
