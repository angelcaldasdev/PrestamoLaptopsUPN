# 💻 Sistema de Préstamo de Laptops UPN

## 1. Descripción del proyecto

El **Sistema de Préstamo de Laptops UPN** es una aplicación de escritorio desarrollada en Java que permite gestionar el registro de estudiantes, el inventario de laptops, la asignación de préstamos y el registro de devoluciones.

El sistema busca facilitar el control de los equipos tecnológicos, evitando asignaciones duplicadas y permitiendo conocer la disponibilidad de cada laptop.

## 2. Objetivo

Desarrollar un sistema que permita administrar de manera sencilla y organizada el préstamo de laptops dentro del entorno universitario, aplicando los principios de programación orientada a objetos y las herramientas del lenguaje Java.

## 3. Funcionalidades principales

El sistema cuenta con cuatro funcionalidades principales:

- **Registro de estudiantes:** permite ingresar el nombre y código universitario del estudiante.
- **Registro de laptops:** permite registrar el código, marca y modelo de cada equipo.
- **Registro de préstamos:** permite asignar una laptop disponible a un estudiante registrado.
- **Registro de devoluciones:** permite finalizar un préstamo y actualizar la disponibilidad de la laptop.

## 4. Reglas de validación

Para garantizar el correcto funcionamiento del sistema, se implementaron las siguientes validaciones:

- El código universitario debe comenzar con la letra `U` y contener exactamente 8 caracteres.
- No se permite registrar estudiantes con códigos duplicados.
- No se permite registrar laptops con códigos duplicados.
- Solo se pueden asignar laptops previamente registradas y disponibles.
- Un estudiante no puede tener más de un préstamo activo.
- Una laptop que ya se encuentra prestada no puede asignarse nuevamente.
- Al registrar una devolución, el préstamo cambia al estado finalizado.
- Después de una devolución, la laptop vuelve a estar disponible.
- No se permite registrar una devolución si la laptop no tiene un préstamo activo.

## 5. Tecnologías utilizadas

| Tecnología | Aplicación |
|---|---|
| Java | Lenguaje de programación |
| Java Swing | Desarrollo de la interfaz gráfica |
| Apache NetBeans | Entorno de desarrollo |
| Git | Control de versiones |
| GitHub | Almacenamiento y gestión del repositorio |

## 6. Estructura del proyecto

El proyecto está organizado mediante paquetes que separan las responsabilidades de cada componente:

```text
PrestamoLaptopsUPN/
│
└── src/
    ├── interfaz/
    │   └── FrmPrincipal.java
    │
    ├── modelo/
    │   ├── Estudiante.java
    │   ├── Laptop.java
    │   └── Prestamo.java
    │
    ├── servicio/
    │   └── SistemaPrestamos.java
    │
    └── principal/
        └── Main.java
```

### Descripción de los paquetes

- **modelo:** contiene las clases que representan las entidades principales del sistema: estudiantes, laptops y préstamos.
- **servicio:** contiene la lógica de negocio, las validaciones y las operaciones de registro, búsqueda, préstamo y devolución.
- **interfaz:** contiene la interfaz gráfica desarrollada con Java Swing.
- **principal:** contiene la clase `Main`, encargada de iniciar la aplicación.

## 7. Programación orientada a objetos

Durante el desarrollo se aplicaron conceptos fundamentales de la programación orientada a objetos:

- **Clases y objetos:** representación de estudiantes, laptops y préstamos.
- **Encapsulamiento:** utilización de atributos privados y métodos getters y setters.
- **Constructores:** inicialización de los objetos.
- **Métodos:** organización de las operaciones y funcionalidades del sistema.
- **Listas dinámicas:** utilización de `ArrayList` para almacenar y administrar los registros durante la ejecución.

## 8. Ejecución del proyecto

Para ejecutar la aplicación:

1. Abrir Apache NetBeans.
2. Seleccionar la opción para abrir un proyecto existente.
3. Ubicar la carpeta `PrestamoLaptopsUPN`.
4. Abrir el proyecto.
5. Localizar la clase `Main.java`, dentro del paquete `principal`.
6. Ejecutar el proyecto.

Al iniciar, se mostrará la ventana principal del sistema con las secciones de estudiantes, laptops, préstamos y devoluciones.

## 9. Alcance y limitaciones

Esta versión corresponde a un prototipo funcional de escritorio. Los datos se almacenan temporalmente en listas `ArrayList` durante la ejecución de la aplicación.

Por ello, los registros no se conservan cuando se cierra el programa. La implementación de una base de datos y la persistencia de información quedan como posibles mejoras para futuras versiones.

## 10. Conclusión

El desarrollo del Sistema de Préstamo de Laptops UPN permitió aplicar los fundamentos de programación en Java, la programación orientada a objetos y la creación de interfaces gráficas con Java Swing.

Asimismo, se implementaron validaciones para controlar la disponibilidad de los equipos y evitar inconsistencias en el proceso de préstamo y devolución.

---

**Proyecto académico desarrollado en Java | UPN**