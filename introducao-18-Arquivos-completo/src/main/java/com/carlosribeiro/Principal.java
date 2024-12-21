package com.carlosribeiro;

import com.carlosribeiro.dao.LanceDAO;
import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;

import java.io.*;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {

        PrincipalProduto principalProduto = new PrincipalProduto();
        PrincipalLance principalLance = new PrincipalLance();

        recuperarDados();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Produtos");
            System.out.println("2. Tratar Lances");
            System.out.println("3. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 3:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    principalProduto.principal();
                }
                case 2 -> {
                    principalLance.principal();
                }
                case 3 -> {
                    salvarDados();
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
    private static void salvarDados() {
        ProdutoDAO produtoDAO = FabricaDeDaos.getDAO(ProdutoDAO.class);
        LanceDAO lanceDAO = FabricaDeDaos.getDAO(LanceDAO.class);
        Map<Integer, Produto> mapDeProdutos = produtoDAO.getMap();
        int contadorProdutos = produtoDAO.getContador();
        Map<Integer, Lance> mapDeLances = lanceDAO.getMap();
        int contadorLances = lanceDAO.getContador();

        try {
            FileOutputStream fos = new FileOutputStream("arquivo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapDeProdutos);
            oos.writeInt(contadorProdutos);
            oos.writeObject(mapDeLances);
            oos.writeInt(contadorLances);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void recuperarDados() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("arquivo.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<Integer, Produto> mapDeProdutos = (Map<Integer, Produto>) ois.readObject();
            int contadorProdutos = ois.readInt();

            Map<Integer, Lance> mapDeLances = (Map<Integer, Lance>) ois.readObject();
            int contadorLances = ois.readInt();

            ProdutoDAO produtoDAO = FabricaDeDaos.getDAO(ProdutoDAO.class);
            LanceDAO lanceDAO = FabricaDeDaos.getDAO(LanceDAO.class);
            produtoDAO.setMap(mapDeProdutos);
            produtoDAO.setContador(contadorProdutos);
            lanceDAO.setMap(mapDeLances);
            lanceDAO.setContador(contadorLances);
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("o arquivo não existe e será criado.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}