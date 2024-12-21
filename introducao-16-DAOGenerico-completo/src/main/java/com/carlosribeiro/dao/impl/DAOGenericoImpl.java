package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.DAOGenerico;
import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.model.Produto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DAOGenericoImpl<K, V> implements DAOGenerico<K, V> {
    protected final Map<K, V> map = new LinkedHashMap<>(16);

    public V incluir(K id, V obj) {
        return map.put(id, obj);
    }

    public V alterar(K id, V obj) {
        return map.put(id, obj);
    }

    public V remover(K id) {
        return map.remove(id);
    }

    public V recuperarPorId(K id) {
        return map.get(id);
    }

    public List<V> recuperarTodos() {
        // Porque List tem método get(index) que Collection não possui.
        return new ArrayList<>(map.values()); // .stream().toList();  // java.util.LinkedHashMap$LinkedProdutoalues
    }
}
