/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restDao;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;


public interface DAOlite<T> {

    void save(T e) throws IOException;

    Optional<T> get(int id) throws IOException, InterruptedException;

    Optional<Collection<T>> getAll();

    void update(T e);

    void delete(int id);
}
