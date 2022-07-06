package Sintactico;

import java.util.ArrayList;
import java.util.List;

public class generateTable {
    public static List lista = new ArrayList();
    public static List lista1 = new ArrayList();
    public static List lista2= new ArrayList();
    public static List lista3 = new ArrayList();

    public static List getLista() {
        return lista;
    }

    public static void setLista(List lista) {
        generateTable.lista = lista;
    }

    public static List getLista1() {
        return lista1;
    }

    public static void setLista1(List lista1) {
        generateTable.lista1 = lista1;
    }

    public static List getLista2() {
        return lista2;
    }

    public static void setLista2(List lista2) {
        generateTable.lista2 = lista2;
    }

    public static List getLista3() {
        return lista3;
    }

    public static void setLista3(List lista3) {
        generateTable.lista3 = lista3;
    }
    public static void addArrayList(String dato) {
        lista.add(dato);
    }

    public static void addArrayList1(String dato) {
        lista1.add(dato);
    }

    public static void addArrayList2(Integer dato) {
        lista2.add(dato);
    }

    public static void addArrayList3(Integer dato) {
        lista3.add(dato);
    }
    public static void imprimirLista(){
        for (int i = 0 ; i< generateTable.lista.size(); i++){
            System.out.println(generateTable.lista.get(i) + "\t" + generateTable.lista1.get(i) + "\t"
                    +generateTable.lista2.get(i) + "\t" + generateTable.lista3.get(i) + "\t"  + "imprimio" );
        }
    }
}
