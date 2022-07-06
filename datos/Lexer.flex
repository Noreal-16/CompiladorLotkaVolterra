package AnalizadorSintactico;
import java_cup.runtime.Symbol;
%%
%public
%class LexerCup
%type java_cup.runtine.Symbol
%cup
%full
%line
%char
L=[a-zA-Z]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol (int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol (int type){
            return new Symbol(type, yyline, yycolumn);
        }
%}
%%
{espacio} {/*Ignore*/}
("//"(.)*) {/*Ignore*/}
("Dx | Dy") {return new Symbol(sym.P_RESERVADA, yychar, yyline, yytext());}
("=") {return new Symbol(sym.IGUAL, yychar, yyline, yytext());}
("{") {return new Symbol(sym.LLAVE_A, yychar, yyline, yytext());}
("}") {return new Symbol(sym.LLAVE_C, yychar, yyline, yytext());}
("(") {return new Symbol(sym.PARENTESIS_A, yychar, yyline, yytext());}
(")") {return new Symbol(sym.PARENTESIS_C, yychar, yyline, yytext());}
("+" | "-" | "*" | "/" | "%") {return new Symbol(sym.ARITMETICOS, yychar, yyline, yytext());}
(";") {return new Symbol(sym.P_COMA, yychar, yyline, yytext());}
{L}+ {return new Symbol(sym.NOMBRE, yychar, yyline, yytext());}
{D}+ {return new Symbol(sym.DIGITO, yychar, yyline, yytext());}
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}