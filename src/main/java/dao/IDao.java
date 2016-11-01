package dao;

/**
 * Created by alexp on 16.1.11.
 */
public interface IDao<T> {

    public boolean add(T obj);

    public boolean update(T obj);

    public boolean delete(T obj);

}
