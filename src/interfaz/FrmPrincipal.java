
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
import servicio.BusquedaServicio;

public class FrmPrincipal extends JFrame {

    // ==========================================
    // 1. COLORES PRINCIPALES
    // ==========================================

    private final Color NARANJA = new Color(240, 120, 25);
    private final Color NARANJA_OSCURO = new Color(210, 92, 15);
    private final Color AZUL_MARINO = new Color(30, 48, 72);
    private final Color FONDO = new Color(243, 245, 248);
    private final Color BLANCO = Color.WHITE;
    private final Color GRIS_TEXTO = new Color(90, 100, 115);

    // ==========================================
    // 2. SERVICIOS
    // ==========================================

    private SistemaPrestamos sistema;
    private BusquedaServicio busqueda;

    // ==========================================
    // 3. ÁREAS DE INFORMACIÓN
    // ==========================================

    private JTextArea areaEstudiantes;
    private JTextArea areaLaptops;
    private JTextArea areaPrestamos;
    private JTextArea areaDevoluciones;

    // ==========================================
    // 4. CONSTRUCTOR
    // ==========================================

    public FrmPrincipal() {

        sistema = new SistemaPrestamos();
        busqueda = new BusquedaServicio();

        setTitle("UPN - Sistema de Préstamo de Laptops");

        setSize(1000, 700);

        setMinimumSize(new java.awt.Dimension(850, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        construirInterfaz();

        actualizarListas();
    }

    // ==========================================
    // 5. CONSTRUIR INTERFAZ PRINCIPAL
    // ==========================================

    private void construirInterfaz() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        panelPrincipal.setBackground(FONDO);

        // ENCABEZADO

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

        // PESTAÑAS

        JTabbedPane pestañas = new JTabbedPane();

        pestañas.setFont(new Font("Arial", Font.BOLD, 13));

        pestañas.setBackground(FONDO);

        pestañas.setForeground(AZUL_MARINO);

        // ÁREAS DE INFORMACIÓN

        areaEstudiantes = crearAreaTexto();

        areaLaptops = crearAreaTexto();

        areaPrestamos = crearAreaTexto();

        areaDevoluciones = crearAreaTexto();

        // ==========================================
        // PESTAÑA 1: ESTUDIANTES
        // ==========================================

        JButton btnRegistrarEstudiante =
                crearBoton("Registrar estudiante");

        btnRegistrarEstudiante.addActionListener(e ->
                registrarEstudiante());

        pestañas.addTab(
                "Estudiantes",
                crearPanelConBusqueda(
                        areaEstudiantes,
                        btnRegistrarEstudiante,
                        "Registro y consulta de estudiantes",
                        crearFiltrosEstudiantes()
                )
        );

        // ==========================================
        // PESTAÑA 2: LAPTOPS
        // ==========================================

        JButton btnRegistrarLaptop =
                crearBoton("Registrar laptop");

        btnRegistrarLaptop.addActionListener(e ->
                registrarLaptop());

        pestañas.addTab(
                "Laptops",
                crearPanelConBusqueda(
                        areaLaptops,
                        btnRegistrarLaptop,
                        "Registro y disponibilidad de equipos",
                        crearFiltrosLaptops()
                )
        );

        // ==========================================
        // PESTAÑA 3: PRÉSTAMOS
        // ==========================================

        JButton btnRegistrarPrestamo =
                crearBoton("Registrar préstamo");

        btnRegistrarPrestamo.addActionListener(e ->
                registrarPrestamo());

        pestañas.addTab(
                "Préstamos",
                crearPanelConBusqueda(
                        areaPrestamos,
                        btnRegistrarPrestamo,
                        "Consulta y asignación de préstamos",
                        crearFiltrosPrestamos()
                )
        );

        // ==========================================
        // PESTAÑA 4: DEVOLUCIONES
        // ==========================================

        JButton btnRegistrarDevolucion =
                crearBoton("Registrar devolución");

        btnRegistrarDevolucion.addActionListener(e ->
                registrarDevolucion());

        pestañas.addTab(
                "Devoluciones",
                crearPanel(
                        areaDevoluciones,
                        btnRegistrarDevolucion,
                        "Historial de laptops devueltas"
                )
        );

        panelPrincipal.add(
                pestañas, BorderLayout.CENTER);

        // PIE DE PÁGINA

        JLabel pie = new JLabel(
                "Sistema académico desarrollado en Java | UPN",
                SwingConstants.CENTER);

        pie.setFont(new Font("Arial", Font.PLAIN, 12));

        pie.setForeground(GRIS_TEXTO);

        pie.setBorder(new EmptyBorder(12, 5, 12, 5));

        panelPrincipal.add(pie, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // ==========================================
    // 6. CREAR ÁREA DE TEXTO
    // ==========================================

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

    // ==========================================
    // 7. CREAR BOTONES PERSONALIZADOS
    // ==========================================

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Arial", Font.BOLD, 13));

        boton.setForeground(BLANCO);

        boton.setBackground(NARANJA);

        boton.setFocusPainted(false);

        boton.setBorderPainted(false);

        boton.setOpaque(true);

        boton.setMargin(new Insets(10, 20, 10, 20));

        boton.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                boton.setBackground(NARANJA_OSCURO);
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                boton.setBackground(NARANJA);
            }
        });

