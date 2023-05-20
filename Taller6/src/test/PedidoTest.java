package test;

import main.Pedido;
import main.PedidoValorSuperadoExcetion;
import main.Producto;
import main.ProductoMenu;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    public void testPedidoEnCurso() {//Varias de estas pruebas son basicamente inútiles, lo sé.
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        assertTrue(pedido.getPedidoEnCurso());
    }

    @Test
    public void testCerrarPedido() {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        pedido.cerrarPedido();

        assertFalse(pedido.getPedidoEnCurso());
    }

    @Test
    public void testGetNombreCliente() {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        assertEquals("Johannes", pedido.getNombrecliente());
    }

    @Test
    public void testGetDireccionCliente() {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        assertEquals("123", pedido.getDireccionCliente());
    }

    @Test
    public void testGetItemsPedido() {
        ArrayList<Producto> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 50));
        items.add(new ProductoMenu("Papas",15));
        Pedido pedido = new Pedido("Johannes", "123", true, 1, items);

        assertEquals(items, pedido.getItemsPedido());

        ProductoMenu salsa = new ProductoMenu("Salsa", 5);
        try {
            pedido.agregarProducto(salsa);
        } catch (PedidoValorSuperadoExcetion e) {
            System.out.println(e.getMessage());
        }
        items.add(salsa);

        assertEquals(items, pedido.getItemsPedido());
    }

    @Test
    public void testGetIdPedido() {
        Pedido pedido = new Pedido("Johannes", "123", true, 15, new ArrayList<>());

        assertEquals(15, pedido.getIdPedido());
    }

    @Test
    public void testAgregarProducto() {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        ProductoMenu producto = new ProductoMenu("Hamburguesa", 5000);

        try {
            pedido.agregarProducto(producto);
        } catch (PedidoValorSuperadoExcetion e) {
            System.out.println(e.getMessage());
        }

        assertEquals(1, pedido.getItemsPedido().size());
        assertEquals(producto, pedido.getItemsPedido().get(0));

        ProductoMenu producto2 = new ProductoMenu("Papas", 1000);

        try {
            pedido.agregarProducto(producto2);
        } catch (PedidoValorSuperadoExcetion e) {
            System.out.println(e.getMessage());
        }

        assertEquals(2, pedido.getItemsPedido().size());
        assertEquals(producto2, pedido.getItemsPedido().get(1));

        ProductoMenu producto3 = new ProductoMenu("1 KG Oro Puro", 145000);

        assertThrows(PedidoValorSuperadoExcetion.class, 
        () -> {pedido.agregarProducto(producto3);});
    }

    @Test
    public void testAgregarProducto2() {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        ProductoMenu producto3 = new ProductoMenu("1 KG Oro Puro", 145000);

        assertThrows(PedidoValorSuperadoExcetion.class, 
        () -> {pedido.agregarProducto(producto3);});
    }

    @Test
    public void testGetPrecioNetoPedido() throws PedidoValorSuperadoExcetion {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        ProductoMenu item1 = new ProductoMenu("Hamburguesa", 20000);
        ProductoMenu item2 = new ProductoMenu("Papas", 5000);

        pedido.agregarProducto(item1);
        pedido.agregarProducto(item2);

        assertEquals(25000, pedido.getPrecioNetoPedido());
    }

    @Test
    public void testGetPrecioIVAPedido() throws PedidoValorSuperadoExcetion {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        ProductoMenu item1 = new ProductoMenu("Hamburguesa", 20000);
        ProductoMenu item2 = new ProductoMenu("Papas", 5000);

        pedido.agregarProducto(item1);
        pedido.agregarProducto(item2);

        int expectedVATPrice = (int) (25000 * 0.19);

        assertEquals(expectedVATPrice, pedido.getPrecioIVAPedido());
    }

    @Test
    public void testGetPrecioTotalPedido() throws PedidoValorSuperadoExcetion {
        Pedido pedido = new Pedido("Johannes", "123", true, 1, new ArrayList<>());

        ProductoMenu item1 = new ProductoMenu("Hamburguesa", 20000);
        ProductoMenu item2 = new ProductoMenu("Papas", 5000);

        pedido.agregarProducto(item1);
        pedido.agregarProducto(item2);

        int expectedTotalPrice = (int) (25000 * 1.19);

        assertEquals(expectedTotalPrice, pedido.getPrecioTotalPedido());
    }

    @Test
    public void testGuardarFactura() throws IOException, PedidoValorSuperadoExcetion {
        Pedido pedido = new Pedido("Johannes", "123", true, 999, new ArrayList<>());

        ProductoMenu item1 = new ProductoMenu("Hamburguesa", 20000);
        ProductoMenu item2 = new ProductoMenu("Papas", 5000);

        pedido.agregarProducto(item1);
        pedido.agregarProducto(item2);

        String expectedFacturaText = "Cliente: Johannes\n" +
                "Dirección: 123\n" +
                "Comida:\n" +
                "Hamburguesa:20000\n" +
                "Papas:5000\n" +
                "Total Neto:25000\n" +
                "IVA:4750\n" +
                "TOTAL:29750\n";

        pedido.guardarFactura();

        assertTrue(new File("Facturas/999.txt").exists());

        String facturaContent = Files.readString(Path.of("Facturas/999.txt"));

        assertEquals(expectedFacturaText, facturaContent);

        Files.deleteIfExists(Path.of("Facturas/999.txt"));
    }

    @Test
    public void testGuardarFactura2() throws IOException, PedidoValorSuperadoExcetion {
        Pedido pedido = new Pedido("Johannes", "123", true, 999, new ArrayList<>());

        String expectedFacturaText = "Cliente: Johannes\n" +
                "Dirección: 123\n" +
                "Comida:\n" +
                "Total Neto:0\n" +
                "IVA:0\n" +
                "TOTAL:0\n";

        pedido.guardarFactura();

        assertTrue(new File("Facturas/999.txt").exists());

        String facturaContent = Files.readString(Path.of("Facturas/999.txt"));

        assertEquals(expectedFacturaText, facturaContent);

        Files.deleteIfExists(Path.of("Facturas/999.txt"));
    }

    @Test
    public void generarTextoFactura_ReturnsCorrectText() {//Tuve que cambiar la visibilidad del método hasta publico para probarlo. (No sé si era necesario probarlo, como practicamente ya es probado en la prueba anterior.)
        Pedido pedido = new Pedido("Johannes", "456", true, 987, new ArrayList<>());

        ProductoMenu item1 = new ProductoMenu("Hamburguesa", 20000);
        ProductoMenu item2 = new ProductoMenu("PapasFrancesas", 5000);

        try {
            pedido.agregarProducto(item1);
            pedido.agregarProducto(item2);
        } catch (PedidoValorSuperadoExcetion e) {
            System.out.println(e.getMessage());
        }
        
        String expectedText = "Cliente: Johannes\n" +
                "Dirección: 456\n" +
                "Comida:\n" +
                "Hamburguesa:20000\n" +
                "PapasFrancesas:5000\n" +
                "Total Neto:25000\n" +
                "IVA:4750\n" +
                "TOTAL:29750\n";
        
        
        String actualText = pedido.generarTextoFactura();
        
        assertEquals(expectedText, actualText);
    }

}
