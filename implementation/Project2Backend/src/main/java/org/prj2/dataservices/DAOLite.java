package org.prj2.dataservices;

import java.util.List;

public interface DAOLite<T> {

    T save(T e);
    T get(int id);
    List<T> getAll();
    T update(T e);
    void delete(int id);
}
