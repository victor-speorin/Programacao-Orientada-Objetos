package projeto.service;

import projeto.dao.DaoPedido;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.model.Pedido;
import projeto.util.FabricaDeDaos;

public class PedidoService {
    private final DaoPedido daoPedido = FabricaDeDaos.getDAO(DaoPedido.class);

    public Pedido inclusao(Pedido pedido) {
        pedido.getCliente().getPedidos().add(pedido);
        return daoPedido.incluir(pedido);
    }

    public Pedido remover(int id) throws EntidadeInexistente, ItemAindaAssociado {
        Pedido pedido = pedido_id(id);
        if (pedido.getItensPedidos().isEmpty()) {
            pedido.getCliente().getPedidos().remove(pedido);
            daoPedido.remover(id);
        }
        else{
            throw new ItemAindaAssociado("Esse pedido não pode ser removido!");
        }
        return pedido;
    }

    public Pedido pedido_id(int id) throws EntidadeInexistente{
        Pedido pedido = daoPedido.recuperarPorId(id);
        if (pedido == null){
            throw new EntidadeInexistente("Esse pedido não existe!");
        }
        else return pedido;
    }
}
