package dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    public abstract void inserir(T entidade);
    public abstract List<T> listar();
    Optional<T> buscarPorId(ID id);
}