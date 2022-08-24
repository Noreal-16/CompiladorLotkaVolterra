package Sintactico;

public class Variable {
    private String nombre;
    private Object valor;

    public Variable(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    public Variable(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getValor() {
        return valor;
    }

    // Setter
    public void setValor(Object valor) {
        this.valor = valor;
    }
}
