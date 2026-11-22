
package modelo;

import java.time.LocalDate;

public class Prestamo {
    
    // Atributos de la clase
    private Estudiante estudiante;
    private Laptop laptop;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean activo;

    // Constructor
    public Prestamo(Estudiante estudiante, Laptop laptop) {
        this.estudiante = estudiante;
        this.laptop = laptop;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.activo = true;
    }
    
    // Getters 
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean isActivo() {
        return activo;
    }
    
    // Set
    
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
        this.activo = false;
    }
    
    // Método toString()
    @Override
    public String toString() {
        return "Estudiante: " + estudiante
            + " | Laptop: " + laptop
            + " | Fecha de préstamo: " + fechaPrestamo
            + " | Fecha de devolución: "
            + (fechaDevolucion == null ? "Pendiente" : fechaDevolucion)
            + " | Estado: " + (activo ? "Activo" : "Finalizado");
    }
    
}
