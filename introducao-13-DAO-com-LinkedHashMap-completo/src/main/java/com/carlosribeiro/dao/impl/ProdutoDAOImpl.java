package com.carlosribeiro.dao.impl;

import com.carlosribeiro.model.Produto;
import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.util.ComparatorDeProdutosPorNome;

import java.util.*;

public class ProdutoDAOImpl implements ProdutoDAO {
    private final Map<Integer, Produto> map = new LinkedHashMap<>(10);

    public Produto incluir(Produto produto) {
        return map.put(produto.getNumero(), produto);
    }

    public Produto remover(int id) {
        return map.remove(id);
    }

    public Produto recuperarProdutoPorNumero(int numero) {
        return map.get(numero);
    }

    public List<Produto> recuperarProdutos() {
        return new ArrayList<Produto>(map.values());
    }

    public List<Produto> recuperarProdutosOrdenadosAscendentementePorNome() {
        ArrayList<Produto> listaDeProdutos = new ArrayList<>(map.values());
        listaDeProdutos.sort(new ComparatorDeProdutosPorNome());
        return listaDeProdutos;
    }

    public List<Produto> recuperarProdutosOrdenadosDescendentementePorNome() {
        ArrayList<Produto> listaDeProdutos = new ArrayList<>(map.values());
        listaDeProdutos.sort(new ComparatorDeProdutosPorNome().reversed());
        return listaDeProdutos;
    }

    public boolean listaVazia() {
        return map.isEmpty();
    }
}
