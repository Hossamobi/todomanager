package com.example.my_first_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController  // Markiert REST API
public class Usercontroller {


    @Autowired  // Spring kümmert sich um das Repository
    private UserRepository userRepository;

    // Benutzer registrieren
    @PostMapping("/api/register")
    private ResponseEntity<User> register (@RequestBody User newUser){
        // generte Secret

        newUser.setSecret(UUID.randomUUID().toString()); // zufälliger API-Key
       var savedUser =  userRepository.save(newUser);
       return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);


    }
    // Benutzer abrufen
    @GetMapping("/user")
    private ResponseEntity<User> register(@RequestParam(value="id") int id){
        var user = userRepository.findById(id);

        if (user.isPresent()){
            return new ResponseEntity<User>(user.get(),HttpStatus.OK);
        }

        return new ResponseEntity("No User found with id " + id,HttpStatus.CREATED);



        }
    // Login validieren
    @GetMapping("/validate")
    private ResponseEntity<String> validate(@RequestParam(value="email") String email,
                             @RequestParam(value = "password")String password){
        var validUser = userRepository.findByEmailAndPassword(email,password);
        if(validUser.isPresent()){
            return new ResponseEntity<String>("API Secret "+ validUser.get().getSecret(),HttpStatus.OK);
        }
        return new ResponseEntity("no Acoount found", HttpStatus.NOT_FOUND);


        }






    }



