package main;
public class IngredienteRepetidoException extends HamburguesaException {
    private Ingredientes ingrediente;

    public IngredienteRepetidoException(Ingredientes ingrediente) {
        super("Ingrediente repetido: " + ingrediente.getNombre());
        this.ingrediente = ingrediente;
    }

    public String getIngrediente() {
        return ingrediente.getNombre();
    }
}