package projeto.model;

import projeto.util.ID;

public class ItemFaturado {
    @ID
    private int id;
    private int qtdFaturada;
    private ItemPedido pedido;
    private Fatura fatura;

    public ItemFaturado(int id, int qtdFaturada, ItemPedido pedido, Fatura fatura) {
        this.qtdFaturada = qtdFaturada;
        this.pedido = pedido;
        this.fatura = fatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdFaturada() {
        return qtdFaturada;
    }

    public void setQtdFaturada(int qtdFaturada) {
        this.qtdFaturada = qtdFaturada;
    }

    public ItemPedido getPedido() {
        return pedido;
    }

    public void setPedido(ItemPedido pedido) {
        this.pedido = pedido;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }
}
