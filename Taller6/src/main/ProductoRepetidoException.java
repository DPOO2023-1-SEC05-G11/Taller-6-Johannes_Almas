package main;
public class ProductoRepetidoException extends HamburguesaException {
    private Producto producto;

    public ProductoRepetidoException(Producto producto) {
        super("Producto repetido: " + producto.getNombre());
        this.producto = producto;
    }

    public String getProducto() {
        return producto.getNombre();
    }
}