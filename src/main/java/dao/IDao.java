package dao;

/**
 * Created by alexp on 16.1.11.
 * IDAO main interface, typed.
 */
interface IDao<T> {

    boolean add(T obj);

    boolean delete(T obj);

}
