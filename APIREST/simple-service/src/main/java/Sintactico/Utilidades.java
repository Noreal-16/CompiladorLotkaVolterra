package Sintactico;

import Exceptions.ValorEnteroException;
import Exceptions.VariableDeclaradaException;
import Exceptions.VariableNulaException;
import org.apache.tools.ant.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;



public class Utilidades {
    public static List<Variable> variables = new ArrayList<>();

    /**
     * Metodo para agregar variables
     *
     * @param nombre
     * @param valor
     */
    public static void addVarible(String nombre, String valor) {
        Variable variable = new Variable(nombre, valor);
        variables.add(variable);
    }

    /**
     * Metodo para validar que no se repitan las variables
     *
     * @throws VariableDeclaradaException
     */
    public static void validarEntradas() throws VariableDeclaradaException {
        for (int i = 0; i < variables.size() - 1; i++) {
            for (int j = i + 1; j < variables.size(); j++) {
                if (variables.get(i).getNombre().equals(variables.get(j).getNombre())) {
                    //variables.clear();
                    throw new VariableDeclaradaException("La Variable " + " -> " + variables.get(j).getNombre() + " ya se encuentra definida ");
                }
            }
        }
    }
    public static Boolean verificar(String  nombre)throws VariableNulaException{
        if(buscarPosicion(nombre) >= 0){
            return true;
        }else
            throw new VariableNulaException("La varibale: " +nombre+" "+"no esta definida");
        
       
    }
    public static Integer buscarPosicion(String nombre){
        Integer pos = -1;
        Integer cont = 0;
        
        for(Variable aux : variables){
            if(aux.getNombre().equals(nombre)){
                pos = cont;
                break;
            }
            cont++;
        }
        
        return pos;
        
    } 

    public static List resultadoPresa = new ArrayList();
    public static List resultadoDepredador = new ArrayList();
    public static String result;

    /**
     * Metodo para operar valores enviados por el usuario
     *
     * @param presas
     */
    public static void OperacionLotkaVolterra(List<Double> presas, String tiempo) throws ValorEnteroException {

        List<Double> valoresPresas = new ArrayList<>();
        List<Double> valoresDepredador = new ArrayList<>();
        valoresPresas.add(presas.get(1));
        valoresPresas.add(presas.get(3));
        valoresPresas.add(presas.get(6));
        valoresDepredador.add(presas.get(4));
        valoresDepredador.add(presas.get(7));
        valoresDepredador.add(presas.get(9));
        double t0 = 0.0;
        int n = 1000;
        double tn = 100;

        double h = 0.01;
        double f0, f1;

        int tiemposEvolucion = 0;
        try {
            System.out.println("----Tiempo" + tiempo);
            for (int i = 0; i <variables.size(); i++) {
            if (tiempo.equals(variables.get(i).getNombre())) {
                
                tiemposEvolucion = Integer.parseInt(variables.get(i).getValor().toString());
            }
        }
        } catch (Exception e) {
            //variables.clear();
            throw new ValorEnteroException("La Variable " + " -> " + tiempo + " debe ser unicamente entero ");
        }

        double presasV = presas.get(1);
        double depredadores = presas.get(4);
        for (int i = 1; i <= tiemposEvolucion; i++) {
            f0 = valoresPresas.get(0) + (h * ((presas.get(0) * valoresPresas.get(0)) - (presas.get(2) * valoresPresas.get(1) * valoresDepredador.get(0))));
            f1 = valoresDepredador.get(0) + (h * ((presas.get(5) * valoresPresas.get(2) * (valoresDepredador.get(1) - presas.get(8) * valoresDepredador.get(2)))));

            double presasPonderado = f0 * 1000;
            double depredadorPonderado = f1 * 1000;

            resultadoPresa.add(presasPonderado);
            resultadoDepredador.add(depredadorPonderado);

            //Cambiar valores de presas
            valoresPresas.set(0, f0);
            valoresPresas.set(1, f0);
            valoresPresas.set(2, f0);
            //Cambiar valores de depredadores
            valoresDepredador.set(0, f1);
            valoresDepredador.set(1, f1);
            valoresDepredador.set(2, f1);

        }
    }

