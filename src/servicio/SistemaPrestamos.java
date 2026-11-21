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
    
    public boolean registrarLaptop(Laptop laptop) {

        // Recorrer la lista de laptops
        for (Laptop laptopRegistrado : laptops) {

            // Verificar si el código ya existe
            if (laptopRegistrado.getCodigo()
                .equals(laptop.getCodigo())) {

             return false;
            }
        }

        // Registrar la laptop
        laptops.add(laptop);

        return true;
    }
    
    public Estudiante buscarEstudiante(String codigoUniversitario) {

    // Recorrer la lista de estudiantes
    for (Estudiante estudianteRegistrado : estudiantes) {

        // Comparar los códigos
        if (estudianteRegistrado.getCodigoUniversitario()
        .equals(codigoUniversitario)) {

            // Devolver el estudiante encontrado
            return estudianteRegistrado;
        }
    }

    // Si termina el recorrido sin encontrarlo
    return null;
    }
    
    
    
    public Laptop buscarLaptop(String codigo) {

        // Recorrer la lista de laptops
    for (Laptop laptopRegistrado : laptops) {

        // Comparar los códigos
        if (laptopRegistrado.getCodigo()
        .equals(codigo)) {

            // Devolver el laptop encontrado
            return laptopRegistrado;
        }
    }

    // Si termina el recorrido sin encontrarlo
    return null;
     
    }
   
}