package main;


public class ProductoMenu extends Producto
{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase) 
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	public String getNombre()
	{
		return this.nombre;	
	}
	
	public int getPrecio() 
	{
		return this.precioBase;
	}
	
	public String generarTextoFactura() 
	{
		String concatenacion = getNombre();
		concatenacion += ":" + Integer.toString(getPrecio());
		return concatenacion;
	}
}
