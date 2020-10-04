package ru.javawebinar.topjava.repo;

import java.util.List;

public interface Repository<T> {
    T create(T t);

    T get(int id);

    T update(T t);

    void delete(int id);

    List<T> getAll();
}
