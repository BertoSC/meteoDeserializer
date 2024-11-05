package org.example;

public enum VariableMeteoroloxica {
    CIELO("ceo"), LLUVIA("pchoiva"), TEMPERATURA_MAXIMA ("tmaxFranxa"),
    TEMPERATURA_MINIMA("tminFranxa"), VIENTO ("vento");

    String variable;

    VariableMeteoroloxica(String var){
        this.variable = var;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public static VariableMeteoroloxica getVariable(String var){
        for (VariableMeteoroloxica v:VariableMeteoroloxica.values()){
            if (var.equals(v.getVariable())){
                return v;
            }

        }
        return null;
    }
}
