package projeto.model;

import projeto.util.ID;

import java.util.ArrayList;
import java.util.List;

public class Livro {

    @ID
    private int id;
    private String isbn;
    private String titulo;
    private String descricao;
    private int qtdEstoque;
    private double preco;
    private List<ItemPedido> livrospedidos;

    public Livro(String isbn, String titulo, String descricao, double preco) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.livrospedidos = new ArrayList<ItemPedido>();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public List<ItemPedido> getLivrospedidos() {
        return livrospedidos;
    }

    public void setLivrospedidos(List<ItemPedido> livrospedidos) {
        this.livrospedidos = livrospedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
