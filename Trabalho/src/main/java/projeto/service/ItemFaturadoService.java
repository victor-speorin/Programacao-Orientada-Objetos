package projeto.service;

import projeto.dao.DaoItemFaturado;
import projeto.dao.DaoLivro;
import projeto.exception.EntidadeInexistente;
import projeto.exception.QtdeInvalida;
import projeto.model.ItemFaturado;
import projeto.model.Livro;
import projeto.util.FabricaDeDaos;
import java.util.List;


public class ItemFaturadoService {
    DaoItemFaturado daoItemFaturado = FabricaDeDaos.getDAO(DaoItemFaturado.class);
    DaoLivro livroDao = FabricaDeDaos.getDAO(DaoLivro.class);

    public ItemFaturado inclusao(ItemFaturado itemFaturado) throws QtdeInvalida{
        int aux = itemFaturado.getPedido().getQtdFaltante();
        if(itemFaturado.getQtdFaturada() > itemFaturado.getPedido().getQtdPedida()){
            throw new QtdeInvalida("Existem mais itens faturados que itens pedidos!");
        }

        int qtdEst = itemFaturado.getPedido().getLivro().getQtdEstoque();
        if(qtdEst == 0){
            System.out.println('\n' + "Nao eh possivel faturar o item " + itemFaturado.getPedido().getLivro().getId() + " referente ao pedido " + itemFaturado.getPedido().getPedido().getNumPedido() + " , ja que o seu estoque esta zerado");
            itemFaturado.getPedido().setQtdFaltante(aux);
            return itemFaturado;
        }
        if(qtdEst <= itemFaturado.getQtdFaturada()){
            itemFaturado.setQtdFaturada(qtdEst);
            itemFaturado.getPedido().getLivro().setQtdEstoque(0);
        }
        else{
            itemFaturado.getPedido().getLivro().setQtdEstoque(qtdEst - itemFaturado.getQtdFaturada());
        }
        if(itemFaturado.getQtdFaturada() > itemFaturado.getPedido().getQtdFaltante()) itemFaturado.getPedido().setQtdFaltante(0);
        else itemFaturado.getPedido().setQtdFaltante(itemFaturado.getPedido().getQtdFaltante() - itemFaturado.getQtdFaturada());

        itemFaturado.getPedido().getItens().add(itemFaturado);
        itemFaturado.getFatura().getItensFaturados().add(itemFaturado);
        return daoItemFaturado.incluir(itemFaturado);
    }

    public ItemFaturado remocao(int id)throws EntidadeInexistente {
        ItemFaturado IF = if_id(id);
        IF.getPedido().setQtdFaltante(IF.getQtdFaturada() + IF.getPedido().getQtdFaltante());
        IF.getPedido().getLivro().setQtdEstoque(IF.getPedido().getLivro().getQtdEstoque() + IF.getQtdFaturada());
        IF.getPedido().getItens().remove(IF);
        IF.getFatura().getItensFaturados().remove(IF);
        if (IF.getFatura().getItensFaturados().isEmpty()){IF.getPedido().getPedido().setFaturado(0);}
        return daoItemFaturado.remover(id);
    }

    public ItemFaturado if_id(int id) throws EntidadeInexistente {
        ItemFaturado IF = daoItemFaturado.recuperarPorId(id);
        if (IF == null){
            throw new EntidadeInexistente("Item Inexistente!");
        }
        else return IF;
    }

    public List<ItemFaturado> recuperarItensFaturados(){
        return daoItemFaturado.recuperarTodos();
    }

    public List<ItemFaturado> recuperarItensFaturadosPorPedido(int id){
        return daoItemFaturado.recuperarItensFaturadosDeUmPedido(id);
    }

    public List<ItemFaturado> recuperarItensFaturadosPorLivro(int id) throws EntidadeInexistente {
        Livro livro = livroDao.recuperarPorId(id);
        return daoItemFaturado.recuperarItensFaturadosDeUmLivro(livro);
    }
}
