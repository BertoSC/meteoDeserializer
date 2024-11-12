package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class AppPrediccion {
    PrediccionDao pd;
    boolean funcionando;
    Scanner sc;

    public AppPrediccion(){
        pd = new PrediccionDao();
        funcionando = true;
        sc = new Scanner(System.in);
        menuApp();
    }

    public void menuApp(){
        System.out.println("PREDICCIONES METEOROLÓGICAS DE GALICIA ");
        System.out.println();
        while (funcionando) {
            System.out.println("1. Consultar predicción de un municipio");
            System.out.println("2. Ver listado de municipios disponibles");
            System.out.println("3. Salir");
            System.out.println();
            System.out.println("Selecciona una opción");
            int opcion = sc.nextInt();
            sc.nextLine();
            ejecutarAccionDeMenu(opcion);
        }
    }

    private void ejecutarAccionDeMenu(int opc) {
        if (opc >=1 && opc <=3){
            switch (opc){
                case 1:
                    recuperarPrediccion();
                    break;
                case 2:
                    recuperarListadoMunicipios();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    System.out.println("Gracias por utilizar la APP");
                    funcionando=false;
            }
        }
    }

    private void recuperarListadoMunicipios() {
        List<Concello> listadoC = pd.getConcelloDao().getListaConcellos();
        for (Concello c:listadoC){
            System.out.println(c);
        }
        preguntaUsuario();
    }

    private void preguntaUsuario(){
        System.out.println("¿Quieres ver la predicción meteorológica de algún municipio? (Y/N)");
        String opc = sc.nextLine().toUpperCase();
        if (opc.equals("Y")){
            recuperarPrediccion();
        } else if (opc.equals("N")){
            System.out.println("Regresando al menú principal...");
        } else {
            System.out.println("Comando desconocido. Introduzca un comando válido");

        }

    }

    private void recuperarPrediccion() {
        System.out.println("Introduce el identificador del municipio");
        int id = sc.nextInt();
        Prediccion seleccionada = null;
        if (comprobarIdConcello(id)){
            seleccionada= pd.getById(id);
            System.out.println();
            System.out.println("PREDICCIÓN METEOROLÓGICA: ");
            System.out.println();
            System.out.println(seleccionada);
        } else {
            System.out.println("No se ha encontrado el dato requerido");
        }

    }

    private Boolean comprobarIdConcello(int idConcello) {
        List<Concello> listadoC = pd.getConcelloDao().getListaConcellos();
        for (Concello c:listadoC){
            if (c.getIdConcello()==idConcello){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new AppPrediccion();
    }
}
