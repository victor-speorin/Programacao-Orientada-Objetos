package projeto.model;

import projeto.util.ID;
import java.io.Serializable;

public class ItemFaturado implements Serializable {
    @ID
    private int id;
    private int qtdFaturada;
    private ItemPedido pedido;
    private Fatura fatura;

    public ItemFaturado(int qtdFaturada, ItemPedido pedido, Fatura fatura) {
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

    @Override
    public String toString() {
        return "ItemFaturado: " +
                "id= " + id + " referente ao pedido " + pedido.getPedido().getNumPedido() +
                " e fatura " + fatura.getNumFatura() +
                ". Quantidade faturada: " + qtdFaturada +
                ", item pedido: " + pedido.getLivro().getTitulo() +
                ", quantidade pedida e quantidade faltante: " + pedido.getQtdPedida() +
                ", " + pedido.getQtdFaltante();
    }


    public int getQtdFaturada() {
        return qtdFaturada;
    }

    public void setQtdFaturada(int qtdFaturada) {
        if(qtdFaturada >= pedido.getLivro().getQtdEstoque()) this.qtdFaturada = pedido.getLivro().getQtdEstoque();
        else this.qtdFaturada = qtdFaturada;
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
