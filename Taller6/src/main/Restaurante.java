package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Restaurante {
	
	private static int numeroPedidos = 0;
	private Pedido pedidoEnCurso;
	private ArrayList<Ingredientes> ingredientes;
	private ArrayList<ProductoMenu> productosMenu;
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
	public Restaurante() {}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		ArrayList<Producto> itemsPedido= new ArrayList<Producto>(); 
		pedidoEnCurso = new Pedido(nombreCliente,direccionCliente,true, numeroPedidos+1,itemsPedido);
		numeroPedidos++;
	}
	
	public void cerrarYGuardarPedido() throws IOException {
		pedidoEnCurso.cerrarPedido();
		pedidoEnCurso.guardarFactura();
		pedidos.add(pedidoEnCurso);}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;}
	
	public ArrayList<Ingredientes> getIngrendientes(){
		return ingredientes;}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException, IngredienteRepetidoException, ProductoRepetidoException {
		ingredientes = cargarIngredientes(archivoIngredientes);
		productosMenu = cargarMenu(archivoMenu);
		combos = cargarCombos(archivoCombos);
	}
	
	public Pedido getId(int id) {return pedidos.get(id-1);}

	private ArrayList<Combo> cargarCombos(File archivoCombos) throws IOException, ProductoRepetidoException {
		 try (BufferedReader br = new BufferedReader(new FileReader(archivoCombos))) {
			String st;
			ArrayList<Combo> combos = new ArrayList<Combo>();
			 while ((st = br.readLine()) != null) {		 
			        String[] split = st.split(";");
			        String name = split[0];
			        double descuento = Double.parseDouble(split[1].replace("%",""))/100;
			        ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
			        for (int i = 2; i < split.length; i++) {
						ProductoMenu producto = new ProductoMenu(split[i], getPrecio(split[i]));
		
						if (productos.contains(producto)) {
							throw new ProductoRepetidoException(producto);
						}
		
						productos.add(producto);
					}
			        Combo combo = new Combo(name,descuento,productos);
			        combos.add(combo);}}
		 return combos;}
	
	private int getPrecio(String name) {
		int price = 0;
		for(int i=0;i<productosMenu.size();i++) {
			if(productosMenu.get(i).getNombre() == name) {
				price =  productosMenu.get(i).getPrecio();
			}
		}
		return price;
	}
	
	private ArrayList<ProductoMenu> cargarMenu(File archivoMenu) throws IOException, ProductoRepetidoException {
		try (BufferedReader br = new BufferedReader(new FileReader(archivoMenu))) {
			String st;
			ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
			Set<String> nombresProductos = new HashSet<>();
	
			while ((st = br.readLine()) != null) {
				String[] split = st.split(";");
				ProductoMenu productoMenu = new ProductoMenu(split[0], Integer.parseInt(split[1]));
	
				if (nombresProductos.contains(productoMenu.getNombre())) {
					throw new ProductoRepetidoException(productoMenu);
				}
	
				nombresProductos.add(productoMenu.getNombre());
				productos.add(productoMenu);
			}
	
			return productos;
		}
	}
	
	
	private ArrayList<Ingredientes> cargarIngredientes(File archivoIngredientes) throws FileNotFoundException, IOException, IngredienteRepetidoException {
		try (BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes))) {
			String st;
			ArrayList<Ingredientes> ingredientes = new ArrayList<Ingredientes>();
			Set<String> nombresIngredientes = new HashSet<>();
	
			while ((st = br.readLine()) != null) {
				String[] split = st.split(";");
				Ingredientes ingrediente = new Ingredientes(split[0], Integer.parseInt(split[1]));
	
				if (nombresIngredientes.contains(ingrediente.getNombre())) {
					throw new IngredienteRepetidoException(ingrediente);
				}
	
				nombresIngredientes.add(ingrediente.getNombre());
				ingredientes.add(ingrediente);
			}
	
			return ingredientes;
		}
	}
	

	
	public ArrayList<ProductoMenu> getMenuBase(){
		return productosMenu;}

	public ArrayList<Combo> getCombos(){
		return combos;}
	
	public int getNumeroPedidos(){return numeroPedidos;}

	public void addProductoCurrentPedido(int i) throws PedidoValorSuperadoExcetion {
		pedidoEnCurso.agregarProducto(productosMenu.get(i));
	}
}
