package projeto.model;

import projeto.util.ID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemPedido implements Serializable {

    @ID
    private int id;
    private int qtdPedida;
    private int qtdFaltante;
    private double precoCobrado;
    private Livro livro;
    private Pedido pedido;
    private List<ItemFaturado> itens;

    public ItemPedido(int qtdPedida, Livro livro, Pedido pedido) {
        this.qtdPedida = qtdPedida;
        this.qtdFaltante = qtdPedida;
        setPrecoCobrado(livro,qtdPedida);
        this.livro = livro;
        this.pedido = pedido;
        this.itens = new ArrayList<ItemFaturado>();
    }

    public String listarItemPedido() {
        return "Item Pedido: " + "ID: " + id + " Titulo: " + livro.getTitulo() + " na quantidade " + qtdPedida + " e seu preço total ficou " + precoCobrado ;
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

    public void setPrecoCobrado(Livro livro, int qtdPedida) {
        this.precoCobrado = livro.getPreco() * qtdPedida;
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

    public void setQtdFaltante(int qtdFaltante) {this.qtdFaltante = qtdFaltante;}

    public int getQtdFaltante() {return qtdFaltante;}
}
