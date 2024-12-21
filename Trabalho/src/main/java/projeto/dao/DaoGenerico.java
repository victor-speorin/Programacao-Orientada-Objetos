package projeto.dao;

import java.util.List;
import java.util.Map;

public interface DaoGenerico<V> {
    int getContador();
    void setContador(int contador);
    Map<Integer, V> getMap();
    void setMap(Map<Integer, V> map);
    V incluir(V obj);
    V alterar(V obj);
    V remover(Integer id);
    V recuperarPorId(Integer id);
    List<V> recuperarTodos();
}
