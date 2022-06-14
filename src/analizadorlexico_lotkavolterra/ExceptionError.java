/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package analizadorlexico_lotkavolterra;

/**
 *
 * @author Darkside
 */
public class ExceptionError extends Exception{

    /**
     * Creates a new instance of <code>ExceptionError</code> without detail
     * message.
     */
    public ExceptionError() {
    }

    /**
     * Constructs an instance of <code>ExceptionError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionError(String msg) {
        super(msg);
    }
}
