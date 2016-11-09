package dao.interfaces;


/**
 * Created by alexp on 16.1.11.
 * IDAO main interface, typed.
 */

public interface IDao<T> {

    boolean add(T obj);

    boolean delete(T obj);

}
