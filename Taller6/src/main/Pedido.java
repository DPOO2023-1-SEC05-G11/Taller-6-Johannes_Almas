package main;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Pedido {
	
	private int idPedido;
	private boolean pedidoEnCurso;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido = new ArrayList<Producto>();
	
	public Pedido(String nombreCliente, String direccionCliente, boolean pedidoEnCurso, 
			int idPedido,ArrayList<Producto> itemsPedido)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.pedidoEnCurso = pedidoEnCurso;
		this.idPedido = idPedido;
		this.itemsPedido = itemsPedido;
		this.pedidoEnCurso = pedidoEnCurso;
		
	}
	
	public boolean getPedidoEnCurso() 
	{
		return this.pedidoEnCurso;
	}
	
	public void cerrarPedido() 
	{
		this.pedidoEnCurso = false;
	}
	
	public String getNombrecliente() 
	{
		return this.nombreCliente;
	}
	
	public String getDireccionCliente() 
	{
		return this.direccionCliente;
	}
	
	public ArrayList<Producto> getItemsPedido()
	{
		return this.itemsPedido;
	}
	
	public int getIdPedido() 
	{
		return this.idPedido;
	}
	
    public void agregarProducto(Producto item) throws PedidoValorSuperadoExcetion
    {
		if (getPrecioTotalPedido() + (int) item.getPrecio()*1.19 > 150000)
		{
			throw new PedidoValorSuperadoExcetion(this);
		}else{
			itemsPedido.add(item);
			System.out.println("The product "+item.getNombre()+" was added to Pedido "+idPedido+".");
		}
    }
	
	public int getPrecioNetoPedido() 
	{
		int suma = 0;
		for(int i=0;i<this.itemsPedido.size();i++) 
		{
			suma += this.itemsPedido.get(i).getPrecio();
		}
		
		return suma;
	}
	
	public int getPrecioIVAPedido() 
	{
		return (int) (getPrecioNetoPedido()*0.19);
	}
	
	public int getPrecioTotalPedido() 
	{
		return getPrecioIVAPedido()+getPrecioNetoPedido();
	}
	
	public void guardarFactura() throws IOException {
		File rutaFactura = new File("Facturas/" + Integer.toString(idPedido) + ".txt");
		rutaFactura.createNewFile();
		FileWriter writer = new FileWriter(rutaFactura);
		writer.write(generarTextoFactura());
		writer.close();
	}
	
	public String generarTextoFactura() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cliente: ").append(this.nombreCliente).append("\n");
		sb.append("Dirección: ").append(this.direccionCliente).append("\n");
		sb.append("Comida:\n");
		for (int i = 0; i < this.itemsPedido.size(); i++) {
			sb.append(this.itemsPedido.get(i).generarTextoFactura()).append("\n");
		}
		sb.append("Total Neto:").append(getPrecioNetoPedido()).append("\n");
		sb.append("IVA:").append(getPrecioIVAPedido()).append("\n");
		sb.append("TOTAL:").append(getPrecioTotalPedido()).append("\n");
		return sb.toString();
	}
	
	
	
	
}
