package com.example.my_first_app;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    // Login prüfen
    Optional<User> findByEmailAndPassword(String email, String password);

    // API-Zugriff prüfen
    Optional<User> findBySecret(String secret);

}
