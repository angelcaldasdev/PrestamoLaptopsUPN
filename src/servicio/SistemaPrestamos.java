package servicio;

import java.util.ArrayList;

import modelo.Estudiante;
import modelo.Laptop;
import modelo.Prestamo;

public class SistemaPrestamos {

    // Atributos
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Laptop> laptops;
    private ArrayList<Prestamo> prestamos;

    // Constructor
    public SistemaPrestamos() {
        estudiantes = new ArrayList<>();
        laptops = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    // Registrar estudiante
    public boolean registrarEstudiante(Estudiante estudiante) {

        // Recorrer la lista de estudiantes
        for (Estudiante estudianteRegistrado : estudiantes) {

            // Verificar si el código ya existe
            if (estudianteRegistrado.getCodigoUniversitario()
                    .equals(estudiante.getCodigoUniversitario())) {

                return false;
            }
        }

        // Registrar al estudiante
        estudiantes.add(estudiante);

        return true;
    }
}