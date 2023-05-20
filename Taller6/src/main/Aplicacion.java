package main;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;




public class Aplicacion {
	private Restaurante restaurante = new Restaurante();
	public static void main(String[]args) throws IOException {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
	public void mostrarMenu() {
		System.out.println("1.Mostrar el Menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la información de un pedido anterior dado su id");
		System.out.println("6. Salir");}
	public void ejecutarAplicacion() throws IOException {
		boolean continuar = true;
		cargarDatos();
		while (continuar) {
			mostrarMenu();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1) {
				mostrarMenuRestaurante();}
			else if (opcion_seleccionada == 2) {
				IniciarPedido();}
			else if (opcion_seleccionada == 3) {
				AgregarElementoPedido();}
			else if (opcion_seleccionada == 4) {
				CerrarPedidoFactura();}
			else if (opcion_seleccionada == 5) {
				InfoPedido();}
			else if (opcion_seleccionada == 6) {
				continuar = false;}
		}
	}
	private void cargarDatos() throws IOException {
		File ingredientes = new File("data/ingredientes.txt");
		File menu = new File("data/menu.txt");
		File combos = new File("data/combos.txt");
		try {
			restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
		} catch (IngredienteRepetidoException e) {
			System.out.println("Error: " + e.getMessage());
		}catch (ProductoRepetidoException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	private void InfoPedido() {
		int id = Integer.parseInt(input("Ingrese una id"));
		try{
			Pedido pedido = restaurante.getId(id);
			System.out.println("Cliente: " + pedido.getNombrecliente());
			System.out.println("Dirección: " + pedido.getDireccionCliente());
			System.out.println("Precio Total: " + pedido.getPrecioTotalPedido());
			System.out.println("IVA: " + pedido.getPrecioIVAPedido());
			System.out.println("Productos pedidos: ");
			for(int i=0;i<pedido.getItemsPedido().size();i++) {System.out.println(pedido.getItemsPedido().get(i).getNombre());}
		}catch(IndexOutOfBoundsException e){
			System.out.println("El id ingresada no se puede aceder. \nO no existe, o es el pedido en curso. \nSi es el pedido en curso, terminalo antes de consultar.");
		}
	}
	private void CerrarPedidoFactura() throws IOException {
		restaurante.cerrarYGuardarPedido();
		
	}
	private void AgregarElementoPedido() {
		mostrarMenuRestaurante();
		int seleccionado = Integer.parseInt(input("Por favor seleccione el número del producto que quiera"));
		if (restaurante.getPedidoEnCurso() == null)
		{
			System.out.println("No hay ningún pedido en curso. \nPor favor inicia un nuevo pedido antes de agregar productos.");
		}else
		{
			try {
				restaurante.addProductoCurrentPedido(seleccionado-1);
			} catch (PedidoValorSuperadoExcetion e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	private void IniciarPedido() { 
		if (restaurante.getNumeroPedidos() == 0 || restaurante.getPedidoEnCurso().getPedidoEnCurso() == false) {
			
		String nombre = input("Ingrese su nombre");
		String direccion = input("Ingrese su dirección ");
		restaurante.iniciarPedido(nombre, direccion);
		System.out.println("Pedido creado exitosamento con id:" + restaurante.getPedidoEnCurso().getIdPedido()); }
		else { System.out.println("Ya hay un pedido en curso.");}
		
	}
	private void mostrarMenuRestaurante() {
		ArrayList<ProductoMenu> productosMenu = restaurante.getMenuBase();
		System.out.println("Menú (Nombre,Precio)");
		for(int i = 0;i<productosMenu.size();i++) {
			String nombre = productosMenu.get(i).getNombre();
			int precio = productosMenu.get(i).getPrecio();
			System.out.println(i+1 + ". " + nombre + " " + precio + ".");
		}
	
		
	}
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
