package projeto.service;

import projeto.dao.DaoFatura;
import projeto.exception.EntidadeInexistente;
import projeto.exception.ItemAindaAssociado;
import projeto.model.Fatura;
import projeto.util.FabricaDeDaos;

public class FaturaService {
    private final DaoFatura daoFatura = FabricaDeDaos.getDAO(DaoFatura.class);

    public Fatura inclusao(Fatura fatura) {
        fatura.getCliente().getFaturas().add(fatura);
        return daoFatura.incluir(fatura);
    }

    public Fatura remocao(int numfatura) throws ItemAindaAssociado, EntidadeInexistente {
        Fatura fatura = fatura_num(numfatura);
        if (fatura.getItensFaturados().isEmpty()){
            daoFatura.remover(numfatura);
        }
        else{
            throw new ItemAindaAssociado("Essa Fatura não pode ser removida!");
        }
        return fatura;
    }

    public Fatura fatura_num(int numfatura) throws EntidadeInexistente {
        Fatura fatura = daoFatura.recuperarPorId(numfatura);
        if (fatura == null){
            throw new EntidadeInexistente("Fatura Inexistente!");
        }
        return fatura;
    }
}
