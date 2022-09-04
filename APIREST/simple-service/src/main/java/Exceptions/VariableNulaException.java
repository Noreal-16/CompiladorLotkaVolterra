/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Exceptions;

/**
 *
 * @author franc
 */
public class VariableNulaException extends Exception {

    /**
     * Creates a new instance of <code>VariableNulaException</code> without
     * detail message.
     */
    public VariableNulaException() {
    }

    /**
     * Constructs an instance of <code>VariableNulaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public VariableNulaException(String msg) {
        super(msg);
    }
}
