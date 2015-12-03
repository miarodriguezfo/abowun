package com.example.miguelrodriguez.abowun;

import java.util.ArrayList;

/**
 * Created by Hogar on 27/10/2015.
 */
public class Ave {

    public String foto; // aun no se el tipo de dato que es una imagen
    public String nombreComun;
    public String nombreCientifico;
    public String especie;
    public String caracteristicas;
    public String datoCurioso;
    public static ArrayList<Ave> listaGeneralDeAves; // donde se van a almacenar todas las aves
    public static ArrayList<Ave> avesAMostrar;// donde se van a poner los posibles resultados del servidor

    public Ave(String foto, String nombreComun, String nombreCientifico, String especie, String caracteristicas, String datoCurioso){
        this.foto = foto;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.especie = especie;
        this.caracteristicas = caracteristicas;
        this.datoCurioso = datoCurioso;
    }

    public ArrayList<Ave> seleccionarResultados(String[] id){// este metodo pondra los posibles resultados en el arreglo avesAMostrar y lo retornara
        for(int i = 0; i < id.length; i++){ // suponiendo que el id es el nombre cientifico. Si no lo es, cambiar
            if(id[i].equals(listaGeneralDeAves.get(i).nombreCientifico)){
                avesAMostrar.add(listaGeneralDeAves.get(i));
            }
        }
        return avesAMostrar;
    }

    public static String[] obtenerNombresDeLasSeleccionadas(){
        String[] list = new String[avesAMostrar.size()];
        for(int i=0; i<avesAMostrar.size(); i++){
            list[i]= avesAMostrar.get(i).nombreCientifico;
        }
        return list;
    }

    public static Ave mostrarAve(String nombre){// retornar un ave seleccionada para mostrar
        for (int i = 0; i < listaGeneralDeAves.size(); i++) {
            Ave a = listaGeneralDeAves.get(i);
            if(a.nombreCientifico.equals(nombre)){
                return a;
            }
        }
        return null;
    }

    public String getFoto(){return foto;}
    public String getNombreComun(){return nombreComun;}
    public String getNombreCientifico(){return nombreCientifico;}
    public String getEspecie(){return especie;}
    public String getCaracteristicas(){return caracteristicas;}
    public String getDatoCurioso(){return datoCurioso;}
}
