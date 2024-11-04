package org.example;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrediccionDeserializer implements JsonDeserializer<Prediccion> {
    @Override
    public Prediccion deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject predConcello = jsonElement.getAsJsonObject();
        JsonObject jo = predConcello.getAsJsonObject("predConcello");
        Concello co = null;
        int id = jo.get("idConcello").getAsInt();
        String nom = jo.get("nome").getAsString();
        co = new Concello(id, nom);
        JsonArray ja =jo.get("listaPredDiaConcello").getAsJsonArray();
        List<PrediccionDia> lista = new ArrayList<>();
        for (JsonElement je:ja){
            PrediccionDia pd = jsonDeserializationContext.deserialize(je, PrediccionDia.class);
            lista.add(pd);
        }
        return new Prediccion(co, lista);
    }
}
