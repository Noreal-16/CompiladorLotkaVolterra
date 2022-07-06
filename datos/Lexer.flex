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
L=[a-zA-Z]+[_]?[a-zA-Z0-9]*
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
("Dx" | "Dy") {
          generateTable.addArrayList("P_RESERVADA");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.P_RESERVADA, yychar, yyline, yytext());
      }
("=") {
          generateTable.addArrayList("ASIGNACION");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.ASIGNACION, yychar, yyline, yytext());
      }
("(") {
          generateTable.addArrayList("PARENTESIS_A");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.PARENTESIS_A, yychar, yyline, yytext());
      }
(")") {
          generateTable.addArrayList("PARENTESIS_C");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.PARENTESIS_C, yychar, yyline, yytext());
      }
("+" | "-" | "*" | "/" ) {
          generateTable.addArrayList("ARITMETICOS");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.ARITMETICOS, yychar, yyline, yytext());
      }
(";") {
          generateTable.addArrayList("P_COMA");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.P_COMA, yychar, yyline, yytext());
      }
{L}+ {
          generateTable.addArrayList("NOMBRE");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
         generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.NOMBRE, yychar, yyline, yytext());
      }
{D}+ {
          generateTable.addArrayList("DIGITO");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.DIGITO, yychar, yyline, yytext());
      }
 . {
          generateTable.addArrayList("ERROR");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.ERROR, yychar, yyline, yytext());
      }