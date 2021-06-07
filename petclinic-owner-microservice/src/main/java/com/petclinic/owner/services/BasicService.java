package com.petclinic.owner.services;

import java.util.List;

public interface BasicService<T> {

    T add(T t);

    T get(Long id);

    T modify(T t);

    void delete(Long id);

    List<T> getAll();
}
