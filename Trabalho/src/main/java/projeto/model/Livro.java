package projeto.model;

import projeto.util.ID;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import projeto.dao.DaoCliente;
import projeto.dao.DaoItemFaturado;
import projeto.exception.EntidadeInexistente;
import projeto.service.LivroService;
import projeto.util.FabricaDeDaos;

public class Livro implements Serializable {

    @ID
    private int id;
    private String isbn;
    private String titulo;
    private String descricao;
    private int qtdEstoque;
    private double preco;
    private List<ItemPedido> livrospedidos;

    public Livro(String isbn, String titulo, String descricao, double preco, int qtdEstoque) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.livrospedidos = new ArrayList<ItemPedido>();
    }

    @Override
    public String toString() {
        return "Livro: " +
                "id: " + id +
                ", titulo: '" + titulo + '\'' +
                ", isbn: '" + isbn + '\'' +
                ", descricao: '" + descricao + '\'' +
                ", quantidade no estoque: " + qtdEstoque +
                ", preco: " + preco + '\n';
    }

    public String livroEQuantidade(){
        return "Livro{id=" + id + ", titulo=" + titulo + ", quantidade no estoque=" + qtdEstoque + '}';
    }

    public List<ItemPedido> getItensPedidos() {
        return livrospedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.livrospedidos = itensPedidos;
    }

    public int getId() { return id;}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean nuncaFaturado(){
        for(ItemPedido item: livrospedidos){
            if(!(item.getItens().isEmpty())) return false;
        }
        return true;
    }

}
