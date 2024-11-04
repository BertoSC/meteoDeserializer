package org.example;

public enum VariableMeteoroloxica {
    CIELO("ceo"), LLUVIA("pchoiva"), TEMPERATURA_MAXIMA ("tmaxFranxa"),
    TEMPERATURA_MINIMA("tminFranxa"), VIENTO ("vento");

    String variableMeteroloxica;

    VariableMeteoroloxica(String variableMeteroloxica) {
        this.variableMeteroloxica = variableMeteroloxica;
    }

    public String getVariableMeteroloxica() {
        return variableMeteroloxica;
    }

    public static VariableMeteoroloxica getVariableMeteoroloxica(String var){
        for (VariableMeteoroloxica v : VariableMeteoroloxica.values()){
            if (v.getVariableMeteroloxica().equalsIgnoreCase(var) ||  v.name().equalsIgnoreCase(var)){
                return v;
            }
        }
        return null;
    }
}
