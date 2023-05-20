package main;


public class Ingredientes {
	private String nombre;
	private int costoAdicional;
	//methods
	public Ingredientes(String nombre, int costoAdicional)	
	{
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public int getcostoAdicional()
	{
		return this.costoAdicional;
	}
}

