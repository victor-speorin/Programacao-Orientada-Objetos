package projeto.dao;

import projeto.model.Fatura;

import java.util.List;

public interface DaoFatura extends DaoGenerico<Fatura> {
    public List<Fatura> listarFaturas(int id);
}