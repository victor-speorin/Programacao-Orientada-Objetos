package projeto.model;

import projeto.exception.DataInvalida;
import projeto.util.ID;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fatura {
    @ID
    private int numFatura;
    private LocalDate dataCancelamento;
    private LocalDate dataEmissao;
    private Cliente cliente;
    private List<ItemFaturado> itensFaturados;

    public Fatura(int numFatura, String dataEmissao, Cliente cliente, List<ItemFaturado> itensFaturados) throws DataInvalida {
        setDataEmissao(dataEmissao);
        this.cliente = cliente;
        this.itensFaturados = new ArrayList<>();
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

    public int getNumFatura() {
        return numFatura;
    }

    public void setNumFatura(int numFatura) {
        this.numFatura = numFatura;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFaturado> getItensFaturados() {
        return itensFaturados;
    }

    public void setItensFaturados(List<ItemFaturado> itensFaturados) {
        this.itensFaturados = itensFaturados;
    }
}
