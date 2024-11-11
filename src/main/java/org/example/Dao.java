package org.example;

import java.util.List;

public interface Dao <T, K> {
    T getById(K id);
    T getByName(String name);
    List<T> getAll();
}