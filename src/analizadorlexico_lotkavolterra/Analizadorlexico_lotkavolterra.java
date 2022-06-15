/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico_lotkavolterra;

/**
 *
 * @author Darkside
 */
import java.util.ArrayList;
import java.util.List;

public class Analizadorlexico_lotkavolterra {

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

    public Analizadorlexico_lotkavolterra(String fuente) {
        this.fuente = fuente;
    }

    private void iniciarProceso() throws Exception {
        caracter = fuente.charAt(posicion);
        //System.out.println(caracter);
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
                } else if (caracter == ',') {
                    nombreEstadosCero("COMA");
                } // else if (caracter == 'D' || caracter == 'x' || caracter == 'y') {
                //   estado = 1;
                // lexema += Character.toString(caracter);
                //        } 
                else if(Character.isLetter(caracter)) {
                    estado = 1;
                    lexema += Character.toString(caracter);
                }
                else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {

                } else if (Character.isDigit(caracter)) {
                    estado = 2;
                    lexema += Character.toString(caracter);
                } else {
                    estadoError = true;
                    System.out.println("Error No se encuentra caracter"+caracter.toString());
                    throw new ExceptionError("Token desconocido : " + caracter);
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
                } else if (caracter == ',') {
                    nombresEstados("DERIVADA", "COMA");
                    estado = 0;
                }//else if (caracter == 'D' || caracter == 'x' || caracter == 'y') {
                //7  lexema += Character.toString(caracter);
                else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {
                    if (preservada.contains(lexema)) {
                        listaTokens.add("DERIVADA");
                        listaLexema.add(lexema);
                        estado = 0;
                        lexema = "";
                    } else if (preservada1.contains(lexema)) {
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
                }
                  else if(Character.isLetter(caracter)) {
                    estado = 1;
                    lexema += Character.toString(caracter);
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
                } else if (caracter == ',') {
                    nombresEstados("NUMERO", "COMA");
                    estado = 0;
                    
                }
                  else if(Character.isLetter(caracter)) {
                    estado = 1;
                    lexema += Character.toString(caracter);
                }
                else if (Character.isDigit(caracter)) {
                    lexema += Character.toString(caracter);
                } else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {
                     if (preservada.contains(lexema)) {
                        listaTokens.add("DERIVADA");
                        listaLexema.add(lexema);
                        estado = 0;
                        lexema = "";
                    } else if (preservada1.contains(lexema)) {
                        listaTokens.add("DERIVADA");
                        listaLexema.add(lexema);
                        estado = 0;
                        lexema = "";
                    } else {
                        listaLexema.add(lexema);
                        listaTokens.add("NUMERO");
                        estado = 0;
                        lexema = "";
                    }
//                    listaLexema.add(lexema);
//                    listaTokens.add("NUMERO");
//                    estado = 0;
//                    lexema = "";
//                } else {
                    //  estadoError = true;
                    // System.out.println("ERROR");

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
        // TODO code application logic here
        try {
            Analizadorlexico_lotkavolterra analizarVolt = new Analizadorlexico_lotkavolterra(" Dy  20 ;  Dy =30  ");
            analizarVolt.iniciarProceso();
            analizarVolt.imprimir();
        } catch (Exception e) {
        }

    }

}