        return boton;
    }

    // ==========================================
    // 8. PANEL NORMAL
    // ==========================================

    private JPanel crearPanel(
            JTextArea area,
            JButton boton,
            String descripcion) {

        JPanel panel = new JPanel(
                new BorderLayout(11, 15));

        panel.setBackground(FONDO);

        panel.setBorder(
                new EmptyBorder(20, 20, 20, 20));

        JLabel etiqueta = new JLabel(descripcion);

        etiqueta.setFont(
                new Font("Arial", Font.BOLD, 15));

        etiqueta.setForeground(AZUL_MARINO);

        panel.add(etiqueta, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(area);

        scroll.setBorder(
                new LineBorder(
                        new Color(220, 225, 232), 1));

        panel.add(scroll, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();

        panelBoton.setBackground(FONDO);

        panelBoton.add(boton);

        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    // ==========================================
    // 9. PANEL CON BÚSQUEDA
    // ==========================================

    private JPanel crearPanelConBusqueda(
            JTextArea area,
            JButton boton,
            String descripcion,
            JPanel filtros) {

        JPanel panel = new JPanel(
                new BorderLayout(11, 15));

        panel.setBackground(FONDO);

        panel.setBorder(
                new EmptyBorder(20, 20, 20, 20));

        JPanel superior = new JPanel(
                new BorderLayout(5, 10));

        superior.setBackground(FONDO);

        JLabel etiqueta = new JLabel(descripcion);

        etiqueta.setFont(
                new Font("Arial", Font.BOLD, 15));

        etiqueta.setForeground(AZUL_MARINO);

        superior.add(etiqueta, BorderLayout.NORTH);

        superior.add(filtros, BorderLayout.SOUTH);

        panel.add(superior, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(area);

        scroll.setBorder(
                new LineBorder(
                        new Color(220, 225, 232), 1));

        panel.add(scroll, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();

        panelBoton.setBackground(FONDO);

        panelBoton.add(boton);

        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    // ==========================================
    // 10. FILTROS DE ESTUDIANTES
    // ==========================================

    private JPanel crearFiltrosEstudiantes() {

        JPanel panel = new JPanel();

        panel.setBackground(FONDO);

        JComboBox<String> tipoBusqueda = new JComboBox<>(
                new String[]{"DNI", "Nombre"});

        JTextField txtBusqueda = new JTextField(15);

        JButton btnBuscar = crearBoton("Buscar");

        JButton btnLimpiar = crearBoton("Limpiar");

        btnBuscar.addActionListener(e -> {

            String texto = txtBusqueda.getText().trim();

            if (texto.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un dato para buscar.");

                return;
            }

            List<Estudiante> resultados;

            if (tipoBusqueda.getSelectedItem()
                    .equals("DNI")) {

                resultados = busqueda.buscarPorDni(
                        sistema.listarEstudiantes(),
                        texto);

            } else {

                resultados = busqueda.buscarPorNombre(
                        sistema.listarEstudiantes(),
                        texto);
            }

            mostrarResultados(
                    areaEstudiantes,
                    resultados);
        });

        btnLimpiar.addActionListener(e -> {

            txtBusqueda.setText("");

            actualizarListas();
        });

        panel.add(new JLabel("Buscar por:"));

        panel.add(tipoBusqueda);

        panel.add(txtBusqueda);

        panel.add(btnBuscar);

        panel.add(btnLimpiar);

        return panel;
    }

    // ==========================================
    // 11. FILTROS DE LAPTOPS
    // ==========================================

    private JPanel crearFiltrosLaptops() {

        JPanel panel = new JPanel();

        panel.setBackground(FONDO);

        JTextField txtCodigo = new JTextField(15);

        JButton btnBuscar = crearBoton("Buscar");

        JButton btnLimpiar = crearBoton("Limpiar");

        btnBuscar.addActionListener(e -> {

            String codigo = txtCodigo.getText().trim();

            if (codigo.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el código de la laptop.");

                return;
            }

            List<Laptop> resultados =
                    busqueda.buscarPorCodigoLaptop(
                            sistema.listarLaptops(),
                            codigo);

            mostrarResultados(
                    areaLaptops,
                    resultados);
        });

        btnLimpiar.addActionListener(e -> {

            txtCodigo.setText("");

            actualizarListas();
        });

        panel.add(new JLabel("Código de laptop:"));

        panel.add(txtCodigo);

        panel.add(btnBuscar);

        panel.add(btnLimpiar);

        return panel;
    }

    // ==========================================
    // 12. FILTROS DE PRÉSTAMOS
    // ==========================================

    private JPanel crearFiltrosPrestamos() {

        JPanel panel = new JPanel();

        panel.setBackground(FONDO);

        JComboBox<String> cmbEstado = new JComboBox<>(
                new String[]{"Todos", "Activo", "Devuelto"});

        JButton btnFiltrar = crearBoton("Filtrar");

        JButton btnLimpiar = crearBoton("Limpiar");

        btnFiltrar.addActionListener(e -> {

            String estado =
                    cmbEstado.getSelectedItem().toString();

            if (estado.equals("Todos")) {

                mostrarResultados(
                        areaPrestamos,
                        sistema.listarPrestamos());

            } else {

                List<Prestamo> resultados =
                        busqueda.filtrarPorEstado(
                                sistema.listarPrestamos(),
                                estado);

                mostrarResultados(
                        areaPrestamos,
                        resultados);
            }
        });

        btnLimpiar.addActionListener(e -> {

            cmbEstado.setSelectedIndex(0);

            actualizarListas();
        });

        panel.add(new JLabel("Estado:"));

        panel.add(cmbEstado);

        panel.add(btnFiltrar);

        panel.add(btnLimpiar);

        return panel;
    }

    // ==========================================
    // 13. MOSTRAR RESULTADOS
    // ==========================================

    private void mostrarResultados(
            JTextArea area,
            List<?> resultados) {

        StringBuilder texto = new StringBuilder();

        for (Object resultado : resultados) {

            texto.append(resultado)
                    .append("\n\n");
        }

        area.setText(
                texto.length() == 0
                ? "No se encontraron resultados."
                : texto.toString()
        );
    }

    // ==========================================
    // 14. F01: REGISTRAR ESTUDIANTE
    // ==========================================

    private void registrarEstudiante() {

        String nombre = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre del estudiante:");

        if (nombre == null) {
            return;
        }

        String codigo = JOptionPane.showInputDialog(
                this,
                "Ingrese el código universitario (U + 7 caracteres):");

        if (codigo == null) {
            return;
        }

        String dni = JOptionPane.showInputDialog(
                this,
                "Ingrese el DNI del estudiante (8 dígitos):");

        if (dni == null) {
            return;
        }

        if (nombre.trim().isEmpty()
                || codigo.trim().isEmpty()
                || dni.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        nombre = nombre.trim();

        codigo = codigo.trim();

        dni = dni.trim();

        if (!codigo.matches("U[A-Za-z0-9]{7}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El código debe comenzar con U "
                    + "y contener exactamente 8 caracteres.",
                    "Código inválido",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (!dni.matches("[0-9]{8}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El DNI debe contener exactamente 8 dígitos.",
                    "DNI inválido",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (sistema.buscarEstudiante(codigo) != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "El estudiante ya se encuentra registrado.",
                    "Estudiante duplicado",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // Crear estudiante con sus tres atributos

        Estudiante estudiante = new Estudiante(
                nombre,
                codigo,
                dni);

        if (sistema.registrarEstudiante(estudiante)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Estudiante registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo completar el registro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        actualizarListas();
    }

    // ==========================================
    // 15. F02: REGISTRAR LAPTOP
    // ==========================================

    private void registrarLaptop() {

        String codigo = JOptionPane.showInputDialog(
                this,
                "Ingrese el código de la laptop:");

        if (codigo == null) {
            return;
        }

        String marca = JOptionPane.showInputDialog(
                this,
                "Ingrese la marca:");

        if (marca == null) {
            return;
        }

        String modelo = JOptionPane.showInputDialog(
                this,
                "Ingrese el modelo:");

        if (modelo == null) {
            return;
        }

        if (codigo.trim().isEmpty()
                || marca.trim().isEmpty()
                || modelo.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        codigo = codigo.trim();

        if (sistema.buscarLaptop(codigo) != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "La laptop ya se encuentra registrada.",
                    "Laptop duplicada",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        Laptop laptop = new Laptop(
                codigo,
                marca.trim(),
                modelo.trim());

        if (sistema.registrarLaptop(laptop)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Laptop registrada correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo completar el registro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        actualizarListas();
    }

    // ==========================================
    // 16. F03: REGISTRAR PRÉSTAMO
    // ==========================================

    private void registrarPrestamo() {

        String codigoEstudiante = JOptionPane.showInputDialog(
                this,
                "Ingrese el código del estudiante:");

        if (codigoEstudiante == null) {
            return;
        }

        String codigoLaptop = JOptionPane.showInputDialog(
                this,
                "Ingrese el código de la laptop:");

        if (codigoLaptop == null) {
            return;
        }

        if (codigoEstudiante.trim().isEmpty()
                || codigoLaptop.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar ambos códigos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        codigoEstudiante = codigoEstudiante.trim();

        codigoLaptop = codigoLaptop.trim();

        Estudiante estudiante =
                sistema.buscarEstudiante(codigoEstudiante);

        Laptop laptop =
                sistema.buscarLaptop(codigoLaptop);

        if (estudiante == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existe el estudiante indicado.",
                    "Estudiante no encontrado",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (laptop == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existe la laptop indicada.",
                    "Laptop no encontrada",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (!laptop.isDisponible()) {

            JOptionPane.showMessageDialog(
                    this,
                    "La laptop actualmente se encuentra prestada.",
                    "Laptop no disponible",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        for (Prestamo prestamo : sistema.listarPrestamos()) {

            if (prestamo.isActivo()
                    && prestamo.getEstudiante()
                            .getCodigoUniversitario()
                            .equals(codigoEstudiante)) {

                JOptionPane.showMessageDialog(
                        this,
                        "El estudiante ya tiene un préstamo activo.",
                        "Préstamo existente",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }
        }

        if (sistema.registrarPrestamo(
                codigoEstudiante,
                codigoLaptop)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Préstamo registrado correctamente.\n"
                    + "Estudiante: " + estudiante.getNombre()
                    + "\nLaptop: " + codigoLaptop,
                    "Préstamo exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo completar el préstamo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        actualizarListas();
    }

    // ==========================================
    // 17. F04: REGISTRAR DEVOLUCIÓN
    // ==========================================

    private void registrarDevolucion() {

        String codigoLaptop = JOptionPane.showInputDialog(
                this,
                "Ingrese el código de la laptop a devolver:");

        if (codigoLaptop == null) {
            return;
        }

        if (codigoLaptop.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el código de la laptop.",
                    "Dato incompleto",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        codigoLaptop = codigoLaptop.trim();

        if (sistema.buscarLaptop(codigoLaptop) == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existe la laptop indicada.",
                    "Laptop no encontrada",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (sistema.registrarDevolucion(codigoLaptop)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Devolución registrada correctamente.\n"
                    + "La laptop está disponible nuevamente.",
                    "Devolución exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "La laptop no tiene un préstamo activo.",
                    "Sin préstamo activo",
                    JOptionPane.WARNING_MESSAGE);
        }

        actualizarListas();
    }

    // ==========================================
    // 18. ACTUALIZAR TODAS LAS LISTAS
    // ==========================================

    private void actualizarListas() {

        List<Estudiante> estudiantes =
                sistema.listarEstudiantes();

        List<Laptop> laptops =
                sistema.listarLaptops();

        List<Prestamo> prestamos =
                sistema.listarPrestamos();

        // ACTUALIZAR ESTUDIANTES

        StringBuilder textoEstudiantes =
                new StringBuilder();

        for (Estudiante estudiante : estudiantes) {

            textoEstudiantes.append(estudiante)
                    .append("\n\n");
        }

        areaEstudiantes.setText(
                textoEstudiantes.length() == 0
                ? "No hay estudiantes registrados."
                : textoEstudiantes.toString());

        // ACTUALIZAR LAPTOPS

        StringBuilder textoLaptops =
                new StringBuilder();

        for (Laptop laptop : laptops) {

            textoLaptops.append(laptop)
                    .append("\n\n");
        }

        areaLaptops.setText(
                textoLaptops.length() == 0
                ? "No hay laptops registradas."
                : textoLaptops.toString());

        // ACTUALIZAR PRÉSTAMOS

        StringBuilder textoPrestamos =
                new StringBuilder();

        for (Prestamo prestamo : prestamos) {

            textoPrestamos.append(prestamo)
                    .append("\n\n");
        }

        areaPrestamos.setText(
                textoPrestamos.length() == 0
                ? "No hay préstamos registrados."
                : textoPrestamos.toString());

        // ACTUALIZAR DEVOLUCIONES

        StringBuilder textoDevoluciones =
                new StringBuilder();

        for (Prestamo prestamo : prestamos) {

            if (!prestamo.isActivo()) {

                textoDevoluciones.append(prestamo)
                        .append("\n\n");
            }
        }

        areaDevoluciones.setText(
                textoDevoluciones.length() == 0
                ? "No hay devoluciones registradas."
                : textoDevoluciones.toString());
    }
}
