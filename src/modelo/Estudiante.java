
package modelo;

public class Estudiante {

    // ==============================
    // 1. ATRIBUTOS
    // ==============================
    private String nombre;
    private String codigoUniversitario;
    private String dni;

    // ==============================
    // 2. CONSTRUCTOR ORIGINAL
    // Compatible con el código anterior
    // ==============================
    public Estudiante(String nombre, String codigoUniversitario) {
        this(nombre, codigoUniversitario, "");
    }

    // ==============================
    // 3. CONSTRUCTOR CON DNI
    // ==============================
    public Estudiante(String nombre, String codigoUniversitario, String dni) {
        setNombre(nombre);
        setCodigoUniversitario(codigoUniversitario);
        setDni(dni);
    }

    // ==============================
    // 4. GETTERS Y SETTERS
    // ==============================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getCodigoUniversitario() {
        return codigoUniversitario;
    }

    public void setCodigoUniversitario(String codigoUniversitario) {
        if (codigoUniversitario == null || codigoUniversitario.trim().isEmpty()) {
            throw new IllegalArgumentException("El código universitario no puede estar vacío.");
        }
        this.codigoUniversitario = codigoUniversitario.trim();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            dni = "";
        }
        this.dni = dni.trim();
    }

    // ==============================
    // 5. MOSTRAR INFORMACIÓN
    // ==============================
    @Override
    public String toString() {
        return "Estudiante: " + nombre
                + " | Código universitario: " + codigoUniversitario
                + " | DNI: " + (dni.isEmpty() ? "No registrado" : dni);
    }
}
