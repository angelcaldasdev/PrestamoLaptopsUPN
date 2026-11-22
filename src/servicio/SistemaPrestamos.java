
package servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Estudiante;
import modelo.Laptop;
import modelo.Prestamo;

public class SistemaPrestamos {

    // Listas principales
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Laptop> laptops;
    private ArrayList<Prestamo> prestamos;

    // Constructor
    public SistemaPrestamos() {
        estudiantes = new ArrayList<>();
        laptops = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    // F01: Registrar estudiante
    public boolean registrarEstudiante(Estudiante estudiante) {

        // Validar que los datos no sean nulos o estén vacíos
        if (estudiante == null
                || estudiante.getNombre() == null
                || estudiante.getNombre().trim().isEmpty()
                || estudiante.getCodigoUniversitario() == null) {
            return false;
        }

        // Normalizar el código universitario
        String codigo = estudiante.getCodigoUniversitario().trim();

        // Validar que comience con U y tenga 8 caracteres
        if (!codigo.matches("U[A-Za-z0-9]{7}")) {
            return false;
        }

        // Evitar códigos duplicados
        if (buscarEstudiante(codigo) != null) {
            return false;
        }

        // Guardar el código sin espacios
        estudiante.setCodigoUniversitario(codigo);

        // Registrar al estudiante
        estudiantes.add(estudiante);

        return true;
    }

    // F02: Registrar laptop
    public boolean registrarLaptop(Laptop laptop) {

        // Validar los datos de la laptop
        if (laptop == null
                || laptop.getCodigo() == null
                || laptop.getMarca() == null
                || laptop.getModelo() == null
                || laptop.getCodigo().trim().isEmpty()
                || laptop.getMarca().trim().isEmpty()
                || laptop.getModelo().trim().isEmpty()) {
            return false;
        }

        // Normalizar el código de la laptop
        String codigo = laptop.getCodigo().trim();

        // Evitar códigos duplicados
        if (buscarLaptop(codigo) != null) {
            return false;
        }

        // Guardar los datos sin espacios al inicio o al final
        laptop.setCodigo(codigo);
        laptop.setMarca(laptop.getMarca().trim());
        laptop.setModelo(laptop.getModelo().trim());

        // Registrar la laptop
        laptops.add(laptop);

        return true;
    }

    // Buscar estudiante por código universitario
    public Estudiante buscarEstudiante(String codigoUniversitario) {

        if (codigoUniversitario == null) {
            return null;
        }

        codigoUniversitario = codigoUniversitario.trim();

        for (Estudiante estudianteRegistrado : estudiantes) {

            if (estudianteRegistrado.getCodigoUniversitario()
                    .equals(codigoUniversitario)) {
                return estudianteRegistrado;
            }
        }

        return null;
    }

    // Buscar laptop por código
    public Laptop buscarLaptop(String codigo) {

        if (codigo == null) {
            return null;
        }

        codigo = codigo.trim();

        for (Laptop laptopRegistrado : laptops) {

            if (laptopRegistrado.getCodigo().equals(codigo)) {
                return laptopRegistrado;
            }
        }

        return null;
    }

    // F03: Registrar préstamo
    public boolean registrarPrestamo(
            String codigoUniversitario, String codigoLaptop) {

        // Validar los códigos recibidos
        if (codigoUniversitario == null || codigoLaptop == null
                || codigoUniversitario.trim().isEmpty()
                || codigoLaptop.trim().isEmpty()) {
            return false;
        }

        // Normalizar los códigos
        codigoUniversitario = codigoUniversitario.trim();
        codigoLaptop = codigoLaptop.trim();

        // Buscar estudiante y laptop
        Estudiante estudiante = buscarEstudiante(codigoUniversitario);
        Laptop laptop = buscarLaptop(codigoLaptop);

        // Verificar que el estudiante exista
        if (estudiante == null) {
            return false;
        }

        // Verificar que la laptop exista y esté disponible
        if (laptop == null || !laptop.isDisponible()) {
            return false;
        }

        // Verificar que el estudiante no tenga otro préstamo activo
        for (Prestamo prestamo : prestamos) {

            if (prestamo.isActivo()
                    && prestamo.getEstudiante().getCodigoUniversitario()
                            .equals(codigoUniversitario)) {
                return false;
            }
        }

        // Crear y registrar el préstamo
        Prestamo nuevoPrestamo = new Prestamo(estudiante, laptop);
        prestamos.add(nuevoPrestamo);

        // Actualizar disponibilidad de la laptop
        laptop.setDisponible(false);

        return true;
    }

    // F04: Registrar devolución por código de laptop
    public boolean registrarDevolucion(String codigoLaptop) {

        // Validar el código recibido
        if (codigoLaptop == null || codigoLaptop.trim().isEmpty()) {
            return false;
        }

        // Normalizar el código
        codigoLaptop = codigoLaptop.trim();

        // Buscar la laptop
        Laptop laptop = buscarLaptop(codigoLaptop);

        if (laptop == null) {
            return false;
        }

        // Buscar el préstamo activo correspondiente
        for (Prestamo prestamo : prestamos) {

            if (prestamo.isActivo()
                    && prestamo.getLaptop().getCodigo()
                            .equals(codigoLaptop)) {

                // Registrar la fecha de devolución
                prestamo.setFechaDevolucion(LocalDate.now());

                // Actualizar la disponibilidad de la laptop
                laptop.setDisponible(true);

                return true;
            }
        }

        return false;
    }

    // Listar estudiantes
    public List<Estudiante> listarEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    // Listar laptops
    public List<Laptop> listarLaptops() {
        return new ArrayList<>(laptops);
    }

    // Listar préstamos
    public List<Prestamo> listarPrestamos() {
        return new ArrayList<>(prestamos);
    }
}
