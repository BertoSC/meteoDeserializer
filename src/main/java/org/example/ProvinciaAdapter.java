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
    Type listType = new TypeToken<List<Concello>>(){}.getType();

    @Override
    public List<Provincia> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
          JsonObject jo= jsonElement.getAsJsonObject();
          List<Provincia> prov = new ArrayList<>();
          for (TipoProvincia tp:TipoProvincia.values()){
            TipoProvincia paraAñadirTipo = tp.getTipo(tp.getTipo());
            JsonArray ja=jo.getAsJsonArray(tp.getTipo());
            String nom= "";
            int id=0;
            List<Concello> listC = jsonDeserializationContext.deserialize(ja,listType);
            Provincia temp= new Provincia(paraAñadirTipo, tp.getTipo(), listC);
            prov.add(temp);

        }


        return prov;
        }
    }


