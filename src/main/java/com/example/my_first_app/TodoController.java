package com.example.my_first_app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController  // API-Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;


    // Einzelnes Todo
    @GetMapping("/todo")
    public ResponseEntity<Todo> get(@RequestParam(value = "id") int id) {
        Optional<Todo> todoInDb = todoRepository.findById(id);

        if (todoInDb.isPresent()) {
            return new ResponseEntity<Todo>(todoInDb.get(), HttpStatus.OK);
        }

        return new ResponseEntity("no todo found with id" + id, HttpStatus.NOT_FOUND);
    }

    // Alle Todos für User
    @GetMapping("/todo/all")
    public ResponseEntity<Iterable<Todo>> getAll(@RequestHeader("Api-secret")String secret) {

        var userBySecret = userRepository.findBySecret(secret);

        if (userBySecret.isPresent()) {

            Iterable<Todo> alltodosInDb = todoRepository.findAllByUserId(userBySecret.get().getId());
            return new ResponseEntity<Iterable<Todo>>(alltodosInDb, HttpStatus.OK);

        }
        return new ResponseEntity("Invalid secret",HttpStatus.BAD_REQUEST);

    }

    // Neues Todo
    @PostMapping("/todo")
    public ResponseEntity<Todo> create(@RequestBody Todo newtodo) {
        todoRepository.save(newtodo);
        return new ResponseEntity<Todo>(newtodo, HttpStatus.OK);
    }

    // Todo löschen
    @DeleteMapping("/todo")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {

        Optional<Todo> todoInDb = todoRepository.findById(id);
        // Todo Delete

        if (todoInDb.isPresent()) {
            todoRepository.deleteById(id);
            return new ResponseEntity("Todo deleted ", HttpStatus.OK);
        }
        return new ResponseEntity("No todo Founded id " + id, HttpStatus.NOT_FOUND);

    }


    // Todo aktualisieren
    @PutMapping("/todo")
    public ResponseEntity<Todo> edit(@RequestBody Todo editedTodo) {

        Optional<Todo> todoInDb = todoRepository.findById(editedTodo.getId());

        if (todoInDb.isPresent()) {

            // Todo Update
            Todo savedTodo = todoRepository.save(editedTodo);
            return new ResponseEntity<Todo>(savedTodo, HttpStatus.OK);

        }
        return new ResponseEntity("No todo updated id " + editedTodo.getId(), HttpStatus.NOT_FOUND);


    }

    // Status ändern
    @PatchMapping("/todo/setDone")
    public ResponseEntity<Todo> setDone(@RequestParam(value = "isDone") boolean isDone,
                                        @RequestParam(value = "id") int id) {

        Optional<Todo> todoInDb = todoRepository.findById(id);

        if (todoInDb.isPresent()) {
            // Todo Update
            todoInDb.get().setIsDone(isDone);
            // isDone speichen
            Todo savedTodo = todoRepository.save(todoInDb.get());
            return new ResponseEntity<Todo>(savedTodo, HttpStatus.OK);

        }

        return new  ResponseEntity("No Saved Todo" + id,HttpStatus.NOT_FOUND);


    }






}
