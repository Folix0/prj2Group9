/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restDao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface DAOlite<T> {
    /*
    Optional<T> get(int id);

    Collection<T> getAll();

    Optional<I> save(T t);

    void update(T t);

    void delete(T t);
*/
    void save(T e);
    T get(int id) throws Exception;
    Collection<T> getAll() throws Exception;
    T update(T e);
    void delete(int id);
}
