package test;

import main.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductoMenuTest {
    
    @Test
    public void generarTextoFactura_Hamburguesa_100() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 100);
        String expected = "Hamburguesa:100";
        
        String result = producto.generarTextoFactura();
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void generarTextoFactura_PapasFritas_50() {
        ProductoMenu producto = new ProductoMenu("Papas Fritas", 50);
        String expected = "Papas Fritas:50";
        
        String result = producto.generarTextoFactura();
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void generarTextoFactura_TorskeleverMedSylteagurk_3000() {
        ProductoMenu producto = new ProductoMenu("Torskelever med Sylteagurk", 3000);
        String expected = "Torskelever med Sylteagurk:3000";
        
        String result = producto.generarTextoFactura();
        
        Assertions.assertEquals(expected, result);
    }
    
    
}
