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

    public static Integer operaciones(Integer numero1, Integer numero2, Integer numero3, Integer numero4, Integer numero5, Character t1, Character t2,Character t3,Character t4) {
        Integer res = 0;
        System.out.println(t1 + " "+ t2 + " "+ t3 + " "+ t4);
        if (t1 == '*' && t2 == '-' && t3=='*' && t4=='*' ) {
            res = numero1 * numero2 - numero3*numero4*numero5;
        } else if (t1 == '*' && t2 == '*' && t3=='-' && t4=='*') {
            res = numero1 * numero2 * numero3 - numero4 * numero5;
        }
        System.out.println("El resultado es " + res);
        return res;
    }
     public static Integer operaciones2(Integer numero1, Integer numero2, Integer numero3, Integer numero4, Integer numero5, Character t1, Character t2,Character t3,Character t4,Character t5) {
        Integer res = 0;
        System.out.println(t1 + " "+ t2 + " "+ t3 + " "+ t4 + " "+ t5);
        if ( t1 == '-' && t2 == '*' && t3=='+' && t4=='*' && t5=='*') {
            res = - numero1 * numero2 + numero3 * numero4* numero5;
        } else if (t1 == '*' && t2 == '*' && t3=='-' && t4=='*'  && t5=='*') {
            res = numero1 * numero2 * numero3 - numero4 * numero5;
        }
        System.out.println("El resultado es " + res);
        return res;
    }
     
        public static Integer operaciones3(Integer numero1, Integer numero2, Integer numero3, Integer numero4, Integer numero5, Character t1, Character t2,Character t3,Character t4,Character t5) {
        Integer res = 0;
        System.out.println(t1 + " "+ t2 + " "+ t3 + " "+ t4 + " "+ t5);
        if ( t1 == '-' && t2 == '*' && t3=='+' && t4=='*' && t5=='*') {
            res = - numero1 * numero2 + numero3 * numero4* numero5;
        } 
        System.out.println("El resultado es " + res);
        return res;
       }
}



