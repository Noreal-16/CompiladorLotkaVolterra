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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sintactico {
    public static String URL = "datos"+ File.separatorChar;

    public static void GeneradorLexer(){
        /**
         * Generador de Sym, Sintex, LexerCup
         */

        String [] rutas = {URL+"Lexer.flex"};
        String [] rutasS = {"-parser","Sintax",URL+"sintax.cup"};
        try {
            java_cup.Main.main(rutasS);
            jflex.Main.generate(rutas);
            ///////////////////////////////
            Path sym = Paths.get(System.getProperty("user.dir") + File.separatorChar + "src"+File.separatorChar + "AnalizadorSintactico"
            + File.separatorChar + "sym.java");
            Path sintax = Paths.get(System.getProperty("user.dir") + File.separatorChar + "src"+File.separatorChar + "AnalizadorSintactico"
            + File.separatorChar + "Sintax.java");
            Path lexer = Paths.get(System.getProperty("user.dir") + File.separatorChar + "src"+File.separatorChar + "AnalizadorSintactico"
            + File.separatorChar + "LexerCup.java");
            /*
            Eliminar archivos
             */
            eliminar(sym);
            eliminar(sintax);
            eliminar(lexer);
            /**
             * Mover Archivos
             */
            Files.move(Paths.get(System.getProperty("user.dir") + File.separatorChar + "sym.java"), sym);
            Files.move(Paths.get(System.getProperty("user.dir") + File.separatorChar + "Sintax.java"), sintax);
            Files.move(Paths.get(URL+"LexerCup.java"),lexer);
            
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

        /**
         * Analizador Sintactico
         * ECUACIONES
         *   Dx = A*x-B*x*y;
         *   Dy = Dxy - Cy;
         *  a = 12; b=13;c = 12; d = 0.5; Dx = 12 * 5 - 12*8*9; PRINT Dx AND Dy;
         */
        //GeneradorLexer();
        String cadena = " TCP=0.2; TCD=0.5; ECAD=0.7; ECDP=0.3; a1 = 12;  r1=13; a2 = 12;  r2=13; presas= 2;  depredadores = 1; " +
                        " Dx = TCP * presas  - (ECDP*presas*depredadores); Dy = (ECAD*presas*depredadores) - TCD * depredadores; ";
        LexerCup lexerCup = new LexerCup(new StringReader(cadena));
        Sintax s = new Sintax(lexerCup);
        try {
            s.parse();
            generateTable.imprimirLista();
            Utilidades.imprimirVariables();
            Utilidades.imprimirLotkaVolterra();
            Utilidades.validarEntradas();
        }catch (Exception e){
            generateTable.imprimirLista();
            System.out.println("Hay un ERROR " + e);
            Symbol sym = s.getS();
            if(sym != null){
                System.out.println("Error Syntactico en la linea " + (sym.right + 1) + " Columna " +  (sym.left + 1) + " Texto " + (sym.value));
            }
        }

    }
    
    private static void eliminar(Path ruta){
        try {
            if (Files.exists(ruta)){
                Files.delete(ruta);
            }
        }catch (Exception e){

        }
    }
}
