package com.example.my_first_app;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface  TodoRepository extends CrudRepository<Todo,Integer> {

    // Alle Todos für einen User laden
    Set<Todo> findAllByUserId(int userId);








}

