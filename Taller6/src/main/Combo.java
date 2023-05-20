package main;


import java.util.*;

public class Combo extends Producto
{
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> ItemsCombo;
	
	public Combo(String nombreCombo, double descuento, ArrayList<ProductoMenu> ItemsCombo) 
	{
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
		this.ItemsCombo = ItemsCombo;
	}
	
	public double getDescuento(){
		return this.descuento;
	}

	public void agregarItemACombo(ProductoMenu itemCombo) 
	{
		this.ItemsCombo.add(itemCombo);
	}
	
	public int getPrecio() 
	{
		int suma = 0;
		for(int i=0;i<this.ItemsCombo.size();i++) 
		{
			suma += this.ItemsCombo.get(i).getPrecio();
		}
		
		return (int)(suma*(1-this.descuento));
	}
	
    public ArrayList<ProductoMenu> getItemsCombo() {
        return this.ItemsCombo;
    }
	
	public String getNombre() 
	{
		return this.nombreCombo;
	}
	
	public String generarTextoFactura() 
	{
		String concatenacion = getNombre();
		concatenacion += ":" + Integer.toString(getPrecio());
		return concatenacion;
	}
	
}
