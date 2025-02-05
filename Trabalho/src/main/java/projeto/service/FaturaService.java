package projeto.service;

import corejava.Console;
import projeto.dao.DaoFatura;
import projeto.dao.DaoLivro;
import projeto.exception.ItemAindaAssociado;
import projeto.exception.DataInvalida;
import projeto.exception.EntidadeInexistente;
import projeto.model.*;
import projeto.util.FabricaDeDaos;
import projeto.service.LivroService;

import java.util.ArrayList;
import java.util.List;

public class FaturaService {
    private final DaoFatura daoFatura = FabricaDeDaos.getDAO(DaoFatura.class);
    private final ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
    private final LivroService livroService = new LivroService();

    public Fatura inclusao(String dataFatura, Pedido pedido) throws DataInvalida {
        if (pedido.getFaturado() == 1){
            System.out.println("\nO pedido " + pedido.getNumPedido() + " ja esta integralmente faturado");
            return null;
        }
        Cliente cliente = pedido.getCliente();
        Fatura fatura = new Fatura(dataFatura, cliente);
        List<ItemPedido> itens = pedido.getItensPedidos();
        for (ItemPedido itemPedido : itens) {
            ItemFaturado itemFaturado = new ItemFaturado(itemPedido.getQtdPedida(), itemPedido, fatura);
            itemFaturadoService.inclusao(itemFaturado);
        }
        List<ItemFaturado> itensFaturados = fatura.getItensFaturados();
        if(itensFaturados.isEmpty()){
            System.out.println("\nNao foi possivel faturar o pedido " + pedido.getNumPedido() + " pois o estoque para o(s) livro(s) solicitados esta zerado.");
            return null;
        }
        fatura.setValorTotal();
        fatura.setValorTotalDesconto();
        fatura.getCliente().getFaturas().add(fatura);
        ItemFaturado itemFaturado = fatura.getItensFaturados().get(0);
        List<ItemPedido> itensPedidos = itemFaturado.getPedido().getPedido().getItensPedidos();
        int conts = 0, contn = 0;
        for(ItemPedido itemPedido : itensPedidos) {
            if(itemPedido.getQtdFaltante() == 0) conts++;
            if(itemPedido.getQtdFaltante() == itemPedido.getQtdPedida()) contn++;
        }
        if(conts == itensPedidos.size()) itemFaturado.getPedido().getPedido().setStatus("Pago");
        if((contn < itensPedidos.size()) && contn > 0) itemFaturado.getPedido().getPedido().setStatus("Pagando");
        if(contn == itensPedidos.size()) itemFaturado.getPedido().getPedido().setStatus("Em Aberto");
        itemFaturado.getPedido().getPedido().setFaturado(1);
        System.out.println("\nO pedido " + pedido.getNumPedido() + " foi faturado com sucesso!");
        return daoFatura.incluir(fatura);
    }

    public Fatura remocao(int numfatura) throws ItemAindaAssociado, EntidadeInexistente {
        Fatura fatura = fatura_num(numfatura);
        if(fatura.getCancel() == 1){
            System.out.println('\n' + "A fatura " + fatura.getNumFatura() + " ja foi cancelada e nao pode ser removida.");
            return fatura;
        }
        if (fatura.getItensFaturados().isEmpty()){
            fatura.getCliente().getFaturas().remove(fatura);
            daoFatura.remover(numfatura);
            System.out.println("A fatura " + numfatura + " acaba de ser removida com sucesso");
        }
        else{
            throw new ItemAindaAssociado("Essa Fatura não pode ser removida!");
        }
        return fatura;
    }

    public Fatura fatura_num(int numfatura) throws EntidadeInexistente {
        Fatura fatura = daoFatura.recuperarPorId(numfatura);
        if (fatura == null){
            throw new EntidadeInexistente("Fatura Inexistente!");
        }
        return fatura;
    }

    public List<Fatura> recuperarFaturas() {
        return daoFatura.recuperarTodos();
    }

    public List<Fatura> recuperarFaturasPorPedido(int id) {
        return daoFatura.listarFaturas(id);
    }

    public void cancelar(int idFatura) throws EntidadeInexistente, DataInvalida {
        Fatura xFatura = fatura_num(idFatura);
        Cliente cliente = xFatura.getCliente();
        if(cliente.getFaturas().size() < 3) {
            System.out.println('\n' + "O cliente nao pode cancelar a fatura " + fatura_num(idFatura).getNumFatura() + ", ja que nao possui faturas suficientes.");
            return;
        }
        if(xFatura.getCancel() == 1){
            System.out.println('\n' + "Esta fatura ja foi cancelada!");
            return;
        }
        String dataCancelamento = Console.readLine('\n' + "Digite a data de fatura do cliente seguindo o modelo: DD/MM/YYYY: ");
        xFatura.setDataCancelamento(dataCancelamento);
        List<ItemFaturado> itensFaturados = xFatura.getItensFaturados();
        if(itensFaturados.isEmpty()){
            xFatura.setCancel(1);
            System.out.println('\n' + "A fatura " + fatura_num(idFatura).getNumFatura() + " acaba de ser cancelada com sucesso!" );
            return;
        }
        for(ItemFaturado itemFaturado : itensFaturados)
            itemFaturadoService.remocao(itemFaturado.getId());
        xFatura.setCancel(1);
        System.out.println('\n' + "A fatura " + fatura_num(idFatura).getNumFatura() + " acaba de ser cancelada com sucesso!");
    }

    public void cancelarDefinido(int idFatura, String dataCancelamento) throws EntidadeInexistente, DataInvalida {
        Fatura xFatura = fatura_num(idFatura);
        Cliente cliente = xFatura.getCliente();
        if(cliente.getFaturas().size() < 3) {
            System.out.println('\n' + "O cliente nao pode cancelar a fatura " + fatura_num(idFatura).getNumFatura() + ", ja que nao possui faturas suficientes.");
            return;
        }
        if(xFatura.getCancel() == 1){
            System.out.println('\n' + "Esta fatura ja foi cancelada!");
            return;
        }
        xFatura.setDataCancelamento(dataCancelamento);
        List<ItemFaturado> itensFaturados = xFatura.getItensFaturados();
        if(itensFaturados.isEmpty()){
            xFatura.setCancel(1);
            System.out.println('\n' + "A fatura " + fatura_num(idFatura).getNumFatura() + " acaba de ser cancelada com sucesso!" );
            return;
        }
        List<Integer> listaDeIDS = new ArrayList<>();
        for(ItemFaturado itemFaturado : itensFaturados)
            listaDeIDS.add(itemFaturado.getId());
        for(Integer id : listaDeIDS){
            itemFaturadoService.remocao(id);
        }
        xFatura.setCancel(1);
        System.out.println('\n' + "A fatura " + fatura_num(idFatura).getNumFatura() + " acaba de ser cancelada com sucesso!");
    }
}