package projeto.model;

import projeto.util.ID;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Cliente implements Serializable {
    @ID
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private List<Fatura> faturas;
    private List<Pedido> pedidos;
    public Cliente(String nome, String cpf, String telefone, String email, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.faturas = new ArrayList<Fatura>();
        this.pedidos = new ArrayList<Pedido>();

    }

    @Override
    public String toString() {
        return "Cliente: " +
                "ID: " + id +
                ", Nome: '" + nome + '\'' +
                ", CPF: '" + cpf + '\'' +
                ", Endereco: '" + endereco + '\'' +
                ", Telefone: '" + telefone + '\'' +
                ", e-mail: '" + email + '\'' +
                '\n';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
