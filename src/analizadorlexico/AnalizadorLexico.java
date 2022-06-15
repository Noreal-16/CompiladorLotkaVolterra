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
    private Boolean estadoError=false;
    private String fuente;
    private String lexema = "";
    private Integer estado = 0;
    private Integer posicion = 0;
    private Character caracter = ' ';
    private List<String> listaTokens = new ArrayList<>();
    private List<String> listaLexema = new ArrayList<>();
    public AnalizadorLexico(String fuente){
        this.fuente = fuente;
    }
    private void iniciarProceso (){
        caracter = fuente.charAt(posicion);
        //System.out.println(caracter);
        switch (estado){
            case 0:
                if(caracter == ';'){
                    nombreEstadosCero("P-COMA");
                }else if (caracter == '('){
                    nombreEstadosCero("PARENTESIS_A");
                } else if (caracter == ')') {
                    nombreEstadosCero("PARENTESIS_C");
                } else if (caracter == '=') {
                    nombreEstadosCero("ASIGNACION");
                }else if (caracter == '*') {
                    nombreEstadosCero("MULTIPLICACION");
                }else if (caracter == '-') {
                    nombreEstadosCero("RESTA");
                }else if (caracter == '+') {
                    nombreEstadosCero("SUMA");
                }else if (caracter == 'D' || caracter == 'x' || caracter == 'y') {
                    estado = 1 ;
                    lexema += Character.toString(caracter);
                }else if(Character.isDigit(caracter)){
                    estado = 2;
                    lexema += Character.toString(caracter);
                }else if(caracter == '\n' || caracter == '\t' || caracter == ' '){

                }else  {
                    estadoError = true;
                    System.out.println("Error No se encuentra caracter");
                }
                break;
            case 1:
                if(caracter == ';'){
                    nombresEstados("DERIVADA", "P-COMA");
                    estado = 0;
                }else if (caracter == '('){
                    nombresEstados("DERIVADA", "PARENTESIS_A");
                    estado = 0;
                } else if (caracter == ')') {
                    nombresEstados("DERIVADA", "PARENTESIS_C");
                    estado = 0;
                    listaTokens.add(lexema);
                } else if (caracter == '=') {
                    nombresEstados("DERIVADA", "ASIGNACION");
                    estado = 0;
                }else if (caracter == '*') {
                    nombresEstados("DERIVADA", "MULTIPLICACION");
                    estado = 0 ;
                }else if (caracter == '-') {
                    nombresEstados("DERIVADA", "RESTA");
                    estado = 0 ;
                }else if (caracter == '+') {
                    nombresEstados("DERIVADA", "SUMA");
                    estado = 0 ;
                }else if (caracter == 'D' || caracter == 'x' || caracter == 'y') {
                    lexema += Character.toString(caracter);
                }else if(caracter == '\n' || caracter == '\t' || caracter == ' '){
                    listaLexema.add(lexema);
                    listaTokens.add("DERIVADA");
                    estado = 0;
                    lexema = "";
                }else  {
                    estadoError = true;
                    System.out.println("ERROR");
                }
                break;
            case 2:
                if(caracter == ';'){
                    nombresEstados("NUMERO", "P-COMA");
                    estado = 0;
                }else if (caracter == '('){
                    nombresEstados("NUMERO", "PARENTESIS_A");
                    estado = 0;
                } else if (caracter == ')') {
                    nombresEstados("NUMERO", "PARENTESIS_C");
                    estado = 0;
                } else if (caracter == '=') {
                    nombresEstados("NUMERO", "ASIGNACION");
                    estado = 0;
                }else if (caracter == '*') {
                    nombresEstados("NUMERO", "MULTIPLICACION");
                    estado = 0 ;
                }else if (caracter == '-') {
                    nombresEstados("NUMERO", "RESTA");
                    estado = 0 ;
                }else if (caracter == '+') {
                    nombresEstados("NUMERO", "SUMA");
                    estado = 0 ;
                }else if(Character.isDigit(caracter)){
                    lexema += Character.toString(caracter);
                }else if(caracter == '\n' || caracter == '\t' || caracter == ' '){
                    listaLexema.add(lexema);
                    listaTokens.add("NUMERO");
                    estado = 0;
                    lexema = "";
                }else  {
                    estadoError = true;
                    System.out.println("ERROR");
                }
                break;
            default: break;
        }
        posicion++;

        if (posicion >= fuente.length()){
            //TODO
        }else{
            if (!estadoError){
                iniciarProceso();
            }else {
                System.out.println("Error");
            }
        }
    }
    private void imprimir(){
        for (int i = 0 ; i<listaLexema.size(); i++){
            System.out.println("TOKEN: " + listaTokens.get(i) + " LEXEMA: " + listaLexema.get(i));
        }
    }
    private void nombresEstados(String nToken, String nLexema){
        listaTokens.add(nToken);
        listaLexema.add(lexema);
        listaTokens.add(nLexema);
        listaLexema.add(caracter.toString());
        lexema="";
    }
    private void nombreEstadosCero(String ntoken){
        lexema += Character.toString(caracter);
        listaTokens.add(ntoken);
        listaLexema.add(lexema);
        lexema="";
    }
    public static void main(String[] args) {
        AnalizadorLexico analizarVolt = new AnalizadorLexico("Dx = (10-25) + (25*10); Dy = (1*25) - (15*10);");
        analizarVolt.iniciarProceso();
        analizarVolt.imprimir();
    }
}
