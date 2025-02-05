package projeto.service;

import projeto.dao.DaoItemFaturado;
import projeto.dao.DaoLivro;
import projeto.dao.DaoPedido;
import projeto.exception.ItemAindaAssociado;
import projeto.exception.EntidadeInexistente;
import projeto.model.ItemFaturado;
import projeto.model.ItemPedido;
import projeto.model.Livro;
import projeto.util.FabricaDeDaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LivroService {
    private final DaoLivro daoLivro = FabricaDeDaos.getDAO(DaoLivro.class);
    private final DaoItemFaturado daoItemFaturado = FabricaDeDaos.getDAO(DaoItemFaturado.class);

    public Livro inclusao(Livro livro) {
        daoLivro.incluir(livro);
        return livro;
    }

    public Livro alterar_isbn(Livro livro, String isbn) {
        livro.setIsbn(isbn);
        return livro;
    }

    public Livro alterar_titulo(Livro livro, String titulo) {
        livro.setTitulo(titulo);
        return livro;
    }

    public Livro alterar_descricao(Livro livro, String descricao) {
        livro.setDescricao(descricao);
        return livro;
    }

    public Livro alterar_qtd(Livro livro, int qtd) {
        livro.setQtdEstoque(qtd);
        return livro;
    }

    public Livro alterar_preco(Livro livro, double preco) {
        livro.setPreco(preco);
        return livro;
    }

    public Livro remover(int id) throws EntidadeInexistente, ItemAindaAssociado {
        Livro livro = livro_id(id);
        if (livro.getItensPedidos().isEmpty()){
            daoLivro.remover(id);
        }
        else{
            throw new ItemAindaAssociado("Esse livro não pode ser removido!");
        }
        return livro;
    }

    public Livro livro_id(int id) throws EntidadeInexistente {
        Livro livro = daoLivro.recuperarPorId(id);
        if (livro == null) {
            throw new EntidadeInexistente("Esse livro não existe!");
        }
        else return livro;
    }

    public List<Livro> recuperarLivros(){
        return daoLivro.recuperarTodos();
    }

    public List<Livro> recuperarTodosLivrosNuncaFaturados(){
        return daoLivro.recuperarTodosLivrosNuncaFaturados();
    }

    public List<ItemFaturado> recuperarItensFaturadosMesAno(int id, int mes, int ano) throws EntidadeInexistente {
        Livro livro = livro_id(id);
        List<ItemFaturado> itensFaturados = daoItemFaturado.recuperarItensFaturadosDeUmLivro(livro);
        List<ItemFaturado> resposta = new ArrayList<ItemFaturado>();
        for(ItemFaturado itemFaturado : itensFaturados){
            if((itemFaturado.getFatura().getDataEmissao().getMonthValue() == mes) && (itemFaturado.getFatura().getDataEmissao().getYear() == ano)){
                resposta.add(itemFaturado);
            }
        }
        return resposta;
    }

    public boolean jaFaturadoMesAno(int id, int mes, int ano) throws EntidadeInexistente {
        List<ItemFaturado> itens = recuperarItensFaturadosMesAno(id, mes, ano);
        if(itens.isEmpty()) return false;
        return true;
    }

    public List<Livro> recuperarLivrosFaturadosMesAno(int mes, int ano) throws EntidadeInexistente {
        Map<Integer, Livro> mapDeLivros = daoLivro.getMap();
        return mapDeLivros.values()
                .stream()
                .filter((livro) -> {
                    try {
                        return (jaFaturadoMesAno(livro.getId(), mes, ano));
                    } catch (EntidadeInexistente e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }
}
