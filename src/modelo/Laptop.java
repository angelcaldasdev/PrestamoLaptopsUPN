
package modelo;


public class Laptop {
    
     // Atributos de la clase
    private String codigo;
    private String marca; 
    private String modelo;
    private boolean disponible;

    // Constructor
    public Laptop(String codigo, String marca, String modelo) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.disponible = true;
    }
    
    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    // Método toString()

    @Override
    public String toString() {
        return "Código: " + codigo
                + " | Marca: " + marca 
                + " | Modelo: " + modelo
                + " | Disponible: " + (disponible ? "Sí" : "No");
    }
    
}
