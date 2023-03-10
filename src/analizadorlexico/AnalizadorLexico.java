/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analizadorlexico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class AnalizadorLexico {

    private Boolean estadoError = false;
    private String fuente;
    private String lexema = "";
    private Integer estado = 0;
    private Integer posicion = 0;
    private Character caracter = ' ';
    private List<String> listaTokens = new ArrayList<>();
    private List<String> listaLexema = new ArrayList<>();
    private String preservada = "Dx";
    private String preservada1 = "Dy";

    public AnalizadorLexico(String fuente) {
        this.fuente = fuente;
    }

    private void iniciarProceso() throws Exception {
        caracter = fuente.charAt(posicion);
        switch (estado) {
            case 0:
                if (caracter == ';') {
                    nombreEstadosCero("P-COMA");
                } else if (caracter == '(') {
                    nombreEstadosCero("PARENTESIS_A");
                } else if (caracter == ')') {
                    nombreEstadosCero("PARENTESIS_C");
                } else if (caracter == '=') {
                    nombreEstadosCero("ASIGNACION");
                } else if (caracter == '*') {
                    nombreEstadosCero("MULTIPLICACION");
                } else if (caracter == '-') {
                    nombreEstadosCero("RESTA");
                } else if (caracter == '+') {
                    nombreEstadosCero("SUMA");
                } else if (caracter == 'D' || caracter == 'x' || caracter == 'y') {
                    estado = 1;
                    lexema += Character.toString(caracter);
                    
                }else if(Character.isLetter(caracter))
                {
                    estado=1;
                    lexema+=Character.toString(caracter);
                }
                else if (Character.isDigit(caracter)) {
                    estado = 2;
                    lexema += Character.toString(caracter);
                } else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {

                } else {
                    estadoError = true;
                   throw new ExceptionError("Token desconocido " + caracter.toString());
                }
                break;
            case 1:
                if (caracter == ';') {
                    nombresEstados("DERIVADA", "P-COMA");
                    estado = 0;
                } else if (caracter == '(') {
                    nombresEstados("DERIVADA", "PARENTESIS_A");
                    estado = 0;
                } else if (caracter == ')') {
                    nombresEstados("DERIVADA", "PARENTESIS_C");
                    estado = 0;
                    listaTokens.add(lexema);
                } else if (caracter == '=') {
                    nombresEstados("DERIVADA", "ASIGNACION");
                    estado = 0;
                } else if (caracter == '*') {
                    nombresEstados("DERIVADA", "MULTIPLICACION");
                    estado = 0;
                } else if (caracter == '-') {
                    nombresEstados("DERIVADA", "RESTA");
                    estado = 0;
                } else if (caracter == '+') {
                    nombresEstados("DERIVADA", "SUMA");
                    estado = 0;
                }
                else if(Character.isLetter(caracter)) {
                    estado = 1;
                    lexema += Character.toString(caracter);
                }
                else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {
                     if (preservada.contains(lexema) || preservada1.contains(lexema)) {
                        listaTokens.add("DERIVADA");
                        listaLexema.add(lexema);
                        estado = 0;
                        lexema = "";
                    } else {
                        listaLexema.add(lexema);
                        listaTokens.add("VARIABLE");
                        estado = 0;
                        lexema = "";
                    }
                } else {
                    estadoError = true;
                   throw new ExceptionError("Token desconocido " +caracter.toString());
                }
                break;
            case 2:
                if (caracter == ';') {
                    nombresEstados("NUMERO", "P-COMA");
                    estado = 0;
                } else if (caracter == '(') {
                    nombresEstados("NUMERO", "PARENTESIS_A");
                    estado = 0;
                } else if (caracter == ')') {
                    nombresEstados("NUMERO", "PARENTESIS_C");
                    estado = 0;
                } else if (caracter == '=') {
                    nombresEstados("NUMERO", "ASIGNACION");
                    estado = 0;
                } else if (caracter == '*') {
                    nombresEstados("NUMERO", "MULTIPLICACION");
                    estado = 0;
                } else if (caracter == '-') {
                    nombresEstados("NUMERO", "RESTA");
                    estado = 0;
                } else if (caracter == '+') {
                    nombresEstados("NUMERO", "SUMA");
                    estado = 0;
                } else if (Character.isDigit(caracter) || caracter == ',') {
                    lexema += Character.toString(caracter);
                } else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {
                        listaLexema.add(lexema);
                        listaTokens.add("NUMERO");
                        estado = 0;
                        lexema = "";
                    
                } else {
                    estadoError = true;
                   throw new ExceptionError("Token desconocido " + caracter.toString());
                }
                break;
            default:
                break;
        }
        posicion++;

        if (posicion >= fuente.length()) {
            //TODO
        } else {
            if (!estadoError) {
                iniciarProceso();
            } else {
                System.out.println("Error");
            }
        }
    }

    private void imprimir() {
        for (int i = 0; i < listaLexema.size(); i++) {
            System.out.println("TOKEN: " + listaTokens.get(i) + " LEXEMA: " + listaLexema.get(i));
        }
    }

    private void nombresEstados(String nToken, String nLexema) {
        listaTokens.add(nToken);
        listaLexema.add(lexema);
        listaTokens.add(nLexema);
        listaLexema.add(caracter.toString());
        lexema = "";
    }

    private void nombreEstadosCero(String ntoken) {
        lexema += Character.toString(caracter);
        listaTokens.add(ntoken);
        listaLexema.add(lexema);
        lexema = "";
    }

    public static void main(String[] args) {
        try {
            AnalizadorLexico analizarVolt = new AnalizadorLexico("Dx = (10-25)  + (2,5*10); Dy = (1*25) - (15*-10);");
            analizarVolt.iniciarProceso();
            analizarVolt.imprimir();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
