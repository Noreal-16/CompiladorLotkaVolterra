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

        List<Double> valoresPresas = new ArrayList<>();
        List<Double> valoresDepredador = new ArrayList<>();
        if (presas.size() == 10) {
            valoresPresas.add(presas.get(1));
            valoresPresas.add(presas.get(3));
            valoresPresas.add(presas.get(6));
            valoresDepredador.add(presas.get(4));
            valoresDepredador.add(presas.get(7));
            valoresDepredador.add(presas.get(9));
            double t0 = 0.0;
            int n = 1000;
            double tn = 100;

            double h = 0.1;
            double f0, f1;

            double presasV = presas.get(1);
            double depredadores = presas.get(4);
            for (int i = 1; i <= 2; i++) {
                System.out.println(presasV + " + " + h + " * " + presas.get(0) + " * " + presasV + " - ( " + presas.get(2) + " * " + presasV + " * " + depredadores + ")");

            /*f0 = presasV + (h * ((presas.get(0) * presasV) - (presas.get(2) * presasV * depredadores)));
            f1 = depredadores + (h * ((presas.get(5) * presasV * (depredadores - presas.get(8) * depredadores))));*/
                f0 = valoresPresas.get(0) + (h * ((presas.get(0) * valoresPresas.get(0)) - (presas.get(2) * valoresPresas.get(1) * valoresDepredador.get(0))));
                f1 = valoresDepredador.get(0) + (h * ((presas.get(5) * valoresPresas.get(2) * (valoresDepredador.get(1) - presas.get(8) * valoresDepredador.get(2)))));

                double presasPonderado = f0 * 1000;
                double depredadorPonderado = f1 * 1000;

                resultadoPresa.add(presasPonderado);
                resultadoDepredador.add(depredadorPonderado);

                valoresPresas.set(0, f0);
                valoresPresas.set(1, f0);
                valoresPresas.set(2, f0);
                System.out.println("Los valores presas son " + valoresPresas.toString());
                valoresDepredador.set(0, f1);
                valoresDepredador.set(1, f1);
                valoresDepredador.set(2, f1);
                System.out.println("Los valores depredadores son " + valoresDepredador.toString());
            }
        } else if(presas.size() == 6) {
            System.out.println("Los valores de la nueva lista son " + presas);
            double t0 = 0.0;
            int n = 1000;
            double tn = 100;

            double h = 0.1;
            double f0, f1;

            double presasV = presas.get(4);
            double depredadores = presas.get(5);
            for (int i = 1; i <= 2; i++) {
                System.out.println(presasV + " + " + h + " * " + presas.get(0) + " * " + presasV + " - ( " + presas.get(2) + " * " + presasV + " * " + depredadores + ")");


            /*f0 = presasV + (h * ((presas.get(0) * presasV) - (presas.get(2) * presasV * depredadores)));
            f1 = depredadores + (h * ((presas.get(5) * presasV * (depredadores - presas.get(8) * depredadores))));*/
                f0 = presasV + (h * ((presas.get(0) * presasV) - (presas.get(1) * presasV * depredadores)));
                f1 = depredadores + (h * ((presas.get(2) * presasV * (depredadores - presas.get(3) * depredadores))));

                double presasPonderado = f0 * 1000;
                double depredadorPonderado = f1 * 1000;

                resultadoPresa.add(presasPonderado);
                resultadoDepredador.add(depredadorPonderado);

                presasV = f0;
                depredadores = f1;
            }

        }
    }

    public static void operacionVariables() {
        List<String> datosVariables = new ArrayList<>();
        List<Double> valoresOpe = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getNombre().equals("TCP") | variables.get(i).getNombre().equals("TCD") | variables.get(i).getNombre().equals("ECDP") | variables.get(i).getNombre().equals("ECAD") | variables.get(i).getNombre().equals("presas") | variables.get(i).getNombre().equals("depredadores")) {
                valoresOpe.add(Double.parseDouble(variables.get(i).getValor().toString()));
            }
        }
        System.out.println(valoresOpe);
        OperacionLotkaVolterra(valoresOpe);
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
