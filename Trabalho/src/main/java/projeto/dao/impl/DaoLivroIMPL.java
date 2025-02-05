package projeto.dao.impl;

import projeto.dao.DaoLivro;
import projeto.exception.EntidadeInexistente;
import projeto.model.ItemFaturado;
import projeto.model.ItemPedido;
import projeto.model.Livro;
import projeto.model.Pedido;

import java.util.List;

public class DaoLivroIMPL extends DaoGenericoIMPL<Livro> implements DaoLivro {
    public List<Livro> recuperarTodosLivrosNuncaFaturados(){
        return map.values()
                .stream()
                .filter((livro) -> (livro.nuncaFaturado()))
                .toList();
    }

}