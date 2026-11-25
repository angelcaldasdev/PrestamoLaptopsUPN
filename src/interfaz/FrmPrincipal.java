package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.Estudiante;
import modelo.Laptop;
import modelo.Prestamo;
import servicio.SistemaPrestamos;

public class FrmPrincipal extends JFrame {

    // Colores principales de la interfaz
    private final Color NARANJA = new Color(240, 120, 25);
    private final Color NARANJA_OSCURO = new Color(210, 92, 15);
    private final Color AZUL_MARINO = new Color(30, 48, 72);
    private final Color FONDO = new Color(243, 245, 248);
    private final Color BLANCO = Color.WHITE;
    private final Color GRIS_TEXTO = new Color(90, 100, 115);

    // Sistema de préstamos
    private SistemaPrestamos sistema;

    // Áreas de información
    private JTextArea areaEstudiantes;
    private JTextArea areaLaptops;
    private JTextArea areaPrestamos;
    private JTextArea areaDevoluciones;

    // Constructor
    public FrmPrincipal() {

        sistema = new SistemaPrestamos();

        setTitle("UPN - Sistema de Préstamo de Laptops");
        setSize(950, 650);
        setMinimumSize(new java.awt.Dimension(800, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        construirInterfaz();
        actualizarListas();
    }

    // Construir interfaz principal
    private void construirInterfaz() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(FONDO);

        // Encabezado
        JPanel panelEncabezado = new JPanel(new BorderLayout());
        panelEncabezado.setBackground(NARANJA);
        panelEncabezado.setBorder(
                new EmptyBorder(20, 25, 20, 25));

        JLabel titulo = new JLabel(
                "UPN | SISTEMA DE PRÉSTAMO DE LAPTOPS");

        titulo.setFont(new Font("Arial", Font.BOLD, 23));
        titulo.setForeground(BLANCO);

        JLabel subtitulo = new JLabel(
                "Gestión universitaria de equipos tecnológicos");

        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(BLANCO);

        JPanel textosEncabezado = new JPanel(
                new BorderLayout(5, 5));

        textosEncabezado.setOpaque(false);
        textosEncabezado.add(titulo, BorderLayout.NORTH);
        textosEncabezado.add(subtitulo, BorderLayout.SOUTH);

        JLabel icono = new JLabel("💻");
        icono.setFont(new Font("Arial", Font.PLAIN, 38));

        panelEncabezado.add(
                textosEncabezado, BorderLayout.CENTER);

        panelEncabezado.add(icono, BorderLayout.EAST);

        panelPrincipal.add(
                panelEncabezado, BorderLayout.NORTH);

        // Pestañas principales
        JTabbedPane pestañas = new JTabbedPane();

        pestañas.setFont(
                new Font("Arial", Font.BOLD, 13));

        pestañas.setBackground(FONDO);
        pestañas.setForeground(AZUL_MARINO);

        // Crear áreas de información
        areaEstudiantes = crearAreaTexto();
        areaLaptops = crearAreaTexto();
        areaPrestamos = crearAreaTexto();
        areaDevoluciones = crearAreaTexto();

        // Pestaña de estudiantes
        JButton btnRegistrarEstudiante =
                crearBoton("Registrar estudiante");

        btnRegistrarEstudiante.addActionListener(e ->
                registrarEstudiante());

        pestañas.addTab("Estudiantes",
                crearPanel(areaEstudiantes,
                        btnRegistrarEstudiante,
                        "Registro y consulta de estudiantes"));

        // Pestaña de laptops
        JButton btnRegistrarLaptop =
                crearBoton("Registrar laptop");

        btnRegistrarLaptop.addActionListener(e ->
                registrarLaptop());

        pestañas.addTab("Laptops",
                crearPanel(areaLaptops,
                        btnRegistrarLaptop,
                        "Registro y disponibilidad de equipos"));

        // Pestaña de préstamos
        JButton btnRegistrarPrestamo =
                crearBoton("Registrar préstamo");

        btnRegistrarPrestamo.addActionListener(e ->
                registrarPrestamo());

        pestañas.addTab("Préstamos",
                crearPanel(areaPrestamos,
                        btnRegistrarPrestamo,
                        "Consulta y asignación de préstamos"));

        // Pestaña de devoluciones
        JButton btnRegistrarDevolucion =
                crearBoton("Registrar devolución");

        btnRegistrarDevolucion.addActionListener(e ->
                registrarDevolucion());

        pestañas.addTab("Devoluciones",
                crearPanel(areaDevoluciones,
                        btnRegistrarDevolucion,
                        "Historial de laptops devueltas"));

        panelPrincipal.add(
                pestañas, BorderLayout.CENTER);

        // Pie de página
        JLabel pie = new JLabel(
                "Sistema académico desarrollado en Java | UPN",
                SwingConstants.CENTER);

        pie.setFont(new Font("Arial", Font.PLAIN, 12));
        pie.setForeground(GRIS_TEXTO);
        pie.setBorder(new EmptyBorder(12, 5, 12, 5));

        panelPrincipal.add(pie, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // Crear área de texto
    private JTextArea crearAreaTexto() {

        JTextArea area = new JTextArea();

        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setForeground(AZUL_MARINO);
        area.setBackground(BLANCO);
        area.setMargin(new Insets(12, 12, 12, 12));

        return area;
    }

    // Crear botones personalizados
    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setForeground(BLANCO);
        boton.setBackground(NARANJA);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setMargin(new Insets(10, 20, 10, 20));

        // Efecto de color al pasar el mouse
        boton.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(NARANJA_OSCURO);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(NARANJA);
            }
        });

