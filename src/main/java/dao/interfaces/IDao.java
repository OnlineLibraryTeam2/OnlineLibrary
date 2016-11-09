package dao.interfaces;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexp on 16.1.11.
 * IDAO main interface, typed.
 */

public interface IDao<T> {

    boolean add(T obj);

    boolean delete(T obj);

}
