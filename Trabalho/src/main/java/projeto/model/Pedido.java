package projeto.model;

import projeto.exception.DataInvalida;
import projeto.util.ID;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    @ID
    private int numPedido;
    private LocalDate dataEmissao;
    private LocalDate dataCancelamento;
    private String status;
    private Cliente cliente;
    private List<ItemPedido> itensPedidos;
    private String enderecoEntrega;


    public Pedido(String dataEmissao, String status, Cliente cliente, String enderecoEntrega) throws DataInvalida {
        setDataEmissao(dataEmissao);
        this.status = status;
        this.cliente = cliente;
        this.itensPedidos = new ArrayList<>();
        this.enderecoEntrega = enderecoEntrega;
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
}