        return boton;
    }

    // Crear panel para cada pestaña
    
    // Comentario de Prueva go go go
    private JPanel crearPanel(
            JTextArea area, JButton boton, String descripcion) {

        JPanel panel = new JPanel(new BorderLayout(11, 15));

        panel.setBackground(FONDO);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel etiqueta = new JLabel(descripcion);

        etiqueta.setFont(new Font("Arial", Font.BOLD, 15));
        etiqueta.setForeground(AZUL_MARINO);

        panel.add(etiqueta, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(area);

        scroll.setBorder(
                new LineBorder(new Color(220, 225, 232), 1));

        panel.add(scroll, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();

        panelBoton.setBackground(FONDO);
        panelBoton.add(boton);

        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    // F01: Registrar estudiante
    private void registrarEstudiante() {

        String nombre = JOptionPane.showInputDialog(
                this, "Ingrese el nombre del estudiante:");

        if (nombre == null) {
            return;
        }

        String codigo = JOptionPane.showInputDialog(
                this, "Ingrese el código universitario (U + 7 caracteres):");

        if (codigo == null) {
            return;
        }

        // Validar campos vacíos
        if (nombre.trim().isEmpty() || codigo.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Debe completar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        codigo = codigo.trim();

        // Validar formato del código
        if (!codigo.matches("U[A-Za-z0-9]{7}")) {

            JOptionPane.showMessageDialog(this,
                    "El código universitario debe comenzar con U "
                    + "y contener exactamente 8 caracteres.",
                    "Código inválido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar si el estudiante ya está registrado
        if (sistema.buscarEstudiante(codigo) != null) {

            JOptionPane.showMessageDialog(this,
                    "El estudiante con código " + codigo
                    + " ya se encuentra registrado.",
                    "Estudiante duplicado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Estudiante estudiante = new Estudiante(
                nombre.trim(), codigo);

        if (sistema.registrarEstudiante(estudiante)) {

            JOptionPane.showMessageDialog(this,
                    "Estudiante registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se pudo completar el registro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        actualizarListas();
    }

    // F02: Registrar laptop
    private void registrarLaptop() {

        String codigo = JOptionPane.showInputDialog(
                this, "Ingrese el código de la laptop:");

        if (codigo == null) {
            return;
        }

        String marca = JOptionPane.showInputDialog(
                this, "Ingrese la marca:");

        if (marca == null) {
            return;
        }

        String modelo = JOptionPane.showInputDialog(
                this, "Ingrese el modelo:");

        if (modelo == null) {
            return;
        }

        // Validar campos vacíos
        if (codigo.trim().isEmpty()
                || marca.trim().isEmpty()
                || modelo.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Debe completar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        codigo = codigo.trim();

        // Verificar si la laptop ya está registrada
        if (sistema.buscarLaptop(codigo) != null) {

            JOptionPane.showMessageDialog(this,
                    "La laptop con código " + codigo
                    + " ya se encuentra registrada.",
                    "Laptop duplicada",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Laptop laptop = new Laptop(
                codigo, marca.trim(), modelo.trim());

        if (sistema.registrarLaptop(laptop)) {

            JOptionPane.showMessageDialog(this,
                    "Laptop registrada correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se pudo completar el registro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        actualizarListas();
    }

    // F03: Registrar préstamo
    private void registrarPrestamo() {

        String codigoEstudiante = JOptionPane.showInputDialog(
                this, "Ingrese el código del estudiante:");

        if (codigoEstudiante == null) {
            return;
        }

        String codigoLaptop = JOptionPane.showInputDialog(
                this, "Ingrese el código de la laptop:");

        if (codigoLaptop == null) {
            return;
        }

        // Validar campos vacíos
        if (codigoEstudiante.trim().isEmpty()
                || codigoLaptop.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Debe ingresar ambos códigos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        codigoEstudiante = codigoEstudiante.trim();
        codigoLaptop = codigoLaptop.trim();

        // Buscar estudiante y laptop
        Estudiante estudiante =
                sistema.buscarEstudiante(codigoEstudiante);

        Laptop laptop =
                sistema.buscarLaptop(codigoLaptop);

        // Verificar existencia del estudiante
        if (estudiante == null) {

            JOptionPane.showMessageDialog(this,
                    "No existe un estudiante registrado con el código "
                    + codigoEstudiante + ".",
                    "Estudiante no encontrado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar existencia de la laptop
        if (laptop == null) {

            JOptionPane.showMessageDialog(this,
                    "No existe una laptop registrada con el código "
                    + codigoLaptop + ".",
                    "Laptop no encontrada",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar disponibilidad de la laptop
        if (!laptop.isDisponible()) {

            JOptionPane.showMessageDialog(this,
                    "La laptop " + codigoLaptop
                    + " actualmente se encuentra prestada.",
                    "Laptop no disponible",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar si el estudiante tiene un préstamo activo
        for (Prestamo prestamo : sistema.listarPrestamos()) {

            if (prestamo.isActivo()
                    && prestamo.getEstudiante()
                            .getCodigoUniversitario()
                            .equals(codigoEstudiante)) {

                JOptionPane.showMessageDialog(this,
                        "El estudiante " + estudiante.getNombre()
                        + " ya tiene un préstamo activo.",
                        "Préstamo existente",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Registrar préstamo
        if (sistema.registrarPrestamo(
                codigoEstudiante, codigoLaptop)) {

            JOptionPane.showMessageDialog(this,
                    "Préstamo registrado correctamente.\n"
                    + "Estudiante: " + estudiante.getNombre()
                    + "\nLaptop: " + codigoLaptop,
                    "Préstamo exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se pudo completar el préstamo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        actualizarListas();
    }

    // F04: Registrar devolución
    private void registrarDevolucion() {

        String codigoLaptop = JOptionPane.showInputDialog(
                this, "Ingrese el código de la laptop a devolver:");

        if (codigoLaptop == null) {
            return;
        }

        if (codigoLaptop.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Debe ingresar el código de la laptop.",
                    "Dato incompleto",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        codigoLaptop = codigoLaptop.trim();

        // Verificar existencia de la laptop
        if (sistema.buscarLaptop(codigoLaptop) == null) {

            JOptionPane.showMessageDialog(this,
                    "No existe una laptop registrada con el código "
                    + codigoLaptop + ".",
                    "Laptop no encontrada",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Registrar devolución
        if (sistema.registrarDevolucion(codigoLaptop)) {

            JOptionPane.showMessageDialog(this,
                    "Devolución registrada correctamente. "
                    + "La laptop está disponible nuevamente.",
                    "Devolución exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(this,
                    "La laptop " + codigoLaptop
                    + " no tiene un préstamo activo para devolver.",
                    "Sin préstamo activo",
                    JOptionPane.WARNING_MESSAGE);
        }

        actualizarListas();
    }

    // Actualizar información de las listas
    private void actualizarListas() {

        List<Estudiante> estudiantes =
                sistema.listarEstudiantes();

        List<Laptop> laptops =
                sistema.listarLaptops();

        List<Prestamo> prestamos =
                sistema.listarPrestamos();

        // Actualizar estudiantes
        StringBuilder textoEstudiantes = new StringBuilder();

        for (Estudiante estudiante : estudiantes) {
            textoEstudiantes.append(estudiante).append("\n\n");
        }

        areaEstudiantes.setText(
                textoEstudiantes.length() == 0
                ? "No hay estudiantes registrados."
                : textoEstudiantes.toString());

        // Actualizar laptops
        StringBuilder textoLaptops = new StringBuilder();

        for (Laptop laptop : laptops) {
            textoLaptops.append(laptop).append("\n\n");
        }

        areaLaptops.setText(
                textoLaptops.length() == 0
                ? "No hay laptops registradas."
                : textoLaptops.toString());

        // Actualizar préstamos
        StringBuilder textoPrestamos = new StringBuilder();

        for (Prestamo prestamo : prestamos) {
            textoPrestamos.append(prestamo).append("\n\n");
        }

        areaPrestamos.setText(
                textoPrestamos.length() == 0
                ? "No hay préstamos registrados."
                : textoPrestamos.toString());

        // Actualizar devoluciones
        StringBuilder textoDevoluciones = new StringBuilder();

        for (Prestamo prestamo : prestamos) {

            if (!prestamo.isActivo()) {
                textoDevoluciones.append(prestamo).append("\n\n");
            }
        }

        areaDevoluciones.setText(
                textoDevoluciones.length() == 0
                ? "No hay devoluciones registradas."
                : textoDevoluciones.toString());
    }
}
