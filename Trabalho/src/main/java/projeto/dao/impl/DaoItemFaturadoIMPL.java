package projeto.dao.impl;

import projeto.dao.DaoItemFaturado;
import projeto.model.ItemFaturado;
import projeto.model.Livro;
import projeto.model.Pedido;

import java.util.List;

public class DaoItemFaturadoIMPL extends DaoGenericoIMPL<ItemFaturado> implements DaoItemFaturado {
    public List<ItemFaturado> recuperarItensFaturadosDeUmPedido(int id){
        return map.values()
                .stream()
                .filter((itemFaturado) -> (itemFaturado.getPedido().getPedido().getNumPedido() == id))
                .toList();
    }
    public List<ItemFaturado> recuperarItensFaturadosDeUmLivro(Livro livro){
        return map.values()
                .stream()
                .filter((itemFaturado) -> (itemFaturado.getPedido().getLivro().getId() == livro.getId()))
                .toList();
    }
}