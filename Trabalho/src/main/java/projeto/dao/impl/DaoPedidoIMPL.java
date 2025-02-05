package projeto.dao.impl;

import projeto.dao.DaoPedido;
import projeto.model.Pedido;

import java.util.List;

public class DaoPedidoIMPL extends DaoGenericoIMPL<Pedido> implements DaoPedido {
    public List<Pedido> recuperarPedidosDeUmCliente(int id){
        return map.values()
                .stream()
                .filter((pedido) -> (pedido.getCliente()).getId() == id)
                .toList();
    }
}