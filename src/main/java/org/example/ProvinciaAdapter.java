package org.example;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaAdapter implements JsonDeserializer<List<Provincia>> {

    @Override
    public List<Provincia> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<Provincia> listaProv = new ArrayList<>();
        JsonObject jo=jsonElement.getAsJsonObject();
         for (TipoProvincia tp:TipoProvincia.values()){
            TipoProvincia paraAñadirTipo = tp.getTipo(tp.getTipo());

            List<Concello> listaConcello = jsonDeserializationContext.deserialize(); //revisar
            JsonArray tempAr= jo.getAsJsonArray(tp.getTipo());
               for (JsonElement je: tempAr) {
                   //revisar

               }



            String nom= temp.get("nombre").getAsString();
            int id=temp.get("id").getAsInt();
            lista.add(new Concello(id, nom));
        }

        prov  = new Provincia(t, t.getTipo(), lista);
        return prov;
    }
}
