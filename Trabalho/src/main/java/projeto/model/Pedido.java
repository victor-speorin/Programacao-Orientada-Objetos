package projeto.model;

import projeto.util.ID;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import projeto.exception.DataInvalida;
import javax.xml.crypto.Data;

public class Pedido implements Serializable {

    @ID
    private int numPedido;
    private LocalDate dataEmissao;
    private LocalDate dataCancelamento;
    private String status;
    private Cliente cliente;
    private List<ItemPedido> itensPedidos;
    private String enderecoEntrega;
    private int faturado;

    private static final NumberFormat NF; // Formatador para imprimir e efetuar o parse de números

    // Formatador para imprimir e efetuar o parse de objetos date-time
    private static final DateTimeFormatter DTF;

    static
    {
        Locale locale = Locale.of("pt", "BR");
        NF = NumberFormat.getNumberInstance(locale);
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        NF.setMaximumFractionDigits (2);	   // O default é 3.
        NF.setMinimumFractionDigits (2);
    }

    public String toString() {
        return "Número do pedido: " + numPedido +
                "  Data do pedido: " + getDataPedidoMasc() +
                "  Status: " + status +
                "  Cliente: " + cliente.getNome();
    }

    public void listarPedidoComItens(){
        System.out.println('\n' + "Número do pedido = " + numPedido + ", feito pelo cliente de id " + cliente.getId() + " na data " + itensPedidos.getFirst().getPedido().getDataEmissao() + ", seu status: " + status + " e seus itens:");
        for(ItemPedido item : itensPedidos){
            System.out.println('\n' + item.listarItemPedido());
        }
        System.out.println("\n");
    }


    public Pedido(String dataEmissao, Cliente cliente, String enderecoEntrega) throws DataInvalida {
        setDataEmissao(dataEmissao);
        this.status = "Em aberto";
        this.cliente = cliente;
        this.itensPedidos = new ArrayList<>();
        this.enderecoEntrega = enderecoEntrega;
        this.faturado = 0;
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String novadataEmissao) throws DataInvalida {
        {
            try {
                int dia = Integer.parseInt(novadataEmissao.substring(0, 2));
                int mes = Integer.parseInt(novadataEmissao.substring(3, 5));
                int ano = Integer.parseInt(novadataEmissao.substring(6, 10));

                dataEmissao = LocalDate.of(ano, mes, dia);
            } catch (StringIndexOutOfBoundsException |
                     NumberFormatException |
                     DateTimeException e) {
                throw new DataInvalida("Data inválida.");
            }
        }
    }

    public String getStatus () {
            return status;
        }

    public String getDataPedidoMasc() {
        return DTF.format(dataEmissao);
    }

    public void setStatus (String status){
        this.status = status;
    }

    public LocalDate getDataCancelamento () {
        return dataCancelamento;
    }

    public void setDataCancelamento (String novadataCancelamento) throws DataInvalida {
        {
            try {
                int dia = Integer.parseInt(novadataCancelamento.substring(0, 2));
                int mes = Integer.parseInt(novadataCancelamento.substring(3, 5));
                int ano = Integer.parseInt(novadataCancelamento.substring(6, 10));

                dataEmissao = LocalDate.of(ano, mes, dia);
            } catch (StringIndexOutOfBoundsException |
                     NumberFormatException |
                     DateTimeException e) {
                throw new DataInvalida("Data inválida.");
            }
        }
    }

    public Cliente getCliente () {
        return cliente;
    }

    public void setCliente (Cliente cliente){
        this.cliente = cliente;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public int getFaturado() {
        return faturado;
    }

    public void setFaturado(int faturado) {
        this.faturado = faturado;
    }
}