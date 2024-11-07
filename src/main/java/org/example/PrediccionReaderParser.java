package org.example;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PrediccionReaderParser {
    public Prediccion prediccionParser(JsonReader reader){
        Prediccion pre = new Prediccion();
        Concello co = new Concello();
        List<PrediccionDia> listaPred = new ArrayList<>();

        try {
            reader.beginObject();
            String titulo = reader.nextName();
                if (titulo.equals("predConcello")){
                    reader.beginObject();
                    int idConcello =0;
                    String nombre= "";
                    while (reader.hasNext()) {
                        String caso = reader.nextName();

                        switch (caso){
                            case "idConcello":
                                idConcello= reader.nextInt();
                                break;

                            case "nome":
                                nombre= reader.nextString();
                                break;

                            case "listaPredDiaConcello":
                                listaPred = readerListaPred(reader);
                                break;
                        }
                    }

                    co= new Concello(idConcello, nombre);
                    pre = new Prediccion(co, listaPred);
                    reader.endObject();
                }

            reader.endObject();
            return pre;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PrediccionDia> readerListaPred(JsonReader reader) {
        List <PrediccionDia> lista = new ArrayList<>();
        try {
            reader.beginArray();
            while (reader.hasNext()){
                lista.add(readerPredDia(reader));
            }
            reader.endArray();
            return lista;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PrediccionDia readerPredDia(JsonReader reader) {
        PrediccionDia pd = new PrediccionDia();
        String dataPredicion = "";
        int nivelAviso = 0;
        int tMax = 0;
        int tMin = 0;
        int uvMax = 0;
        List<VariableFranxa> listaVariableFranxa = new ArrayList<>();

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String tipo = reader.nextName();
                switch (tipo) {
                    case "dataPredicion":
                        dataPredicion = reader.nextString();
                        break;
                    case "nivelAviso":
                        JsonToken nivel= reader.peek();
                        if (nivel!=JsonToken.NULL){
                            nivelAviso=reader.nextInt();
                            break;
                        } else {
                            reader.nextNull();
                            nivelAviso=0;
                            break;
                        }
                    case "tMax":
                        tMax=reader.nextInt();
                        break;
                    case "tMin":
                        tMin=reader.nextInt();
                        break;
                    case "uvMax":
                        uvMax=reader.nextInt();
                        break;

                    case "ceo":
                        listaVariableFranxa.add(crearVariable(reader, VariableMeteoroloxica.getVariable("ceo")));
                        break;
                    case "pchoiva":
                        listaVariableFranxa.add(crearVariable(reader, VariableMeteoroloxica.getVariable("pchoiva")));
                        break;
                    case "tmaxFranxa":
                        listaVariableFranxa.add(crearVariable(reader, VariableMeteoroloxica.getVariable("tmaxFranxa")));
                        break;
                    case "tminFranxa":
                        listaVariableFranxa.add(crearVariable(reader, VariableMeteoroloxica.getVariable("tminFranxa")));
                        break;
                    case "vento":
                        listaVariableFranxa.add(crearVariable(reader, VariableMeteoroloxica.getVariable("vento")));
                        break;
                }
            }
            reader.endObject();
            pd = new PrediccionDia(dataPredicion, nivelAviso, tMax, tMin, uvMax, listaVariableFranxa);
            return pd;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private VariableFranxa crearVariable(JsonReader reader, VariableMeteoroloxica vm) {
        VariableFranxa vf = new VariableFranxa();
        int mañana = 0;
        int tarde = 0;
        int noche = 0;
        try {
            reader.beginObject();
            while (reader.hasNext()){
                String tipo = reader.nextName();
                switch (tipo){
                    case "manha":
                        mañana = reader.nextInt();
                        break;
                    case "tarde":
                        tarde= reader.nextInt();
                        break;
                    case "noite":
                        noche = reader.nextInt();
                        break;
                }
            }
            reader.endObject();
            vf = new VariableFranxa(vm, mañana, tarde, noche);
            return vf;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
