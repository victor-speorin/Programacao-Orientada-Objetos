package projeto.principais;

import corejava.Console;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.model.ItemFaturado;
import projeto.model.Livro;
import projeto.service.LivroService;

import java.util.List;

public class LivroPrincipal {
    public LivroService livroService = new LivroService();
    public void principal(){
        Livro xLivro;
        boolean continua = true;
        while(continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um livro");
            System.out.println('\n' + "2. Alterar um livro");
            System.out.println('\n' + "3. Remover um livro");
            System.out.println('\n' + "4. Listar todos livros");
            System.out.println('\n' + "5. Listar livros nunca faturados");
            System.out.println('\n' + "6. Listar itens faturados de um livro em um mês e ano");
            System.out.println('\n' + "7. Listar livros faturados em um mês e ano");
            System.out.println('\n' + "8. Voltar");
            int opcao = Console.readInt('\n' + "Digite um número de 1 a 8: ");
            switch (opcao){
                case 1->{
                    String titulo = Console.readLine('\n' + "Digite o titulo: ");
                    String isbn = Console.readLine('\n' + "Digite o isbn: ");
                    String descricao = Console.readLine('\n' + "Digite a descricao: ");
                    int qtdEstoque = Console.readInt('\n' + "Digite a quantidade desse livro no estoque: ");
                    double preco = Console.readDouble('\n' + "Digite o preço: ");
                    xLivro = new Livro(isbn, titulo, descricao, preco, qtdEstoque);
                    livroService.inclusao(xLivro);
                    System.out.println('\n' + "Livro cadastrado com sucesso!");
                }
                case 2->{
                    int idAlteracao = Console.readInt('n' + "Digite o id do livro a ser alterado: ");
                    try{
                        xLivro = livroService.livro_id(idAlteracao);
                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Titulo");
                        System.out.println('\n' + "2. ISBN");
                        System.out.println('\n' + "3. Descrição");
                        System.out.println('\n' + "4. Quantidade no estoque");
                        int opcaoAlteracao = Console.readInt('\n' + "Digite um numero de 1 a 4: ");
                        switch (opcaoAlteracao){
                            case 1->{
                                String novoTitulo = Console.readLine('\n' + "Digite o novo titulo: ");
                                xLivro.setTitulo(novoTitulo);
                                System.out.println('\n' + "O titulo acaba de ser alterado com sucesso!");
                            }
                            case 2->{
                                String novoISBN = Console.readLine('\n' + "Digite o novo ISBN: ");
                                xLivro.setIsbn(novoISBN);
                                System.out.println('\n' + "O ISBN acaba de ser alterado com sucesso!");
                            }
                            case 3->{
                                String novaDescricao = Console.readLine('\n' + "Digite a nova descricao: ");
                                xLivro.setDescricao(novaDescricao);
                                System.out.println('\n' + "A descricao acaba de ser alterada com sucesso!");
                            }
                            case 4->{
                                int soma = Console.readInt('\n' + "Digite quanto quer adicionar ao estoque: ");
                                xLivro.setQtdEstoque(soma + xLivro.getQtdEstoque());
                                System.out.println('\n' + "Estoque alterado com sucesso!");
                            }
                            default -> {
                                System.out.println('\n' + "Opção inválida!");
                            }
                        }
                    } catch (EntidadeInexistente e) {
                        System.out.println('\n' + e.getMessage());
                    }


                }
                case 3->{
                    int idRemocao = Console.readInt('\n' + "Digite o id do livro a ser removido: ");
                    try{
                        xLivro = livroService.livro_id(idRemocao);
                        livroService.remover(idRemocao);
                        System.out.println('\n' + "Livro removido com sucesso");
                    } catch (EntidadeInexistente | ItemAindaAssociado e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4->{
                    List<Livro> livros = livroService.recuperarLivros();
                    if(livros.isEmpty()){
                        System.out.println('\n' + "Sem livros cadastrados no sistema.");
                        break;
                    }
                    for(Livro livro : livros){
                        System.out.println('\n' + livro.toString());
                    }
                }
                case 5-> {
                    List<Livro> livrosNunca = livroService.recuperarTodosLivrosNuncaFaturados();
                    List<Livro> livros = livroService.recuperarLivros();
                    if(livros.isEmpty()){
                        System.out.println('\n' + "Sem livros cadastrados no sistema.");
                        break;
                    }
                    if(livrosNunca.isEmpty()){
                        System.out.println('\n' + "Todos os livros ja foram faturados");
                        break;
                    }
                    for(Livro livro : livrosNunca){
                        System.out.println('\n' + livro.toString());
                    }
                }
                case 6-> {
                    int mes = Console.readInt('\n' + "Mes:");
                    int ano = Console.readInt('\n' + "Ano:");
                    int id = Console.readInt('\n' + "Digite o id do livro: ");
                    try{
                        List<ItemFaturado> itensMesAno = livroService.recuperarItensFaturadosMesAno(id, mes, ano);
                        System.out.println('\n' + "Lista de itens faturados do livro " + id + " no mes " + mes + " e ano " + ano);
                        for(ItemFaturado item : itensMesAno){
                            System.out.println('\n' + item.toString());
                        }
                    } catch(EntidadeInexistente e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 7-> {
                    int mes = Console.readInt('\n' + "Mes:");
                    int ano = Console.readInt('\n' + "Ano:");
                    try{
                        List<Livro> lista = livroService.recuperarLivrosFaturadosMesAno(mes, ano);
                        if(lista.isEmpty()){
                            System.out.println('\n' + "Sem livros faturados nesse mes e ano");
                            break;
                        }
                        for(Livro livro : lista){
                            List<ItemFaturado> itens = livroService.recuperarItensFaturadosMesAno(livro.getId(), mes, ano);
                            int c = 0;
                            for(ItemFaturado item : itens) c += item.getQtdFaturada();
                            System.out.println('\n' + "O livro " + livro.getTitulo() + " foi faturado " + c + " vezes nesse período.");
                        }
                    } catch (EntidadeInexistente e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 8-> continua = false;
                default -> {
                    System.out.println('\n' + "Opção inválida!");
                }
            }
        }
    }
}