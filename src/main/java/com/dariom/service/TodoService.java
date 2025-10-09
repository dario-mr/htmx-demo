package com.dariom.service;

import static org.springframework.util.StringUtils.hasText;

import com.dariom.model.Todo;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final List<Todo> todos = new ArrayList<>();
  private long nextId = 4;

  @PostConstruct
  public void init() {
    todos.add(new Todo(1, "first todo"));
    todos.add(new Todo(2, "second todo"));
    todos.add(new Todo(3, "third todo"));
  }

  public List<Todo> findAll() {
    return todos;
  }

  public Todo add(String text) {
    if (!hasText(text)) {
      throw new RuntimeException("Todo text cannot be empty.");
    }

    var todo = new Todo(nextId++, text);
    todos.add(todo);
    return todo;
  }

  public void delete(long id) {
    if (id == 3) {
      throw new RuntimeException("Error deleting todo.");
    }

    todos.removeIf(todo -> todo.id() == id);
  }
}
