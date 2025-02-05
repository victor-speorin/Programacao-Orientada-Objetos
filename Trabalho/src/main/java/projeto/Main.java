package projeto;

import corejava.Console;
import projeto.principais.ClientePrincipal;
import projeto.principais.LivroPrincipal;
import projeto.principais.PedidoPrincipal;
import projeto.dao.*;
import projeto.exception.ItemAindaAssociado;
import projeto.exception.DataInvalida;
import projeto.exception.EntidadeInexistente;
import projeto.model.*;
import projeto.service.*;
import projeto.util.Email;
import projeto.util.FabricaDeDaos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws DataInvalida {
        //recuperarDados();
        ClientePrincipal principalCliente = new ClientePrincipal();
        LivroPrincipal principalLivro = new LivroPrincipal();
        PedidoPrincipal principalPedido = new PedidoPrincipal();
        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Clientes");
            System.out.println('\n' + "2. Tratar Livros");
            System.out.println('\n' + "3. Tratar Pedidos");
            System.out.println('\n' + "4. Testar Sistema");
            System.out.println('\n' + "5. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    principalCliente.principal();
                }
                case 2 -> {
                    principalLivro.principal();
                }
                case 3 -> {
                    principalPedido.principal();
                }
                case 4->{
                    testarSistema();
                }
                case 5 -> {
                    salvarDados();
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }

    private static void salvarDados() {
        DaoCliente clienteDao = FabricaDeDaos.getDAO(DaoCliente.class);
        DaoFatura faturaDao = FabricaDeDaos.getDAO(DaoFatura.class);
        DaoItemFaturado itemFaturadoDao = FabricaDeDaos.getDAO(DaoItemFaturado.class);
        DaoItemPedido itemPedidoDao = FabricaDeDaos.getDAO(DaoItemPedido.class);
        DaoLivro livroDao = FabricaDeDaos.getDAO(projeto.dao.DaoLivro.class);
        DaoPedido pedidoDao = FabricaDeDaos.getDAO(DaoPedido.class);

        Map<Integer, Cliente> mapDeClientes = clienteDao.getMap();
        int contadorClientes = clienteDao.getContador();

        Map<Integer, Fatura> mapDeFaturas = faturaDao.getMap();
        int contadorFaturas = faturaDao.getContador();

        Map<Integer, ItemFaturado> mapDeItemFaturado = itemFaturadoDao.getMap();
        int contadorItemFaturado = itemFaturadoDao.getContador();

        Map<Integer, ItemPedido> mapDeItemPedido = itemPedidoDao.getMap();
        int contadorItemPedido = itemPedidoDao.getContador();

        Map<Integer, Livro> mapDeLivros = livroDao.getMap();
        int contadorLivros = livroDao.getContador();

        Map<Integer, Pedido> mapDePedidos = pedidoDao.getMap();
        int contadorPedidos = pedidoDao.getContador();

        try {
            FileOutputStream fos = new FileOutputStream("arq.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapDeClientes);
            oos.writeInt(contadorClientes);
            oos.writeObject(mapDeFaturas);
            oos.writeInt(contadorFaturas);
            oos.writeObject(mapDeItemFaturado);
            oos.writeInt(contadorItemFaturado);
            oos.writeObject(mapDeItemPedido);
            oos.writeInt(contadorItemPedido);
            oos.writeObject(mapDeLivros);
            oos.writeInt(contadorLivros);
            oos.writeObject(mapDePedidos);
            oos.writeInt(contadorPedidos);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recuperarDados() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("arq.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<Integer, Cliente> mapDeClientes = (Map<Integer, Cliente>) ois.readObject();
            int contadorClientes = ois.readInt();

            Map<Integer, Fatura> mapDeFaturas = (Map<Integer, Fatura>) ois.readObject();
            int contadorFaturas = ois.readInt();

            Map<Integer, ItemFaturado> mapDeItemFaturado = (Map<Integer, ItemFaturado>) ois.readObject();
            int contadorItemFaturado = ois.readInt();

            Map<Integer, ItemPedido> mapDeItemPedido = (Map<Integer, ItemPedido>) ois.readObject();
            int contadorItemPedido = ois.readInt();

            Map<Integer, Livro> mapDeLivros = (Map<Integer, Livro>) ois.readObject();
            int contadorLivros = ois.readInt();

            Map<Integer, Pedido> mapDePedidos = (Map<Integer, Pedido>) ois.readObject();
            int contadorPedidos = ois.readInt();

            DaoCliente clienteDao = FabricaDeDaos.getDAO(DaoCliente.class);
            DaoFatura faturaDao = FabricaDeDaos.getDAO(DaoFatura.class);
            DaoItemFaturado itemFaturadoDao = FabricaDeDaos.getDAO(DaoItemFaturado.class);
            DaoItemPedido itemPedidoDao = FabricaDeDaos.getDAO(DaoItemPedido.class);
            DaoLivro livroDao = FabricaDeDaos.getDAO(DaoLivro.class);
            DaoPedido pedidoDao = FabricaDeDaos.getDAO(DaoPedido.class);
            clienteDao.setMap(mapDeClientes);
            clienteDao.setContador(contadorClientes);
            faturaDao.setMap(mapDeFaturas);
            faturaDao.setContador(contadorFaturas);
            itemFaturadoDao.setMap(mapDeItemFaturado);
            itemFaturadoDao.setContador(contadorItemFaturado);
            itemPedidoDao.setMap(mapDeItemPedido);
            itemPedidoDao.setContador(contadorItemPedido);
            livroDao.setMap(mapDeLivros);
            livroDao.setContador(contadorLivros);
            pedidoDao.setMap(mapDePedidos);
            pedidoDao.setContador(contadorPedidos);
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("o arquivo não existe e será criado.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testarSistema(){
        LivroService livroService = new LivroService();
        ClienteService clienteService = new ClienteService();
        ItemPedidoService itemPedidoService = new ItemPedidoService();
        PedidoService pedidoService = new PedidoService();
        ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
        FaturaService faturaService = new FaturaService();
        //Cadastrando livros
        Livro livro_1 = new Livro ("10", "Aaa", "Aaaa", 100, 10);
        Livro livro_2 = new Livro ("20", "Bbb", "Bbbb", 200, 20);
        Livro livro_3 = new Livro ("30", "Ccc", "Cccc", 300, 30);
        Livro livro_4 = new Livro ("40", "Ddd", "Dddd", 400, 40);
        Livro livro_5 = new Livro ("50", "Eee", "Eeee", 500, 50);
        livroService.inclusao(livro_1);
        livroService.inclusao(livro_2);
        livroService.inclusao(livro_3);
        livroService.inclusao(livro_4);
        livroService.inclusao(livro_5);
        List<Livro> livros = livroService.recuperarLivros();
        System.out.println('\n' + "Lista de livros:");
        for(Livro livro : livros){
            System.out.println('\n' + livro.toString());
        }

        //Cadastrando clientes
        Cliente cliente_1 = new Cliente("Xxxx", "111", "11111111", "xxxx@gmail.com", "");
        Cliente cliente_2 = new Cliente("Yyyy", "222", "22222222", "yyyy@gmail.com", "");
        clienteService.inclusao(cliente_1);
        clienteService.inclusao(cliente_2);

        System.out.println("==========================================================\n\n");
        //Cadastrando pedidos
        try{
            Pedido pedido_1 = new Pedido("01/01/2025", cliente_1, "");
            ItemPedido itemPedido_1 = new ItemPedido(5,livro_1, pedido_1);
            ItemPedido itemPedido_2 = new ItemPedido(15,livro_2, pedido_1);
            itemPedidoService.inclusao(itemPedido_1);
            itemPedidoService.inclusao(itemPedido_2);
            pedidoService.inclusao(pedido_1);
            Email emailum = new Email();
            emailum.setCliente(cliente_1);
            emailum.start();
            emailum.join();

            Pedido pedido_2 = new Pedido("02/01/2025", cliente_1, "");
            ItemPedido itemPedido_3 = new ItemPedido(10,livro_1, pedido_2);
            ItemPedido itemPedido_4 = new ItemPedido(40, livro_3, pedido_2);
            itemPedidoService.inclusao(itemPedido_3);
            itemPedidoService.inclusao(itemPedido_4);
            pedidoService.inclusao(pedido_2);
            Email emaildois = new Email();
            emaildois.setCliente(cliente_1);
            emaildois.start();
            emaildois.join();

            Pedido pedido_3 = new Pedido("03/01/2025", cliente_1, "");
            ItemPedido itemPedido_5 = new ItemPedido(5,livro_1, pedido_3);
            ItemPedido itemPedido_6 = new ItemPedido(10,livro_3, pedido_3);
            itemPedidoService.inclusao(itemPedido_5);
            itemPedidoService.inclusao(itemPedido_6);
            pedidoService.inclusao(pedido_3);
            Email emaitres = new Email();
            emaitres.setCliente(cliente_1);
            emaitres.start();
            emaitres.join();

            Pedido pedido_4 = new Pedido("04/01/2025", cliente_1, "");
            ItemPedido itemPedido_7 = new ItemPedido(10,livro_2, pedido_4);
            ItemPedido itemPedido_8 = new ItemPedido(10,livro_3, pedido_4);
            ItemPedido itemPedido_9 = new ItemPedido(10, livro_4, pedido_4);
            itemPedidoService.inclusao(itemPedido_7);
            itemPedidoService.inclusao(itemPedido_8);
            itemPedidoService.inclusao(itemPedido_9);
            pedidoService.inclusao(pedido_4);
            Email emailquatro = new Email();
            emailquatro.setCliente(cliente_1);
            emailquatro.start();
            emailquatro.join();

            Pedido pedido_5 = new Pedido("05/01/2025", cliente_1, "");
            ItemPedido itemPedido_10 = new ItemPedido(5,livro_2, pedido_5);
            ItemPedido itemPedido_11 = new ItemPedido(5,livro_3, pedido_5);
            ItemPedido itemPedido_12 = new ItemPedido(5, livro_4, pedido_5);
            itemPedidoService.inclusao(itemPedido_10);
            itemPedidoService.inclusao(itemPedido_11);
            itemPedidoService.inclusao(itemPedido_12);
            pedidoService.inclusao(pedido_5);
            Email emailcin = new Email();
            emailcin.setCliente(cliente_1);
            emailcin.start();
            emailcin.join();

            List<Pedido> pedidos = pedidoService.recuperarPedidos();
            System.out.println("\n==========================================================\n\n" + "Lista de pedidos:");
            for(Pedido pedido : pedidos){
                pedido.listarPedidoComItens();
            }

            //Listando livros novamente
            System.out.println("==========================================================\n\n" + "Lista de livros:");
            for(Livro livro : livros) System.out.println('\n' + livro.toString());

            System.out.println("==========================================================\n\n");
            //Faturando faturas 1 e 2
            faturaService.inclusao("10/01/2025",pedido_1);
            faturaService.inclusao("10/01/2025",pedido_2);
            System.out.println();


            //Tentando cancelar fatura_2
            System.out.println("==========================================================\n\n8)");
            faturaService.cancelar(2);

            System.out.println("\n9)");
            //Faturando fatura 3 e 4
            faturaService.inclusao("20/01/2025", pedido_3);
            System.out.println();
            faturaService.inclusao("20/01/2025", pedido_4);
            System.out.println();

            //Faturando fatura 5
            faturaService.inclusao("28/02/2025", pedido_5);
            System.out.println();

            //Lista de livros
            System.out.println("==========================================================\n" + "Lista de livros:");
            for(Livro livro: livros){
                System.out.println('\n' + livro.toString());
            }

            List<Fatura> faturas = faturaService.recuperarFaturas();
            System.out.println("==========================================================\n\n" + "Lista de faturas:");
            for(Fatura fatura : faturas){
                System.out.println('\n' + fatura.toString());
                System.out.println("Itens faturados da fatura:");
                for(ItemFaturado itemFaturado : fatura.getItensFaturados()){
                    System.out.println('\n' + "Livro " + itemFaturado.getPedido().getLivro().getTitulo() + " - " + itemFaturado.getQtdFaturada());
                }
                System.out.println("\n");
            }

            //Tentando cancelar pedido 5
            System.out.println("==========================================================\n\n13)");
            pedidoService.cancelarDefinido(pedido_5, "28/02/2025");

            //Tentando cancelar fatura 3
            System.out.println("\n14)");
            faturaService.cancelarDefinido(3, "06/01/2025");

            //Tentando remover fatura 3
            System.out.println("\n15)");
            faturaService.remocao(3);

            //Tentando remover fatura 4
            System.out.println("\n16)");
            Fatura umaFatura = faturaService.fatura_num(4);
            List<Integer> listaDeIds = new ArrayList<Integer>();
            List<ItemFaturado> itemFaturados = umaFatura.getItensFaturados();
            for(ItemFaturado itemFaturado : itemFaturados){
                listaDeIds.add(itemFaturado.getId());
            }
            for(Integer x : listaDeIds){
                itemFaturadoService.remocao(x);
            }
            faturaService.remocao(4);
            System.out.println("\n");

            //Listando livros
            System.out.println("==========================================================\n\n" + "Lista de livros:\n");
            for(Livro livro: livros){
                System.out.println(livro.toString());
            }

            //Abastecendo estoques
            livro_1.setQtdEstoque(livro_1.getQtdEstoque() + 100);
            livro_2.setQtdEstoque(livro_2.getQtdEstoque() + 200);
            livro_3.setQtdEstoque(livro_3.getQtdEstoque() + 300);
            livro_4.setQtdEstoque(livro_4.getQtdEstoque() + 400);
            livro_5.setQtdEstoque(livro_5.getQtdEstoque() + 500);

            System.out.println("==========================================================\n\n" + "DEPOIS DO ABASTECIMENTO DE ESTOQUES: ");
            //Listando livros
            for(Livro livro: livros){
                System.out.println('\n' + livro.toString());
            }

            //Faturando faturas 1 a 5
            faturaService.inclusao("01/02/2025", pedido_1);
            faturaService.inclusao("02/02/2025", pedido_2);
            faturaService.inclusao("03/02/2025", pedido_3);
            faturaService.inclusao("04/02/2025", pedido_4);
            faturaService.inclusao("05/02/2025", pedido_5);


            //Relatorio 1
            System.out.println();
            System.out.println();
            System.out.println("==========================================================\n\n" + "RELATORIO 1:");
            List<ItemFaturado> itensLivro1 = livroService.recuperarItensFaturadosMesAno(livro_1.getId(), 01, 2025);
            System.out.println('\n' + "Lista de itens faturados do livro " + livro_1.getId() + " no mes " + 1 + " e ano " + 2025);
            for(ItemFaturado item : itensLivro1){
                System.out.println('\n' + item.toString());
            }

            //Relatorio 2
            System.out.println();
            System.out.println();
            System.out.println('\n' + "RELATORIO 2:");
            List<Livro> livrosNunca = livroService.recuperarTodosLivrosNuncaFaturados();
            List<Livro> livrosaux = livroService.recuperarLivros();
            if(livros.isEmpty()){
                System.out.println('\n' + "Sem livros cadastrados.");
            }
            else if(livrosNunca.isEmpty()){
                System.out.println('\n' + "Todos livros ja foram faturados");
            }
            else {
                System.out.println('\n' + "Lista de livros nunca faturados:");
                for (Livro livro : livrosNunca) {
                    System.out.println('\n' + livro.toString());
                }
            }

            //Relatorio 3
            System.out.println();
            System.out.println();
            System.out.println('\n' + "RELATORIO 3:");
            List<Livro> lista = livroService.recuperarLivrosFaturadosMesAno(02, 2025);
            if(lista.isEmpty()){
                System.out.println('\n' + "Sem livros faturados nesse mes e ano");
            } else {
                System.out.println('\n' + "Lista de livros faturados em fevereiro de 2025:");
                for (Livro livro : lista) {
                    List<ItemFaturado> itens = livroService.recuperarItensFaturadosMesAno(livro.getId(), 02, 2025);
                    int c = 0;
                    for (ItemFaturado item : itens) c += item.getQtdFaturada();
                    System.out.println('\n' + "O livro " + livro.getTitulo() + " foi faturado " + c + " vezes nesse período.");
                }
            }

            System.out.println("\n\n\nFIM DA EXECUCAO.");
        }catch (EntidadeInexistente | ItemAindaAssociado | DataInvalida | InterruptedException e){
            System.out.println('\n' + e.getMessage());
        }
    }

}

