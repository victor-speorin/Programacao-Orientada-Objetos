package projeto.dao;

import projeto.exception.EntidadeInexistente;
import projeto.model.Livro;

import java.util.List;

public interface DaoLivro extends DaoGenerico<Livro>{
    public List<Livro> recuperarTodosLivrosNuncaFaturados();
}