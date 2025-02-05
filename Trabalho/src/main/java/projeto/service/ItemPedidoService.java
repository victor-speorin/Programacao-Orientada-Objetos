package projeto.service;

import projeto.dao.DaoItemPedido;
import projeto.exception.ItemAindaAssociado;
import projeto.exception.EntidadeInexistente;
import projeto.model.ItemPedido;
import projeto.model.Pedido;
import projeto.util.FabricaDeDaos;

import java.util.List;


public class ItemPedidoService {
    private final DaoItemPedido daoItemPedido = FabricaDeDaos.getDAO(DaoItemPedido.class);

    public ItemPedido inclusao(ItemPedido itemPedido){
        itemPedido.getLivro().getItensPedidos().add(itemPedido);
        itemPedido.getPedido().getItensPedidos().add(itemPedido);
        daoItemPedido.incluir(itemPedido);
        return itemPedido;
    }

    public ItemPedido remocao(int id) throws ItemAindaAssociado, EntidadeInexistente {
        ItemPedido IP = ip_id(id);
        if (IP.getItens().isEmpty()){
            IP.getPedido().getItensPedidos().remove(IP);
            IP.getLivro().getItensPedidos().remove(IP);
        }
        else{
            throw new ItemAindaAssociado("Esse Item não pode ser removido!");
        }
        return IP;
    }

    public ItemPedido ip_id(int id) throws EntidadeInexistente {
        ItemPedido IP = daoItemPedido.recuperarPorId(id);
        if (IP == null){
            throw new EntidadeInexistente("Item Inexistente!");
        }
        else return IP;
    }

    public List<ItemPedido> recuperarItensPedidos() {
        return daoItemPedido.recuperarTodos();
    }
}
