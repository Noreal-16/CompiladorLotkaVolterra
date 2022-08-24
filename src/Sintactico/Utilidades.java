package Sintactico;

import Exceptions.VariableDeclaradaException;
import org.apache.tools.ant.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utilidades {
    public static List<Variable> variables = new ArrayList<>();

    public static void addVarible(String nombre, String valor) {
        Variable variable = new Variable(nombre, valor);
        variables.add(variable);
    }

    public static void imprimirVariables() {
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

    public static void OperacionLotkaVolterra(List<Double> presas) {
        double t0 = 0.0;
        int n = 1000;
        double tn = 100;

        double h = 0.1;
        double f0, f1;

        double presasV = presas.get(1);
        double depredadores = presas.get(4);
        for (int i = 1; i <= 2; i++) {
            System.out.println(presasV + " + " + h + " * " + presas.get(0) + " * " + presasV + " - ( " + presas.get(2) + " * " + presasV + " * " + depredadores + ")");
            f0 = presasV + (h * ((presas.get(0) * presasV) - (presas.get(2) * presasV * depredadores)));
            f1 = depredadores + (h * ((presas.get(5) * presasV * (depredadores - presas.get(8) * depredadores))));
            double presasPonderado = f0 * 1000;
            double depredadorPonderado = f1 * 1000;
            resultadoPresa.add(presasPonderado);
            resultadoDepredador.add(depredadorPonderado);

            presasV = f0;
            depredadores = f1;
        }
    }

    public static void imprimirLotkaVolterra() {
        int contador = 0;
        for (int i = 0; i < resultadoPresa.size(); i++) {
            contador = i + 1;
            System.out.println("Presas anios " + contador + " => " + resultadoPresa.get(i));
        }
        for (int i = 0; i < resultadoDepredador.size(); i++) {
            contador = i + 1;
            System.out.println("Depredadores anios " + contador + " => " + resultadoDepredador.get(i));
        }
    }

    public static Boolean comparacionLitas(List<String> lista2) {
        List<String> nomVariables = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++) {
            nomVariables.add(variables.get(i).getNombre());
        }
        Collections.sort(nomVariables);
        Collections.sort(lista2);

        System.out.println("Nombres de variables " + nomVariables.toString());
        return lista2.equals(nomVariables);
    }

}
