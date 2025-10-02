package com.todoapp.service;

import com.todoapp.entity.Todo;
import com.todoapp.entity.TodoCollection;
import com.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodosByCollection(TodoCollection collection) {
        return todoRepository.findByCollectionOrderByCreatedAtDesc(collection);
    }

    public Optional<Todo> getTodoByIdAndCollection(Long id, TodoCollection collection) {
        return todoRepository.findByIdAndCollection(id, collection);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo updatedTodo, TodoCollection collection) {
        Optional<Todo> todoOpt = todoRepository.findByIdAndCollection(id, collection);
        if (todoOpt.isPresent()) {
            Todo todo = todoOpt.get();
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todo.setCompleted(updatedTodo.getCompleted());
            todo.setType(updatedTodo.getType());
            todo.setDueDate(updatedTodo.getDueDate());
            todo.setTargetAmount(updatedTodo.getTargetAmount());
            todo.setCurrentAmount(updatedTodo.getCurrentAmount());
            return todoRepository.save(todo);
        }
        return null;
    }

    public Todo addMoneyToSavingGoal(Long id, BigDecimal amount, TodoCollection collection) {
        Optional<Todo> todoOpt = todoRepository.findByIdAndCollection(id, collection);
        if (todoOpt.isPresent()) {
            Todo todo = todoOpt.get();
            if (todo.getType() == Todo.TodoType.SAVING) {
                BigDecimal newAmount = todo.getCurrentAmount().add(amount);
                todo.setCurrentAmount(newAmount);
                return todoRepository.save(todo);
            }
        }
        return null;
    }

    public boolean deleteTodo(Long id, TodoCollection collection) {
        Optional<Todo> todoOpt = todoRepository.findByIdAndCollection(id, collection);
        if (todoOpt.isPresent()) {
            todoRepository.delete(todoOpt.get());
            return true;
        }
        return false;
    }
}