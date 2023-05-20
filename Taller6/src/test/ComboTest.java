package test;

import main.Combo;
import main.ProductoMenu;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ComboTest {

    @Test
    public void testGetDescuento() {//No muy util, pero bueno....
        Combo combo = new Combo("Combo 1", 0.2, new ArrayList<>());

        assertEquals(0.2, combo.getDescuento());
    }

    @Test
    public void testAgregarItemACombo() {
        Combo combo = new Combo("Combo Hamburguesa", 0.2, new ArrayList<>());

        ProductoMenu productoMenu = new ProductoMenu("Hamburguesa", 10);

        combo.agregarItemACombo(productoMenu);

        assertEquals(1, combo.getItemsCombo().size());
        assertEquals(productoMenu, combo.getItemsCombo().get(0));
    }

    @Test
    public void testAgregarItemsACombo() {
        ProductoMenu salchicha = new ProductoMenu("Salchichas", 50);
        ProductoMenu papas = new ProductoMenu("Papas", 50);
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(salchicha);
        productos.add(papas);

        Combo combo = new Combo("Combo Salchipapa", 0.2, productos);

        assertEquals(2, combo.getItemsCombo().size());

        ProductoMenu salsa = new ProductoMenu("Salsa de tomate", 5);

        combo.agregarItemACombo(salsa);

        assertEquals(3, combo.getItemsCombo().size());
        assertEquals(salsa, combo.getItemsCombo().get(2));
    }

    @Test
    public void testGetPrecio() {
        Combo combo = new Combo("Hamburguesa/Papas", 0.2, new ArrayList<>());

        ProductoMenu item1 = new ProductoMenu("Hamburguesa", 10);
        ProductoMenu item2 = new ProductoMenu("Papas", 5);

        combo.agregarItemACombo(item1);
        combo.agregarItemACombo(item2);

        int expectedPrice = (int) ((5 + 10) * (0.8));
        int price = combo.getPrecio();

        assertEquals(expectedPrice, price);
    }

    @Test
    public void testGenerarTextoFactura() {
        Combo combo = new Combo("Hamburguesa/Papas", 0.2, new ArrayList<>());

        ProductoMenu item1 = new ProductoMenu("Hamburguesa", 10);
        ProductoMenu item2 = new ProductoMenu("Papas", 5);

        combo.agregarItemACombo(item1);
        combo.agregarItemACombo(item2);

        int expectedPrice = (int) ((5 + 10) * (0.8));

        String expectedFacturaText = "Hamburguesa/Papas:" + expectedPrice;

        assertEquals(expectedFacturaText, combo.generarTextoFactura());
    }

    @Test
    public void testGenerarTextoFactura2() {
        ProductoMenu salchicha = new ProductoMenu("Salchichas", 50);
        ProductoMenu papas = new ProductoMenu("Papas", 50);
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(salchicha);
        productos.add(papas);

        Combo combo = new Combo("Combo Salchipapa", 0.1, productos);

        int expectedPrice = (int) ((50 + 50) * (0.9));

        String expectedFacturaText = "Combo Salchipapa:" + expectedPrice;

        assertEquals(expectedFacturaText, combo.generarTextoFactura());
    }

    @Test
    public void testGetItemsCombo() {
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 50);
        ProductoMenu papas = new ProductoMenu("Papas", 20);
        ProductoMenu gaseosa = new ProductoMenu("Gaseosa", 10);

        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(hamburguesa);
        productos.add(papas);
        productos.add(gaseosa);

        Combo combo = new Combo("Combo Hamburguesa", 0.5, productos);

        assertEquals(productos, combo.getItemsCombo());
    }
}
