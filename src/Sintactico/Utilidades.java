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

    public static Integer operaciones(Integer numero1, Integer presa, Integer numero3, Integer depredador, Integer numero4, Character t1, Character t2, Character t3, Character t4) {
        Integer resPresa = 0;
        Integer derPresa = 0;
        Integer resDepredaor = 0;
        Integer derDepredador = 0;
        if (t1 == '*' && t2 == '-' && t3 == '*' && t4 == '*') {
            resPresa =(int)(numero1 * presa - numero3 * presa * depredador);
            derPresa = (int)(presa * (numero1 - numero3* depredador ));
            resultadoPresa.add(derPresa);
            return derPresa;
        } else if (t1 == '*' && t2 == '*' && t3 == '-' && t4 == '*') {
            resDepredaor = (int)(numero1 * presa * depredador - numero4*depredador);
            derDepredador= (int)(depredador * (numero1 *presa - numero4 ));
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
}
