package com.example.my_first_app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Kein @RestController!
public class TodoPageController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos-page")
    public String showTodos(@RequestParam int userId, Model model) {
        var todos = todoRepository.findAllByUserId(userId);
        model.addAttribute("todos", todos);
        model.addAttribute("userId", userId); // damit das Formular weiß, wem das Todo gehört
        return "todos";
    }

    @PostMapping("/todo/save")
    public String saveTodo(@RequestParam String description,
                           @RequestParam boolean isDone,
                           @RequestParam int userId) {
        Todo todo = new Todo();
        todo.setDescription(description);
        todo.setIsDone(isDone);
        todo.setUserId(userId);

        todoRepository.save(todo);

        return "redirect:/todos-page"; // Seite neu laden
    }
    @PostMapping("/todo/done")
    public String setDone(@RequestParam int id) {
        var todoOpt = todoRepository.findById(id);
        if (todoOpt.isPresent()) {
            Todo todo = todoOpt.get();
            todo.setIsDone(true);
            todoRepository.save(todo);
        }
        return "redirect:/todos-page";
    }


}
