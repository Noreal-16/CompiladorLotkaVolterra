package AnalizadorSintactico;
import java_cup.runtime.Symbol;
import Sintactico.generateTable;
%%
%public
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z]+[_]?[a-zA-Z0-9]*
//D=[0-9]+
D=[0-9]*[.]?[0-9]*
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
("Dx") {
          generateTable.addArrayList("PRESAS");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.PRESAS, yychar, yyline, yytext());
      }
("Dy") {
          generateTable.addArrayList("DEPREDADORES");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.DEPREDADORES, yychar, yyline, yytext());
      }
("PRINT") {
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
("*") {
          generateTable.addArrayList("MULTIPLICACION");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.MULTIPLICACION, yychar, yyline, yytext());
      }
("-") {
          generateTable.addArrayList("RESTA");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.RESTA, yychar, yyline, yytext());
      }
(";") {
          generateTable.addArrayList("P_COMA");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.P_COMA, yychar, yyline, yytext());
      }

("TCD") {
          generateTable.addArrayList("TCD");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.TCD, yychar, yyline, yytext());
      }
("TCP") {
          generateTable.addArrayList("TCP");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.TCP, yychar, yyline, yytext());
      }
("ECDP") {
          generateTable.addArrayList("ECDP");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.ECDP, yychar, yyline, yytext());
      }
("ECAD") {
          generateTable.addArrayList("ECAD");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.ECAD, yychar, yyline, yytext());
      }
("PRESA" | "presa") {
          generateTable.addArrayList("PRESA");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.PRESA, yychar, yyline, yytext());
      }
("DEPREDADOR" | "depredador") {
          generateTable.addArrayList("DEPREDADOR");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.DEPREDADOR, yychar, yyline, yytext());
      }
("AND") {
          generateTable.addArrayList("CONCATENADOR");
          generateTable.addArrayList1(yytext());
          generateTable.addArrayList2(yyline + 1);
          generateTable.addArrayList3(yycolumn  + 1);
          return new Symbol(sym.CONCATENADOR, yychar, yyline, yytext());
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