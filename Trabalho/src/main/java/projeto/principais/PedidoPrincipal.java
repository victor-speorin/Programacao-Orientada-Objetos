package projeto.principais;

import corejava.Console;
import projeto.exception.DataInvalida;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.model.*;
import projeto.service.*;
import projeto.util.Email;

import java.util.List;

public class PedidoPrincipal {
    PedidoService pedidoService = new PedidoService();
    ItemPedidoService itemPedidoService = new ItemPedidoService();
    ClienteService clienteService = new ClienteService();
    LivroService livroService = new LivroService();
    ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
    FaturaService faturaService = new FaturaService();
    public void principal(){
        Pedido xPedido;
        boolean continua = true;
        Cliente xCliente;
        Livro xLivro;
        ItemPedido umItemPedido;
        while(continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um Pedido");
            System.out.println('\n' + "2. Remover um Pedido");
            System.out.println('\n' + "3. Cancelar um Pedido");
            System.out.println('\n' + "4. Listar todos Pedidos");
            System.out.println('\n' + "5. Listar todos Pedidos com seus itens");
            System.out.println('\n' + "6. Voltar");
            int opcao = Console.readInt('\n' + "Digite um numero de 1 a 6");
            switch (opcao){
                case 1->{
                    int idCliente = Console.readInt('\n' + "Digite o id do cliente que vai fazer o pedido: ");
                    try{
                        xCliente = clienteService.cliente_id(idCliente);
                        String dataPedido = Console.readLine('\n' + "Digite a data do pedido no modelo DD/MM/YYYY: ");
                        String enderecoEntrega = Console.readLine('\n' + "Digite o endereco de entrega:");
                        xPedido = new Pedido(dataPedido,xCliente,enderecoEntrega);
                        System.out.println('\n' + "Livros disponíveis: ");
                        List<Livro> livros = livroService.recuperarLivros();
                        for(Livro livro : livros){
                            System.out.println('\n' + livro.livroEQuantidade());
                        }
                        int itens = Console.readInt('\n' + "Digite a quantidade de itens que serão pedidos (min 1): ");
                        if(itens < 1){
                            System.out.println('\n' + "Quantidade de itens invalida.");
                            break;
                        }
                        for(int i = 0; i < itens; i++){
                            int idLivro = Console.readInt('\n' + "Digite o id do livro a ser pedido: ");
                            xLivro = livroService.livro_id(idLivro);
                            int qtdPedida = Console.readInt('\n' + "Digite a quantidade que sera pedida desse livro");
                            umItemPedido = new ItemPedido(qtdPedida, xLivro, xPedido);
                            itemPedidoService.inclusao(umItemPedido);
                        }
                        pedidoService.inclusao(xPedido);
                        Email umEmail = new Email();
                        umEmail.setCliente(xCliente);
                        umEmail.start();
                        umEmail.join();
                    } catch (EntidadeInexistente | DataInvalida | InterruptedException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2->{
                    int idPedido = Console.readInt('\n' + "Digite o id do pedido a ser removido: ");
                    try{
                        xPedido = pedidoService.pedido_id(idPedido);
                        int opcaoRemocao = Console.readInt('\n' + "Digite 1 para remover um pedido e 2 para remover um item pedido.");
                        switch (opcaoRemocao){
                            case 1->{
                                pedidoService.remover(idPedido);
                                System.out.println('\n' + "Seu pedido foi removido com sucesso!");
                            }
                            case 2->{
                                List<ItemPedido> itensPedidos = xPedido.getItensPedidos();
                                if(itensPedidos.isEmpty()){
                                    System.out.println('\n' + "Sem itens para remocao");
                                    break;
                                }
                                System.out.println('\n' + "Lista de itens: ");
                                for(ItemPedido itemPedido : itensPedidos){
                                    System.out.println('\n' + itemPedido.listarItemPedido());
                                }
                                int idItemPedido = Console.readInt('\n' + "Digite o id do item pedido a ser removido: ");
                                umItemPedido = itemPedidoService.remocao(idItemPedido);
                            }
                            default -> {
                                System.out.println('\n' + "Opção inválida!");
                            }
                        }
                    } catch (EntidadeInexistente | ItemAindaAssociado e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3->{
                    int idPedido = Console.readInt('\n' + "Digite o id do pedido a ser cancelado: ");
                    try {
                        pedidoService.remover(idPedido);
                    } catch (EntidadeInexistente | ItemAindaAssociado e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4->{
                    List<Pedido> pedidos = pedidoService.recuperarPedidos();
                    if(pedidos.isEmpty()){
                        System.out.println('\n' + "Sem pedidos");
                        break;
                    }
                    for(Pedido pedido : pedidos){
                        System.out.println('\n' + pedido.toString());
                    }
                }
                case 5->{
                    List<Pedido> pedidos = pedidoService.recuperarPedidos();
                    if(pedidos.isEmpty()){
                        System.out.println('\n' + "Sem pedidos");
                        break;
                    }
                    for(Pedido pedido : pedidos){
                        pedido.listarPedidoComItens();
                    }
                }
                case 6-> continua = false;
                default -> {
                    System.out.println('\n' + "Opção inválida!");
                }
            }
        }
    }
}