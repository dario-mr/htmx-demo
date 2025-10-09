package com.dariom.v1.controller;

import com.dariom.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoController {

  private final TodoService service;

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("todos", service.findAll());
    return "home";
  }

  @PostMapping("/todos")
  public String add(@RequestParam String text, Model model) {
    var todo = service.add(text);
    model.addAttribute("todo", todo);
    return "fragments/todo :: newTodo(todo=${todo})";
  }

  @DeleteMapping("/todos/{id}")
  public String delete(@PathVariable long id) {
    service.delete(id);
    return "fragments/empty :: none";
  }
}
