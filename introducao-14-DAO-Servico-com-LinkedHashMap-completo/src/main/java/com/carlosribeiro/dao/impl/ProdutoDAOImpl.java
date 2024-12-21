package com.carlosribeiro.dao.impl;

import com.carlosribeiro.model.Produto;
import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.util.ComparatorDeProdutosPorNome;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    private final LinkedHashMap<Integer, Produto> linkedHashMap = new LinkedHashMap<>(10);

    public Produto incluir(Produto produto) {
        return linkedHashMap.put(produto.getNumero(), produto);
    }

    public Produto remover(int id) {
        return linkedHashMap.remove(id);
    }

    public Produto recuperarProdutoPorNumero(int numero) {
        return linkedHashMap.get(numero);
    }

    public List<Produto> recuperarProdutos() {
        return new ArrayList<Produto>(linkedHashMap.values());
    }

    public List<Produto> recuperarProdutosOrdenadosAscendentementePorNome() {
        ArrayList<Produto> listaDeProdutos = new ArrayList<>(linkedHashMap.values());
        listaDeProdutos.sort(new ComparatorDeProdutosPorNome());
        return listaDeProdutos;
    }

    public List<Produto> recuperarProdutosOrdenadosDescendentementePorNome() {
        ArrayList<Produto> listaDeProdutos = new ArrayList<>(linkedHashMap.values());
        listaDeProdutos.sort(new ComparatorDeProdutosPorNome().reversed());
        return listaDeProdutos;
    }

    public boolean listaVazia() {
        return linkedHashMap.isEmpty();
    }
}
