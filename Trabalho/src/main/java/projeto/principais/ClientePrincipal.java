package projeto.principais;

import corejava.Console;
import projeto.exception.DataInvalida;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.exception.QtdeInvalida;
import projeto.model.*;
import projeto.service.*;

import java.util.ArrayList;
import java.util.List;

public class ClientePrincipal {
    public ClienteService clienteService = new ClienteService();
    public PedidoService pedidoService = new PedidoService();
    public FaturaService faturaService = new FaturaService();
    public ItemPedidoService itemPedidoService = new ItemPedidoService();
    public ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
    public void principal() throws DataInvalida {
        boolean continua = true;
        String nome, endereco, cpf, email, telefone;
        Cliente xCliente;
        ItemPedido itemPedido;
        Fatura xFatura = null;
        while(continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um cliente");
            System.out.println('\n' + "2. Alterar um cliente");
            System.out.println('\n' + "3. Remover um cliente");
            System.out.println('\n' + "4. Tratar faturas");
            System.out.println('\n' + "5. Listar todos clientes");
            System.out.println('\n' + "6. Voltar");
            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");
            switch(opcao){
                // Cadastra cliente
                case 1 -> {
                    nome = Console.readLine("Digite o nome: ");
                    endereco = Console.readLine("Digite o endereço: ");
                    cpf = Console.readLine("Digite o cpf: ");
                    email = Console.readLine("Digite o email: ");
                    telefone = Console.readLine("Digite o telefone: ");
                    xCliente = new Cliente(nome, cpf, telefone, email, endereco);
                    clienteService.inclusao(xCliente);
                    System.out.println("O cliente " + nome + " foi cadastrado com sucesso!");
                }
                // Altera cliente
                case 2 -> {
                    int id = Console.readInt("Digite o id do cliente a ser alterado: ");
                    try{
                        xCliente = clienteService.cliente_id(id);
                    } catch (EntidadeInexistente e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1. Nome");
                    System.out.println('\n' + "2. CPF");
                    System.out.println('\n' + "3. Endereço");
                    System.out.println('\n' + "4. Telefone");
                    System.out.println('\n' + "5. Email");
                    int x = Console.readInt('\n' + "Digite um numero entre 1 e 5: ");
                    switch(x){
                        // Altera Nome
                        case 1 -> {
                            String nomenovo = Console.readLine('\n' + "Digite o novo nome: ");
                            clienteService.alterar_nome(xCliente, nomenovo);
                            System.out.println('\n' + "Nome alterado com sucesso!");
                        }
                        // Altera CPF
                        case 2 -> {
                            String cpfnovo = Console.readLine('\n' + "Digite o novo cpf: ");
                            clienteService.alterar_cpf(xCliente, cpfnovo);
                            System.out.println('\n' + "CPF alterado com sucesso!");
                        }
                        // Altera endereço
                        case 3 -> {
                            String endnovo = Console.readLine('\n' + "Digite o novo endereco: ");
                            clienteService.alterar_endereco(xCliente, endnovo);
                            System.out.println('\n' + "Endereco alterado com sucesso!");
                        }
                        // Altera telefone
                        case 4 -> {
                            String telnovo = Console.readLine('\n' + "Digite o novo telefone do cliente: ");
                            clienteService.alterar_telefone(xCliente, telnovo);
                            System.out.println('\n' + "Telefone alterado com sucesso!");
                        }
                        // Altera email
                        case 5 -> {
                            String emailnovo = Console.readLine('\n' + "Digite o novo email: ");
                            clienteService.alterar_email(xCliente, emailnovo);
                            System.out.println('\n' + "Email alterado com sucesso!");
                        }
                        default -> {
                            System.out.println('\n' + "Opção inválida!");
                        }
                    }
                }
                // Remove cliente
                case 3 -> {
                    int id = Console.readInt("Digite o id do cliente a ser removido: ");
                    try{
                        xCliente = clienteService.remover(id);
                        System.out.println('\n' + "O cliente acaba de ser removido com sucesso");
                    } catch (EntidadeInexistente | ItemAindaAssociado e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                // Tratando Faturas
                case 4 -> {
                    int id = Console.readInt('\n' + "Digite o id do cliente para tratar faturas: ");
                    try{
                        xCliente = clienteService.cliente_id(id);
                    } catch (EntidadeInexistente e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    List<Pedido> pedidos = xCliente.getPedidos();
                    if(pedidos.isEmpty()){
                        System.out.println('\n' + "Este cliente ainda não tem pedidos.");
                        break;
                    }
                    System.out.println('\n' + "O que deseja tratar nas faturas?");
                    System.out.println('\n' + "1. Emitir Fatura");
                    System.out.println('\n' + "2. Remover Fatura");
                    System.out.println('\n' + "3. Cancelar Fatura");
                    System.out.println('\n' + "4. Listar Faturas");
                    int opcaofatura = Console.readInt('\n' + "Digite um numero entre 1 e 4: ");
                    switch(opcaofatura){
                        // Emissão de Faturas
                        case 1 -> {
                            String dataFatura = Console.readLine('\n' + "Digite a data de fatura a ser emitida no modelo DD/MM/YYYY: ");
                            try{
                                xFatura = new Fatura(dataFatura, xCliente);
                            } catch(DataInvalida e){
                                System.out.println('\n' + e.getMessage());
                            }
                            System.out.println('\n' + "Lista de pedidos do cliente " + xCliente.getNome());
                            for(Pedido pedido : pedidos){
                                pedido.listarPedidoComItens();
                                System.out.println('\n');
                            }
                            id = Console.readInt('\n' + "Digite o número do pedido a ser faturado: ");
                            int itensFaturados = Console.readInt('\n' + "Digite quantos itens serão faturados (min 1): ");
                            if(itensFaturados < 1) throw new QtdeInvalida('\n' + "Deve ter ao menos um item faturado.");
                            for(int i = 0; i < itensFaturados; i++) {
                                int idItem = Console.readInt('\n' + "Digite id do item pedido a ser faturado");
                                try {
                                    itemPedido = itemPedidoService.ip_id(idItem);
                                } catch (EntidadeInexistente e) {
                                    System.out.println('\n' + e.getMessage());
                                    break;
                                }
                                int qtdFaturada = Console.readInt('\n' + "Digite a quantidade deste item a ser faturada: ");
                                ItemFaturado umItemFaturado = new ItemFaturado(qtdFaturada, itemPedido, xFatura);
                                try{
                                    itemFaturadoService.inclusao(umItemFaturado);
                                } catch (QtdeInvalida e){
                                    System.out.println('\n' + e.getMessage());
                                    break;
                                }
                            }
                            xFatura.setValorTotal();
                            xFatura.setValorTotalDesconto();
                            faturaService.inclusao(dataFatura,pedidoService.pedido_id(id));
                            System.out.println('\n' + "Sua fatura acaba de ser emitida com sucesso!");
                        }
                        // Remover fatura
                        case 2 ->{
                            List<Fatura> faturas = xCliente.getFaturas();
                            if(faturas.isEmpty()){
                                System.out.println('\n' + "Cliente nao possui faturas");
                                break;
                            }
                            System.out.println('\n' + "Lista de faturas do cliente " + xCliente.getNome());
                            for(Fatura fatura : faturas){
                                System.out.println('\n' + fatura.toString());
                            }
                            int idFatura = Console.readInt('\n' + "Digite o numero da fatura a ser removida:");
                            try{
                                xFatura = faturaService.fatura_num(idFatura);
                            } catch (EntidadeInexistente e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            if(xFatura.getCancel() == 1){
                                System.out.println('\n' + "Essa fatura nao pode ser removida ja que esta cancelada.");
                                break;
                            }
                            System.out.println('\n' + "Deseja remover um item faturado ou toda a fatura?");
                            int opcaoCancelamento = Console.readInt('\n' + "1 para fatura toda, 2 para um item faturado");
                            switch(opcaoCancelamento){
                                case 1 -> {
                                    List<ItemFaturado> itemFaturados = xFatura.getItensFaturados();
                                    if(itemFaturados.isEmpty()){
                                        try{
                                            faturaService.remocao(xFatura.getNumFatura());
                                        } catch (EntidadeInexistente | ItemAindaAssociado e){
                                            System.out.println('\n' + e.getMessage());
                                        }
                                        System.out.println('\n' + "Sua fatura acaba de ser removida com sucesso!");
                                        break;
                                    }
                                    List<Integer> listaDeIds = new ArrayList<Integer>();
                                    for(ItemFaturado itemFaturado : itemFaturados){
                                        listaDeIds.add(itemFaturado.getId());
                                    }
                                    try{
                                        for(Integer x : listaDeIds){
                                            itemFaturadoService.remocao(x);
                                        }
                                    } catch (EntidadeInexistente e) {
                                        System.out.println('\n' + e.getMessage());
                                    }
                                    try{
                                        faturaService.remocao(xFatura.getNumFatura());
                                    } catch (EntidadeInexistente | ItemAindaAssociado e) {
                                        System.out.println('\n' + e.getMessage());
                                    }
                                    System.out.println('\n' + "Sua fatura foi removida com sucesso!");
                                }
                                case 2 -> {
                                    List<ItemFaturado> itensFaturados = xFatura.getItensFaturados();
                                    if(itensFaturados.isEmpty()){
                                        System.out.println('\n' + "Fatura nao possui itens faturados");
                                        break;
                                    }
                                    System.out.println('\n' + "Itens faturados:");
                                    for(ItemFaturado itemFaturado : itensFaturados){
                                        System.out.println('\n' + itemFaturado.toString());
                                    }
                                    int idItemFatura = Console.readInt('\n' + "Digite o id do item faturado que deseja remover:");
                                    try{
                                        itemFaturadoService.remocao(idItemFatura);
                                    } catch (EntidadeInexistente e) {
                                        System.out.println('\n' + e.getMessage());
                                    }
                                    System.out.println('\n' + "Item faturado acaba de ser removido com sucesso!");
                                }
                                default -> {
                                    System.out.println('\n' + "Opção inválida!");
                                }
                            }
                        }
                        // Cancelar Fatura
                        case 3->{
                            List<Fatura> faturas = xCliente.getFaturas();
                            if(faturas.size() < 3){
                                System.out.println('\n' + "Esse cliente nao possui o numero minimo de faturas para cancelamento");
                                break;
                            }
                            System.out.println('\n' + "Lista de faturas do cliente " + xCliente.getNome());
                            for(Fatura fatura : faturas){
                                System.out.println('\n' + fatura.toString());
                            }
                            int idFatura = Console.readInt('\n' + "Digite o numero da fatura a ser cancelada:");
                            try{
                                xFatura = faturaService.fatura_num(idFatura);
                            } catch (EntidadeInexistente e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            if(xFatura.getCancel() == 1){
                                System.out.println('\n' + "Fatura ja cancelada");
                                break;
                            }
                            String dataCancelamento = Console.readLine('\n' + "Digite a data de cancelamento do cliente no modelo DD/MM/YYYY: ");
                            try{
                                xFatura.setDataCancelamento(dataCancelamento);
                            } catch (DataInvalida e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            List<ItemFaturado> itensFaturados = xFatura.getItensFaturados();
                            if(itensFaturados.isEmpty()){
                                xFatura.setCancel(1);
                                System.out.println('\n' + "Fatura cancelada com sucesso!" );
                                break;
                            }
                            List<Integer> listaDeIds = new ArrayList<Integer>();
                            for(ItemFaturado itemFaturado : itensFaturados){
                                listaDeIds.add(itemFaturado.getId());
                            }
                            try{
                                for(Integer x : listaDeIds){
                                    itemFaturadoService.remocao(x);
                                }
                            } catch (EntidadeInexistente e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            xFatura.setCancel(1);
                            System.out.println('\n' + "A fatura foi cancelada com sucesso!");
                        }
                        // Listando Faturas
                        case 4->{
                            List<Fatura> faturas = xCliente.getFaturas();
                            if(faturas.isEmpty()){
                                System.out.println('\n' + "O cliente " + xCliente.getNome() + " nao possui faturas");
                                break;
                            }
                            System.out.println('\n' + "Faturas do cliente " + xCliente.getNome());
                            for(Fatura fatura : faturas){
                                System.out.println('\n' + fatura.toString());
                                System.out.println('\n' + "Itens faturados da fatura " + fatura.getNumFatura());
                                List<ItemFaturado> itensFaturados = fatura.getItensFaturados();
                                for(ItemFaturado itemFaturado : itensFaturados){
                                    System.out.println('\n' + itemFaturado.toString());
                                }
                            }
                        }
                        default -> {
                            System.out.println('\n' + "Opção inválida!");
                        }
                    }
                }
                case 5->{
                    System.out.println('\n' + "Listar todos os clientes" + '\n' + "Listar os pedidos de um certo cliente" + '\n' + "Listar as faturas de um certo cliente");
                    int opcaoLista = Console.readInt('\n' + "1 para todos clientes, 2 para pedidos, 3 para faturas");
                    switch(opcaoLista){
                        case 1->{
                            List<Cliente> clientes = clienteService.listar();
                            if(clientes.isEmpty()){
                                System.out.println('\n' + "Nao existem clientes no sistema");
                                break;
                            }
                            for(Cliente cliente : clientes){
                                System.out.println('\n' + cliente.toString());
                            }
                        }
                        case 2-> {
                            int idCliente = Console.readInt('\n' + "Digite id do cliente que deseja listar seus pedidos: ");
                            try {
                                Cliente clienteLista = clienteService.cliente_id(idCliente);
                                List<Pedido> pedidos = pedidoService.recuperarPedidosPorCliente(idCliente);
                                if (pedidos.isEmpty()) {
                                    System.out.println('\n' + "O cliente " + clienteLista.getNome() + " nao possui pedidos");
                                    break;
                                }
                                for (Pedido pedido : pedidos) {
                                    System.out.println('\n' + pedido.toString());
                                }
                            } catch (EntidadeInexistente e) {
                                System.out.println('\n' + e.getMessage());
                            }
                        }
                        case 3->{
                            int idCliente = Console.readInt('\n' + "Digite id do cliente que deseja listar suas faturas: ");
                            try{
                                Cliente clienteFatura = clienteService.cliente_id(idCliente);
                                List<Fatura> faturas = clienteFatura.getFaturas();
                                if(faturas.isEmpty()){
                                    System.out.println('\n' + "O cliente " + clienteFatura.getNome() + " nao possui faturas");
                                    break;
                                }
                                for(Fatura fatura : faturas){
                                    System.out.println('\n' + fatura.toString());
                                }
                            } catch (EntidadeInexistente e) {
                                System.out.println('\n' + e.getMessage());
                            }
                        }
                        default -> {
                            System.out.println('\n' + "Opção inválida!");
                        }
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