package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaAdapter implements JsonDeserializer<List<Provincia>> {
    // creamos el tipo personalizado para poder invocar el adaptador de Lista de Concellos
    Type listType = new TypeToken<List<Concello>>(){}.getType();

    @Override
    public List<Provincia> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
          // asociamos el objeto general a un JsonObject e iniciamos una lista de Provincia de concentra las provincias
          JsonObject jo= jsonElement.getAsJsonObject();
          List<Provincia> prov = new ArrayList<>();
          // usamos un ENUM para identificar las cuatro provincias y, por cada constante, deserializamos la provincia correspondiente
          for (TipoProvincia tp:TipoProvincia.values()){
            TipoProvincia paraAñadirTipo = tp.getTipo(tp.getTipo());
            // asociamos el objeto a un JsonArray e invocamos el deserializador de Lista de Concellos, al que le pasamos el JA para que pille los datos
            JsonArray ja=jo.getAsJsonArray(tp.getTipo());
            List<Concello> listC = jsonDeserializationContext.deserialize(ja,listType);
            // Asociamos las variables al obj Provincia y lo añadimos a la lista
            Provincia temp= new Provincia(paraAñadirTipo, tp.getTipo(), listC);
            prov.add(temp);

        }

        return prov;
        }
    }


