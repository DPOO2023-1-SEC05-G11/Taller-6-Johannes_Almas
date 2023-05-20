package main;


import java.util.ArrayList;

public class ProductoAjustado extends Producto
{
	private ProductoMenu base;
	private ArrayList<Ingredientes> agregados;
	private ArrayList<Ingredientes> eliminados;
	
	public ProductoAjustado(ProductoMenu base,ArrayList<Ingredientes> agregados,ArrayList<Ingredientes> eliminados)
	{
		this.base = base;
		this.agregados = agregados;
		this.eliminados = eliminados;
	}
	
	public String getNombre() 
	{
		return this.base.getNombre();
	}
	
	public int getPrecio() 
	{
		int adicion = 0;
		if (this.eliminados != null){
			for(int i=0;i<this.agregados.size();i++) 
			{
				adicion += this.agregados.get(i).getcostoAdicional();
			}
		}
		return this.base.getPrecio() + adicion;
	}
	
	public String generarTextoFactura() {
    String concatenacion = this.base.getNombre();
    
    if (this.eliminados == null){
	}else if(this.eliminados.size() > 0) {
        concatenacion += " sin ";
        for (int i = 0; i < this.eliminados.size(); i++) {
            concatenacion += this.eliminados.get(i).getNombre();
            if (i != this.eliminados.size() - 1) {
                concatenacion += ", ";
            }
        }
    }
    
	if (this.agregados == null){
	}else if (this.agregados.size() > 0) {
        concatenacion += " con ";
        for (int i = 0; i < this.agregados.size(); i++) {
            concatenacion += this.agregados.get(i).getNombre();
            if (i != this.agregados.size() - 1) {
                concatenacion += ", ";
            }
        }
    }
    
    concatenacion += ": " + Integer.toString(getPrecio());
    return concatenacion;
}

}	
