package projeto.model;

import projeto.util.ID;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {

    @ID
    private int id;
    private int qtdPedida;
    private double precoCobrado;
    private Livro livro;
    private Pedido pedido;
    private List<ItemFaturado> itens;

    public ItemPedido(int id, int qtdPedida, double precoCobrado, Livro livro, Pedido pedido) {
        this.qtdPedida = qtdPedida;
        this.precoCobrado = precoCobrado;
        this.livro = livro;
        this.pedido = pedido;
        this.itens = new ArrayList<ItemFaturado>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdPedida() {
        return qtdPedida;
    }

    public void setQtdPedida(int qtdPedida) {
        this.qtdPedida = qtdPedida;
    }

    public double getPrecoCobrado() {
        return precoCobrado;
    }

    public void setPrecoCobrado(double precoCobrado) {
        this.precoCobrado = precoCobrado;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<ItemFaturado> getItens() {
        return itens;
    }

    public void setItens(List<ItemFaturado> itens) {
        this.itens = itens;
    }
}
