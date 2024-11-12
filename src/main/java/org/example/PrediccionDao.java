package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class PrediccionDao implements Dao<Prediccion, Integer>{
    private static final String BASE_URL = "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=";
    private Gson gson;
    private ConcelloDao concelloDao;

    public PrediccionDao(){
        this.concelloDao=new ConcelloDao();
        this.gson= new GsonBuilder()
                .registerTypeAdapter(Prediccion.class, new PrediccionDeserializer())
                .registerTypeAdapter(PrediccionDia.class, new PrediccionDiaDeserializer()).create();
    }

    public PrediccionDao(ConcelloDao concelloD){
        this.concelloDao=concelloD;
        new GsonBuilder()
                .registerTypeAdapter(Prediccion.class, new PrediccionDeserializer())
                .registerTypeAdapter(PrediccionDia.class, new PrediccionDiaDeserializer()).create();
    }

    public PrediccionDao(ConcelloDao concelloD,Gson gs){
        this.concelloDao=concelloD;
        this.gson=gs;
    }

    public ConcelloDao getConcelloDao() {
        return concelloDao;
    }

    @Override
    public Prediccion getById(Integer id) {
        String urlAcceso = "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=" + id;
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
                    String json = prediccionJson.toString();
                    if (json != null) {
                        Prediccion p = gson.fromJson(json, Prediccion.class);
                        return p;

                    } else {
                        System.out.println("No se pudo obtener la predicción.");
                    }
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
        return new Prediccion();
    }

    @Override
    public Prediccion getByName(String name) {
        //List <Prediccion> listadoPred=getAll();
        List <Concello> listadoCon = concelloDao.getAll();
        int idSeleccionado=0;
        Prediccion seleccionada = null;
        for (Concello c:listadoCon){
            if (c.getNome().equals(name)){
                idSeleccionado=c.getIdConcello();
            }
        }
        return seleccionada=getById(idSeleccionado);
    }

    @Override
    public List<Prediccion> getAll() {
        List <Prediccion> listadoPred= null;
        List <Concello> listadoCon = concelloDao.getAll();
        for (Concello c:listadoCon){
            listadoPred.add(getById(c.getIdConcello()));
        }
        return listadoPred;
    }
}
