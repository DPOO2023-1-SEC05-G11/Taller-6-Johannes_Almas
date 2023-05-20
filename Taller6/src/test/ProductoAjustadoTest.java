package test;

import main.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ProductoAjustadoTest {

    @Test
    public void getPrecio_SoloHamburguesa_100() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 100);
        ProductoAjustado producto = new ProductoAjustado(base, new ArrayList<>(), new ArrayList<>());
        
        int precio = producto.getPrecio();
        
        Assertions.assertEquals(100, precio);
    }

    @Test
    public void getPrecio_HamburguesaConQuesoTomate_130() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 100);
        ArrayList<Ingredientes> agregados = new ArrayList<>();
        agregados.add(new Ingredientes("Queso", 20));
        agregados.add(new Ingredientes("Tomate", 10));
        ArrayList<Ingredientes> eliminados = new ArrayList<>();
        eliminados.add(new Ingredientes("Lechuga", 10));
        ProductoAjustado producto = new ProductoAjustado(base, agregados, eliminados);
        
        int precio = producto.getPrecio();
        
        Assertions.assertEquals(130, precio);
    }

    @Test
    public void generarTextoFactura_Hamburguesa_ReturnsCorrectText() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 100);
        ArrayList<Ingredientes> agregados = new ArrayList<>();
        agregados.add(new Ingredientes("Queso", 20));
        agregados.add(new Ingredientes("Tomate", 10));
        ArrayList<Ingredientes> eliminados = new ArrayList<>();
        eliminados.add(new Ingredientes("Cebolla", 5));
        ProductoAjustado producto = new ProductoAjustado(base, agregados, eliminados);
        
        String textoFactura = producto.generarTextoFactura();
        
        Assertions.assertEquals("Hamburguesa sin Cebolla con Queso, Tomate: 130", textoFactura);
    }
    
    @Test
    public void generarTextoFactura_Papas_ReturnsCorrectText() {
        ProductoMenu base = new ProductoMenu("Papas", 60);
        ArrayList<Ingredientes> agregados = new ArrayList<>();
        agregados.add(new Ingredientes("Queso", 20));
        agregados.add(new Ingredientes("Salsa de tomate", 10));
        ArrayList<Ingredientes> eliminados = new ArrayList<>();
        eliminados.add(new Ingredientes("Sal", 5));
        eliminados.add(new Ingredientes("Mayo", 10));
        ProductoAjustado producto = new ProductoAjustado(base, agregados, eliminados);
        
        String textoFactura = producto.generarTextoFactura();
        
        Assertions.assertEquals("Papas sin Sal, Mayo con Queso, Salsa de tomate: 90", textoFactura);
    }

    @Test
    public void generarTextoFactura_SoloSalchichaNulls_ReturnsCorrectText() {
        ProductoMenu base = new ProductoMenu("Salchicha", 1000);
        ProductoAjustado producto = new ProductoAjustado(base, null, null);
        
        String textoFactura = producto.generarTextoFactura();
        
        Assertions.assertEquals("Salchicha: 1000", textoFactura);
    }

    @Test
    public void generarTextoFactura_SoloSalchicha_ReturnsCorrectText() {
        ProductoMenu base = new ProductoMenu("Salchicha", 1000);
        ProductoAjustado producto = new ProductoAjustado(base, new ArrayList<>(), new ArrayList<>());
        
        String textoFactura = producto.generarTextoFactura();
        
        Assertions.assertEquals("Salchicha: 1000", textoFactura);
    }
    
}
