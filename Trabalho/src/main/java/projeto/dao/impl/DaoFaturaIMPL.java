package projeto.dao.impl;

import projeto.dao.DaoFatura;
import projeto.model.Fatura;
import projeto.model.Pedido;

import java.util.List;

public class DaoFaturaIMPL extends DaoGenericoIMPL<Fatura> implements DaoFatura {
    public List<Fatura> listarFaturas(int id) {
        return map.values()
                .stream()
                .filter((fatura -> (fatura.getPrimeiroItem().getPedido().getPedido().getNumPedido()==id)))
                .toList();
    }
}
