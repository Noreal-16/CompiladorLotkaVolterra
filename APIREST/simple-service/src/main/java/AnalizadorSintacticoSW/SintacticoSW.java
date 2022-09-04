/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AnalizadorSintacticoSW;

/**
 *
 * @author leon
 */
import AnalizadorSintactico.LexerCup;
import AnalizadorSintactico.Sintax;
import Sintactico.Utilidades;

import java.io.StringReader;
import java.util.Base64;
import java_cup.runtime.Symbol;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/presadepredador")
public class SintacticoSW {
     @GET
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResult(@PathParam("codigo") String code){
        System.out.println("Lo que me enviaste"+code);
        String codigo = new String (Base64.getDecoder().decode(code));
        LexerCup lexerCup = new LexerCup(new StringReader(codigo));
        Sintax s = new Sintax(lexerCup);
        try {
            //s.parse();
            
            Utilidades.variables.clear();
            System.out.println("json"+ Utilidades.resultadoVariables);
            return Response.ok("{\"message\":\"" +Utilidades.resultadoVariables+"\"}").header("Access-Control-Allow-Origin","*").build();
              //return Response.ok(Utilidades.resultadoVariables).header("Access-Control-Allow-Origin","*").build();

//generateTable.imprimirLista();
        }catch (Exception e){
            //generateTable.imprimirLista();
            
            //e.printStackTrace();
            Symbol sym = s.getS();
            if(sym != null){
                System.out.println("Error Syntactico en la linea " + (sym.right + 1) + " Columna " +  (sym.left + 1) + " Texto " + (sym.value));
                String msg=" Error Syntactico en la linea ";
                String msg1=" Columna ";
                String msg2=" Texto ";
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\""+msg+(sym.right + 1)+msg1+(sym.left + 1)+msg2+(sym.value)+"\"}").header("Access-Control-Allow-Origin","*").build();
            }else{
               System.out.println("Hay un ERROR " + e); 
               
               String msgerror = " Hay un error ";
               return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\""+ msgerror+e+"\"}").header("Access-Control-Allow-Origin","*").build();
            }
            
        }
        
        
        
    }
    
}

