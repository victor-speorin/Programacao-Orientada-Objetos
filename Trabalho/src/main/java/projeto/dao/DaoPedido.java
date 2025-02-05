package projeto.dao;

import projeto.model.Pedido;

import java.util.List;

public interface DaoPedido extends DaoGenerico<Pedido>{
    public List<Pedido> recuperarPedidosDeUmCliente(int id);
}