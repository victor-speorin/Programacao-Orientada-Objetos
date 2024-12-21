package projeto;

import corejava.Console;

import java.io.*;
import java.util.Map;

import projeto.dao.DaoCliente;
import projeto.dao.DaoPedido;
import projeto.dao.DaoFatura;
import projeto.dao.DaoLivro;
import projeto.model.Fatura;
import projeto.model.Pedido;
import projeto.model.Livro;
import projeto.model.Cliente;
import projeto.principais.ClientePrincipal;
import projeto.principais.FaturaPrincipal;
import projeto.principais.LivroPrincipal;
import projeto.principais.PedidoPrincipal;


public class Main {
    public static void main(String[] args) {

        ClientePrincipal clientePrincipal = new ClientePrincipal();
        FaturaPrincipal faturaPrincipal = new FaturaPrincipal();
        LivroPrincipal livroPrincipal = new LivroPrincipal();
        PedidoPrincipal pedidoPrincipal = new PedidoPrincipal();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Cliente");
            System.out.println("2. Tratar Faturas");
            System.out.println("3. Tratar Livros");
            System.out.println("4. Tratar Pedidos");
            System.out.println("5. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 3:");

            System.out.println();

            switch (opcao) {
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                    continua = false;
                }
                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
