package main;
public class PedidoValorSuperadoExcetion extends HamburguesaException {
    private Pedido pedido;

    public PedidoValorSuperadoExcetion(Pedido pedido) {
        super("El precio del pedido " + pedido.getIdPedido() + "(" + pedido.getPrecioTotalPedido() 
        + ") será superior al precio limite de 150.000COP.\nNo puede agregar este producto.");
        this.pedido = pedido;
    }

    public int getProducto() {
        return pedido.getIdPedido();
    }
}