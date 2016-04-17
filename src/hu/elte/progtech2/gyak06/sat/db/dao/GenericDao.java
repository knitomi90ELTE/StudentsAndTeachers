package hu.elte.progtech2.gyak06.sat.db.dao;

import hu.elte.progtech2.gyak06.sat.db.entity.PersistentEntity;
import java.util.List;

public interface GenericDao<T extends PersistentEntity> {

    public List<T> findAll();

    public T findById(Integer id);

    public void create(T entity);

    public void update(T entity);

    public void delete(T entity);

}
