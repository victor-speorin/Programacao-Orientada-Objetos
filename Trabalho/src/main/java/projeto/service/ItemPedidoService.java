package projeto.service;

import projeto.dao.DaoItemPedido;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.model.ItemPedido;
import projeto.util.FabricaDeDaos;

public class ItemPedidoService {
    private final DaoItemPedido daoItemPedido = FabricaDeDaos.getDAO(DaoItemPedido.class);

    public ItemPedido inclusao(ItemPedido itemPedido){
        itemPedido.getLivro().getLivrospedidos().add(itemPedido);
        itemPedido.getPedido().getItensPedidos().add(itemPedido);
        daoItemPedido.incluir(itemPedido);
        return itemPedido;
    }

    public ItemPedido remocao(int id) throws ItemAindaAssociado, EntidadeInexistente {
        ItemPedido IP = ip_id(id);
        if (IP.getItens().isEmpty()){
            IP.getPedido().getItensPedidos().remove(IP);
            IP.getLivro().getLivrospedidos().remove(IP);
            daoItemPedido.remover(id);
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
}
