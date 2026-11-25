
package servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import modelo.Estudiante;
import modelo.Laptop;
import modelo.Prestamo;

public class BusquedaServicio {

    // ==========================================
    // 1. BUSCAR ESTUDIANTE POR DNI
    // ==========================================
    public List<Estudiante> buscarPorDni(
            List<Estudiante> estudiantes, String dni) {

        List<Estudiante> resultados = new ArrayList<>();

        if (estudiantes == null || dni == null || dni.trim().isEmpty()) {
            return resultados;
        }

        for (Estudiante estudiante : estudiantes) {

            if (estudiante.getDni() != null
                    && estudiante.getDni().contains(dni.trim())) {

                resultados.add(estudiante);
            }
        }

        return resultados;
    }

    // ==========================================
    // 2. BUSCAR ESTUDIANTE POR NOMBRE
    // ==========================================
    public List<Estudiante> buscarPorNombre(
            List<Estudiante> estudiantes, String nombre) {

        List<Estudiante> resultados = new ArrayList<>();

        if (estudiantes == null || nombre == null
                || nombre.trim().isEmpty()) {
            return resultados;
        }

        String busqueda = nombre.trim().toLowerCase(Locale.ROOT);

        for (Estudiante estudiante : estudiantes) {

            if (estudiante.getNombre() != null
                    && estudiante.getNombre()
                            .toLowerCase(Locale.ROOT)
                            .contains(busqueda)) {

                resultados.add(estudiante);
            }
        }

        return resultados;
    }

    // ==========================================
    // 3. BUSCAR LAPTOP POR CODIGO
    // ==========================================
    public List<Laptop> buscarPorCodigoLaptop(
            List<Laptop> laptops, String codigo) {

        List<Laptop> resultados = new ArrayList<>();

        if (laptops == null || codigo == null
                || codigo.trim().isEmpty()) {
            return resultados;
        }

        String busqueda = codigo.trim();

        for (Laptop laptop : laptops) {

            if (laptop.getCodigo() != null
                    && laptop.getCodigo().equalsIgnoreCase(busqueda)) {

                resultados.add(laptop);
            }
        }

        return resultados;
    }

    // ==========================================
    // 4. FILTRAR PRESTAMOS POR ESTADO
    // ==========================================
    public List<Prestamo> filtrarPorEstado(
            List<Prestamo> prestamos, String estado) {

        List<Prestamo> resultados = new ArrayList<>();

        if (prestamos == null || estado == null
                || estado.trim().isEmpty()) {
            return resultados;
        }

        for (Prestamo prestamo : prestamos) {

            String estadoActual = prestamo.isActivo()
                    ? "Activo"
                    : "Devuelto";

            if (estadoActual.equalsIgnoreCase(estado.trim())) {
                resultados.add(prestamo);
            }
        }

        return resultados;
    }
}
