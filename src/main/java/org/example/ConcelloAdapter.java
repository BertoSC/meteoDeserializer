package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConcelloAdapter extends TypeAdapter<List<Concello>> {

    @Override
    public void write(JsonWriter jsonWriter, List<Concello> concelloList) throws IOException {
    }

    @Override
    public List<Concello> read(JsonReader jsonReader) throws IOException {
        List <Concello> lista = new ArrayList<>();
        //if (jsonReader.peek()==JsonToken.BEGIN_ARRAY)
        jsonReader.beginArray();
        while (jsonReader.hasNext()){
            lista.add(concelloReader(jsonReader));
        }

        jsonReader.endArray();
        return lista;
    }

    public Concello concelloReader(JsonReader jsonReader) {
       Concello temp = new Concello();
        try {
            int id = 0;
            String nom = "";
            jsonReader.beginObject();
            while (jsonReader.hasNext()){
                String tipo = jsonReader.nextName();
                switch (tipo){
                    case "id":
                        id=jsonReader.nextInt();
                        break;
                    case "nombre":
                        nom=jsonReader.nextString();
                        break;
                }
            }
            jsonReader.endObject();
            temp= new Concello(id, nom);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }
}
