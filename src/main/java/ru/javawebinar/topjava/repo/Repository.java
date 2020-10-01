package ru.javawebinar.topjava.repo;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
}
