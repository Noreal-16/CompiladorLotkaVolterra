package Sintactico;


import AnalizadorSintactico.LexerCup;
import AnalizadorSintactico.Sintax;
import java_cup.Lexer;
import java_cup.internal_error;
import java_cup.runtime.Symbol;
import jflex.SilentExit;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class Sintactico {
    public static String URL = "datos"+ File.separatorChar;

    public void GeneradorLexer(){
        /**
         * Generador de Sym, Sintex, LexerCup
         */

        String [] rutas = {URL+"Lexer.flex"};
        String [] rutasS = {"-parser","Sintax",URL+"sintax.cup"};
        try {
            java_cup.Main.main(rutasS);
            jflex.Main.generate(rutas);
        } catch (SilentExit e) {
            System.out.println("ERROR" + e);
            //throw new RuntimeException(e);
        } catch (internal_error e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {

        //new Sintactico().GeneradorLexer();

        //ENTERO a; \n ENTERO b =45; \n ENTERO imprimir (){ENTERO c = 56; PRINT ()};

        /**
         * Analizador Sintactico
         */
       String cadena = " alex \n b \n c ";
        LexerCup lexerCup = new LexerCup(new StringReader(cadena));
        Sintax s = new Sintax(lexerCup);
        try {
            s.parse();
            generateTable.imprimirLista();
        }catch (Exception e){
            generateTable.imprimirLista();
            System.out.println("Hay un ERROR " + e);
            Symbol sym = s.getS();
            if(sym != null){
                System.out.println("Error Syntactico en la linea " + (sym.right + 1) + " Columna " +  (sym.left + 1) + " Texto " + (sym.value));
            }
        }

    }
}