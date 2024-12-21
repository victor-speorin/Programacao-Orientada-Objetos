package projeto.service;

import projeto.dao.DaoLivro;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.model.Livro;
import projeto.util.FabricaDeDaos;

import java.nio.channels.InterruptedByTimeoutException;

public class LivroService {
    private final DaoLivro daoLivro = FabricaDeDaos.getDAO(DaoLivro.class);

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
        if (livro.getLivrospedidos().isEmpty()){
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
}
