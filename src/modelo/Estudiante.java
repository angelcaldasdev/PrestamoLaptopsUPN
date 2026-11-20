package modelo;

public class Estudiante {

    // Atributos de la clase
    private String nombre;
    private String codigoUniversitario;

    // Constructor
    public Estudiante(String nombre, String codigoUniversitario) {
        this.nombre = nombre;
        this.codigoUniversitario = codigoUniversitario;
    }

    // Getter y Setter del nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter del código universitario
    public String getCodigoUniversitario() {
        return codigoUniversitario;
    }

    public void setCodigoUniversitario(String codigoUniversitario) {
        this.codigoUniversitario = codigoUniversitario;
    }

    // Mostrar información del estudiante
    @Override
    public String toString() {
        return "Nombre: " + nombre 
                + " | Código: " + codigoUniversitario;
    }
    
    
}