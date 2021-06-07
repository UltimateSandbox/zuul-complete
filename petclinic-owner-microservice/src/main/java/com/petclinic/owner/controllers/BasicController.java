package com.petclinic.owner.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BasicController<T> {

    T add(T t);

    T get(Long id);

    T modify(T t);

    ResponseEntity<String> delete(Long id);

    List<T> getAll();

}