    /**
     * Metodo para operar variables con presa-depredador
     *
     * @param lista
     */
    public static void operarVariables(List<String> lista, String tiempo) throws ValorEnteroException, VariableDeclaradaException {
        Double tcp = 0.0, presa = 0.0, ecdp = 0.0, depredador = 0.0, ecad = 0.0, tcd = 0.0;
        validarEntradas();

        for (int i = 0; i < variables.size(); i++) {
            if (lista.get(0).equals(variables.get(i).getNombre())) {
                tcp = Double.parseDouble(variables.get(i).getValor().toString());
            } else if (lista.get(1).equals(variables.get(i).getNombre())) {
                presa = Double.parseDouble(variables.get(i).getValor().toString());
            } else if (lista.get(2).equals(variables.get(i).getNombre())) {
                ecdp = Double.parseDouble(variables.get(i).getValor().toString());
            } else if (lista.get(3).equals(variables.get(i).getNombre())) {
                depredador = Double.parseDouble(variables.get(i).getValor().toString());
            } else if (lista.get(4).equals(variables.get(i).getNombre())) {
                ecad = Double.parseDouble(variables.get(i).getValor().toString());
            } else if (lista.get(5).equals(variables.get(i).getNombre())) {
                tcd = Double.parseDouble(variables.get(i).getValor().toString());
            }
        }
        double t0 = 0.0;
        int n = 1000;
        double tn = 100;

        double h = 0.01;
        double f0, f1;
        double valorP = presa;
        double valorD = depredador;

        int tiemposEvolucion = 0;
        
         try {
            for (int i = 0; i <variables.size(); i++) {
            if (tiempo.equals(variables.get(i).getNombre())) {
                tiemposEvolucion = Integer.parseInt(variables.get(i).getValor().toString());
            }
        }
        } catch (Exception e) {
            //variables.clear();
            throw new ValorEnteroException("La Variable --- " + " -> " + tiempo + " debe ser unicamente entero ");
        }

        for (int i = 1; i <= tiemposEvolucion; i++) {
            f0 = valorP + (h * ((tcp * valorP) - (ecdp * valorP * valorD)));
            f1 = valorD + (h * ((ecad * valorP * valorD) - (tcd * valorD)));

            double presasPonderado = f0 * 1000;
            double depredadorPonderado = f1 * 1000;

            valorP = f0;
            valorD = f1;

            resultadoPresa.add(presasPonderado);
            resultadoDepredador.add(depredadorPonderado);
        }
    }

    /**
     * Metodo para imprimir resultado Presa Depredador
     */
    public static List <String> resultadoVariables = new ArrayList<>();
    

    public static void imprimirLotkaVolterra(String ec1, String ec2) {
        try {
            double valorDepredador = 0.0;
            double valorPresa = 0.0;
            for (int i = 0; i < resultadoPresa.size(); i++) {
                for (int j = 0; j < resultadoDepredador.size(); j++) {
                    valorPresa = Double.parseDouble(resultadoPresa.get(i).toString());
                    valorDepredador = Double.parseDouble(resultadoDepredador.get(j).toString());
                }
            }
            for (int i = 0; i <variables.size() ; i++) {
                if (variables.get(i).getNombre().contains("tiempo") | variables.get(i).getNombre().contains("tmp") | variables.get(i).getNombre().contains("time") |variables.get(i).getNombre().contains("TIEMPO")) {

                    result = "Presas en el  anios " + variables.get(i).getValor() + " => " + ec1 + " " + valorPresa + " Depredadores en el anio " + variables.get(i).getValor() + " => " + ec2 + " " + valorDepredador;System.out.println("Presas en el  anios " + variables.get(i).getValor() + " => " + ec1 + " " + valorPresa + " Depredadores en el anio " + variables.get(i).getValor() + " => " + ec2 + " " + valorDepredador);
                    resultadoVariables.add(result);
                }
            }
        } catch (Exception e) {
        }
        
        variables.clear();
    }
}
