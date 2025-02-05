package projeto.service;

import corejava.Console;
import projeto.dao.DaoPedido;
import projeto.exception.ItemAindaAssociado;
import projeto.exception.DataInvalida;
import projeto.exception.EntidadeInexistente;
import projeto.model.Cliente;
import projeto.model.Fatura;
import projeto.model.ItemFaturado;
import projeto.model.Pedido;
import projeto.util.FabricaDeDaos;
import java.util.List;

public class PedidoService {
    private final DaoPedido daoPedido = FabricaDeDaos.getDAO(DaoPedido.class);
    private final ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
    private final FaturaService faturaService = new FaturaService();

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

    public void cancelar(int numPedido) throws EntidadeInexistente, DataInvalida, ItemAindaAssociado {
        Pedido umPedido = pedido_id(numPedido);
        String dataCancelamento = Console.readLine('\n' + "Digite a data do cancelamento no modelo DD/MM/YYYY: ");
        umPedido.setDataCancelamento(dataCancelamento);
        umPedido.setStatus("Cancelado");
        List<ItemFaturado> itensFaturados = itemFaturadoService.recuperarItensFaturadosPorPedido(umPedido.getNumPedido());
        for(ItemFaturado itemFaturado : itensFaturados){
            itemFaturadoService.remocao(itemFaturado.getId());
        }
        List<Fatura> faturas = faturaService.recuperarFaturasPorPedido(umPedido.getNumPedido());
        for(Fatura fatura : faturas){
            faturaService.remocao(fatura.getNumFatura());
        }
        System.out.println('\n' + "O Pedido de numero " + umPedido.getNumPedido() + " foi cancelado com sucesso!");
    }

    public void cancelarDefinido(Pedido umPedido, String dataCancelamento) throws EntidadeInexistente, DataInvalida, ItemAindaAssociado {
        if(umPedido.getFaturado() == 1){
            System.out.println('\n' + "Nao foi possivel cancelar o pedido de numero " + umPedido.getNumPedido() + " ja que ele esta faturado.");
        }
        else{
            umPedido.setDataCancelamento(dataCancelamento);
            umPedido.setStatus("Cancelado");
            List<ItemFaturado> itensFaturados = itemFaturadoService.recuperarItensFaturadosPorPedido(umPedido.getNumPedido());
            for(ItemFaturado itemFaturado : itensFaturados){
                itemFaturadoService.remocao(itemFaturado.getId());
            }
            List<Fatura> faturas = faturaService.recuperarFaturasPorPedido(umPedido.getNumPedido());
            for(Fatura fatura : faturas){
                faturaService.remocao(fatura.getNumFatura());
            }
            System.out.println('\n' + "O pedido de numero" + umPedido.getNumPedido() + " foi cancelado com sucesso!");
        }
    }

    public Pedido pedido_id(int id) throws EntidadeInexistente{
        Pedido pedido = daoPedido.recuperarPorId(id);
        if (pedido == null){
            throw new EntidadeInexistente("Esse pedido não existe!");
        }
        else return pedido;
    }

    public List<Pedido> recuperarPedidos() {
        return daoPedido.recuperarTodos();
    }
    public List<Pedido> recuperarPedidosPorCliente(int id) {
        return daoPedido.recuperarPedidosDeUmCliente(id);
    }
}
