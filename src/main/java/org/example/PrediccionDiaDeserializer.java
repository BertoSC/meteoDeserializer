package org.example;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrediccionDiaDeserializer implements JsonDeserializer<PrediccionDia> {
    @Override
    public PrediccionDia deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jo = jsonElement.getAsJsonObject();
        String dat = jo.get("dataPredicion").getAsString();
        int nivelAviso = 0;

        if (!jo.get("nivelAviso").isJsonNull()){
            jo.get("nivelAviso").getAsInt();

        } else {
            nivelAviso =0;
        }

        int tMax = jo.get("tMax").getAsInt();
        int tMin = jo.get("tMin").getAsInt();
        int uvMax = jo.get("uvMax").getAsInt();

        List<VariableFranxa> listaVariableFranxa = crearListaFranxa(jo);
        PrediccionDia pd = new PrediccionDia(dat, nivelAviso, tMax, tMin, uvMax, listaVariableFranxa);
        return pd;

    }

    public List <VariableFranxa> crearListaFranxa (JsonObject jo){
        List<VariableFranxa> listaVariableFranxa = new ArrayList<>();

        JsonObject joC= jo.get("ceo").getAsJsonObject();
        VariableFranxa cielo = crearVariable(VariableMeteoroloxica.getVariable("ceo"), joC);

        JsonObject joL= jo.get("pchoiva").getAsJsonObject();
        VariableFranxa lluvia = crearVariable(VariableMeteoroloxica.getVariable("pchoiva"), joL);

        JsonObject joTmax= jo.get("tmaxFranxa").getAsJsonObject();
        VariableFranxa tempMax = crearVariable(VariableMeteoroloxica.getVariable("tmaxFranxa"), joTmax);

        JsonObject joTmin= jo.get("tminFranxa").getAsJsonObject();
        VariableFranxa tempMin = crearVariable(VariableMeteoroloxica.getVariable("tminFranxa"), joTmin);

        JsonObject joVent= jo.get("vento").getAsJsonObject();
        VariableFranxa viento = crearVariable(VariableMeteoroloxica.getVariable("vento"), joVent);

        listaVariableFranxa.add(cielo);
        listaVariableFranxa.add(lluvia);
        listaVariableFranxa.add(tempMax);
        listaVariableFranxa.add(tempMin);
        listaVariableFranxa.add(viento);

        return listaVariableFranxa;
    }

    public VariableFranxa crearVariable(VariableMeteoroloxica vm, JsonObject jo) {
        VariableFranxa temp = new VariableFranxa(vm, jo.get("manha").getAsInt(), jo.get("tarde").getAsInt(), jo.get("noite").getAsInt());
        return temp;
    }
}
