package projeto.dao;

import projeto.model.ItemFaturado;
import projeto.model.Livro;

import java.util.List;

public interface DaoItemFaturado extends DaoGenerico<ItemFaturado> {
    public List<ItemFaturado> recuperarItensFaturadosDeUmPedido(int id);
    public List<ItemFaturado> recuperarItensFaturadosDeUmLivro(Livro livro);
}