package Sintactico;

import Exceptions.VariableDeclaradaException;

import java.util.ArrayList;
import java.util.List;

public class Utilidades {
    public static List<Variable> variables = new ArrayList<>();

    public static void addVarible(String nombre, String valor) {
        Variable variable = new Variable(nombre, valor);
        variables.add(variable);
    }

    public static void  imprimirVariables(){
        for (int i = 0; i < variables.size(); i++) {
            System.out.println(variables.get(i).getNombre() + " " + variables.get(i).getValor());
        }
    }
    public static void validarEntradas() throws VariableDeclaradaException {
        int contador = 0;
        for (int i = 1; i < variables.size(); i++) {
            if (variables.get(contador).getNombre().equals(variables.get(i).getNombre()) && !(variables.get(i).getNombre().isEmpty())) {
                throw new VariableDeclaradaException("La Variable " + " -> " + variables.get(contador).getNombre() + " ya se encuentra definida ");
            }
        }
        contador++;
    }

   public static List resultadoPresa = new ArrayList();
   public static List resultadoDepredador = new ArrayList();

    public static Double operaciones(Double numero1, Double presa, Double numero3, Double depredador, Double numero4, Character t1, Character t2, Character t3, Character t4) {
        Double resPresa = 0.0;
        Double derPresa = 0.0;
        Double resDepredaor = 0.0;
        Double derDepredador = 0.0;
        if (t1 == '*' && t2 == '-' && t3 == '*' && t4 == '*') {
            resPresa =(double)(numero1 * presa - numero3 * presa * depredador);
            derPresa = (double)(presa * (numero1 - numero3* depredador ));
            resultadoPresa.add(derPresa);
            return derPresa;
        } else if (t1 == '*' && t2 == '*' && t3 == '-' && t4 == '*') {
            resDepredaor = (double)(numero1 * presa * depredador - numero4*depredador);
            derDepredador= (double)(depredador * (numero1 *presa - numero4 ));
            resultadoDepredador.add(derDepredador);
            return derDepredador;
        }
        System.out.println("El resultado es " + derPresa);
        return derPresa;
    }
    public static void imprimirResultado(String derPresa, String derDepredador){
        for (int i = 0; i < resultadoPresa.size(); i++) {
            derPresa = resultadoPresa.get(i).toString();
            System.out.println("La cantidad de presas son : " + derPresa);
        }
        for (int i = 0; i < resultadoDepredador.size(); i++) {
            derDepredador =resultadoDepredador.get(i).toString();
            System.out.println("La cantidad de depredadores son : " + derDepredador);
        }
    }

    public static void OperacionLista (){
        double r1 = Double.parseDouble(variables.get(0).getValor().toString());
        double a1 = Double.parseDouble(variables.get(1).getValor().toString());
        double a2 = Double.parseDouble(variables.get(2).getValor().toString());
        double r2 = Double.parseDouble(variables.get(3).getValor().toString());
        double presa = Double.parseDouble(variables.get(4).getValor().toString());
        double depredador = Double.parseDouble(variables.get(5).getValor().toString());



    }
}
