package projeto.service;

import projeto.dao.DaoCliente;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.model.Cliente;
import projeto.util.FabricaDeDaos;

import java.util.List;

public class ClienteService {
    private final DaoCliente daoCliente = FabricaDeDaos.getDAO(DaoCliente.class);

    public Cliente inclusao(Cliente cliente) {
        daoCliente.incluir(cliente);
        return cliente;
    }

    public Cliente alterar_nome(Cliente cliente, String nome) {
        cliente.setNome(nome);
        return cliente;
    }

    public Cliente alterar_cpf(Cliente cliente, String cpf) {
        cliente.setCpf(cpf);
        return cliente;
    }

    public Cliente alterar_email(Cliente cliente, String email) {
        cliente.setEmail(email);
        return cliente;
    }

    public Cliente alterar_telefone(Cliente cliente, String tel) {
        cliente.setTelefone(tel);
        return cliente;
    }

    public Cliente alterar_endereco(Cliente cliente, String endereco) {
        cliente.setEndereco(endereco);
        return cliente;
    }

    public Cliente remover(int id) throws ItemAindaAssociado, EntidadeInexistente {
        Cliente cliente = cliente_id(id);
        if(cliente.getPedidos().isEmpty() && cliente.getFaturas().isEmpty()) {
            daoCliente.remover(id);
        }
        else{
            throw new ItemAindaAssociado("Esse Cliente não pode ser removido!");
        }
        return cliente;
    }

    public Cliente cliente_id(int id) throws EntidadeInexistente {
        Cliente cliente = daoCliente.recuperarPorId(id);
        if (cliente == null){
            throw new EntidadeInexistente("Cliente Inexistente!");
        }
        return cliente;
    }

    public List<Cliente> listar(){
        return daoCliente.recuperarTodos();
    }
}
