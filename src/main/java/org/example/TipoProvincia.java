package org.example;

public enum TipoProvincia {
        ACORUÑA ("A Coruña"), LUGO("Lugo"), PONTEVEDRA ("Pontevedra"),
        OURENSE("Ourense");

        String tipoProvincia;

        TipoProvincia(String ti){
            this.tipoProvincia = ti;
        }

        public String getTipo() {
            return tipoProvincia;
        }

        public void setVariable(String ti) {
            this.tipoProvincia = ti;
        }

        public static TipoProvincia getTipo(String t){
            for (TipoProvincia tp: TipoProvincia.values()){
                if (t.equals(tp.getTipo())){
                    return tp;
                }
            }
            return null;
        }
    }


