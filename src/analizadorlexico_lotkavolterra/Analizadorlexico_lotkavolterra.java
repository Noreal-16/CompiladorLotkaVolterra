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

    /**
     * @param args the command line arguments
     */
    private String fuente;
    private String lexema = "";
    private Integer estado = 0;
    private Integer posicion = 0;
    private Character caracter = ' ';
    private List<String> listaTokens = new ArrayList<>();
    private List<String> listaLexema = new ArrayList<>();

    public Analizadorlexico_lotkavolterra(String fuente) {
        this.fuente = fuente;
    }

    public void iniciarProceso() throws Exception{
        caracter = fuente.charAt(posicion);
        switch (estado) {
            case 0:{
                if (caracter == ',') {
                    nombreEstadoCero("P_COMA");
                } else if (caracter == '(') {
                    nombreEstadoCero("PARENTESIS_A");
                } else if (caracter == ')') {
                    nombreEstadoCero("PARENTESIS_B");
                } else if (caracter == '=') {
                    nombreEstadoCero("ASIGNACION");
                } else if (caracter == '*') {
                    nombreEstadoCero("MULTIPLICACION");
                } else if (caracter == '-') {
                    nombreEstadoCero("RESTA");
                } else if (caracter == '+') {
                    nombreEstadoCero("SUMA");
                } else if (caracter == 'D' || caracter == 'x' || caracter == 'y') {
                    estado = 1;
                    lexema += Character.toString(caracter);
                } else if (Character.isDigit(caracter)) {
                    estado = 2;
                    lexema += Character.toString(caracter);
                } else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {

                } else {
           
                   System.out.println("Error"+ caracter.toString());
                   throw new ExceptionError("Desconocido: " +caracter);
                    
                }
                break;
            }
            case 1:{
                if (caracter == ';') {
                    nombresEstados("DERIVADA ", " P_COMA");}
                else if(caracter == ';'){
                     nombresEstados("DERIVADA ", " P_COMA");                  
                } else if (caracter == '(') {
                    nombresEstados("DERIVADA ", " PARENTESIS_A");
                } else if (caracter == ')') {
                    nombresEstados("DERIVADA ", " PARENTESIS_C");
                } else if (caracter == '=') {
                    nombresEstados("DERIVADA ", " ASIGNACION");
                } else if (caracter == '*') {
                    nombresEstados("DERIVADA ", " MULTIPLICACION");
                } else if (caracter == '-') {
                    nombresEstados("DERIVADA ", " RESTA");
                } else if (caracter == '+') {
                    nombresEstados("DERIVADA ", " SUMA");
                } else if (caracter == 'D' || caracter == 'x' || caracter == 'y') {
                    lexema += Character.toString(caracter);
                } else if (caracter == '\n' || caracter == '\t' || caracter == ' ') {
                    listaLexema.add(lexema);
                    listaTokens.add("DERIVADA");
                    estado = 0;
                    lexema = "";                  
                } 
                else if(caracter == ':' ){
                    //System.out.println("ERROR");
                    throw new ExceptionError("Caracter desconocido "+caracter);
                }
                else {
                         throw new ExceptionError("Desconocido: " +caracter);
                }
                break;
            }
            case 2:{
                 if(caracter == ';'){
                    nombresEstados("NUMERO "," P-COMA");
                    estado = 0;
                }else if (caracter == '('){
                    nombresEstados("NUMERO "," PARENTESIS_A");
                    estado = 0;
                } else if (caracter == ')') {
                    nombresEstados("NUMERO "," PARENTESIS_C");
                    estado = 0;
                } else if (caracter == '=') {
                    nombresEstados("NUMERO "," ASIGNACION");
                    estado = 0;
                }else if (caracter == '*') {
                    nombresEstados("NUMERO "," MULTIPLICACION");
                    estado = 0 ;
                }else if (caracter == '-') {
                    nombresEstados("NUMERO "," RESTA");
                    estado = 0 ;
                }else if (caracter == '+') {
                    nombresEstados("NUMERO "," SUMA");
                    estado = 0 ;
                }else if(Character.isDigit(caracter)){
                    lexema += Character.toString(caracter);
                }else if(caracter == '\n' || caracter == '\t' || caracter == ' '){
                    listaLexema.add(lexema);
                    listaTokens.add("NUMERO");
                    estado = 0;
                    lexema = "";
                }else  {
                   System.out.println("Error:  "+ caracter.toString());
                   throw new ExceptionError("Desconocido: " +caracter);
                }
                break;
            }
                default:break;
        }
        posicion++;
        if(posicion>=fuente.length()){
            //TODO
        }else{
            iniciarProceso();
        }
    }

    private void imprimir() throws Exception{
        for (int i = 0; i < listaLexema.size(); i++) {
            System.out.println("TOKEN : " + listaTokens.get(i) + "  LEXEMA : " + listaLexema.get(i));

        }
    }

    private void nombresEstados(String nToken, String nLexema) {
        listaTokens.add(nToken);
        listaLexema.add(lexema);
        listaTokens.add(nLexema);
        listaLexema.add(caracter.toString());
        lexema = "";
    }

    private void nombreEstadoCero(String token) {
        lexema += Character.toString(caracter);
        listaTokens.add(token);
        listaLexema.add(lexema);
        lexema = "";
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Analizadorlexico_lotkavolterra analizarVolt= new Analizadorlexico_lotkavolterra("  Dx / = 20 + 30 ");
        try {
            analizarVolt.iniciarProceso();
            analizarVolt.imprimir();
        } catch (Exception e) {
        }
    
    }
  
}
