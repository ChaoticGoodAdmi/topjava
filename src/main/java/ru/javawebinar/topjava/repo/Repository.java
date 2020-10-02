package ru.javawebinar.topjava.repo;

import java.util.List;

public interface Repository<T> {
    void save(T t);

    T get(int id);

    void update(T t);

    void delete(int id);

    List<T> getAll();

    int size();

    int getId(T t);
}
