package projeto.model;

import projeto.exception.DataInvalida;
import projeto.util.ID;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fatura implements Serializable {
    @ID
    private int numFatura;
    private LocalDate dataCancelamento;
    private LocalDate dataEmissao;
    private Cliente cliente;
    private List<ItemFaturado> itensFaturados;
    private double valorTotal;
    private double valorDesconto;
    private int cancel = 0;

    private static final NumberFormat NF; // Formatador para o parse de números

    private static final DateTimeFormatter DTF; // Formatador para o parse de objetos date-time

    static
    {
        Locale locale = Locale.of("pt", "BR");
        NF = NumberFormat.getNumberInstance(locale);
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        NF.setMaximumFractionDigits (2);
        NF.setMinimumFractionDigits (2);
    }

    public Fatura(String dataEmissao, Cliente cliente) throws DataInvalida {
        setDataEmissao(dataEmissao);
        this.cliente = cliente;
        this.itensFaturados = new ArrayList<ItemFaturado>();
    }

    @Override
    public String toString() {
        return "Fatura: " +
                "numero de fatura: " + numFatura +
                ", valor total: " + valorTotal +
                ", valor total desconto: " + valorDesconto +
                ", cliente: " + cliente.getNome() +
                ", dataFatura: " + getDataFaturaMasc() +
                '\n';
    }

    public void setDataEmissao(String novadataEmissao) throws DataInvalida {
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

    public void setDataCancelamento (String novadataCancelamento) throws DataInvalida {
            try {
                int dia = Integer.parseInt(novadataCancelamento.substring(0, 2));
                int mes = Integer.parseInt(novadataCancelamento.substring(3, 5));
                int ano = Integer.parseInt(novadataCancelamento.substring(6, 10));

                dataCancelamento = LocalDate.of(ano, mes, dia);
            } catch (StringIndexOutOfBoundsException |
                     NumberFormatException |
                     DateTimeException e) {
                throw new DataInvalida("Data inválida.");
            }
    }

    public double getValorTotal() {return valorTotal;}

    public double getValorDesconto() {return valorDesconto;}

    public void setValorTotal() {
        double soma = 0.0;
        for (ItemFaturado itemFaturado : itensFaturados) {
            soma += itemFaturado.getQtdFaturada() * itemFaturado.getPedido().getLivro().getPreco();
        }
        this.valorTotal = soma;
    }

    public void setValorTotalDesconto() {
        if(getCliente().getFaturas().size() >= 3){
            double soma = 0.0;
            for (ItemFaturado itemFaturado : itensFaturados) {
                soma += itemFaturado.getQtdFaturada() * itemFaturado.getPedido().getLivro().getPreco();
            }
            this.valorDesconto = soma * 0.05;
        }
        else this.valorDesconto = 0;
    }

    public int getNumFatura() {
        return numFatura;
    }

    public void setNumFatura(int numFatura) {
        this.numFatura = numFatura;
    }

    public int getCancel() {return cancel;}

    public void setCancel(int cancel) {this.cancel = cancel;}

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public String getDataFaturaMasc(){
        return DTF.format(dataEmissao);
    }

    public ItemFaturado getPrimeiroItem(){return itensFaturados.get(0);}

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
