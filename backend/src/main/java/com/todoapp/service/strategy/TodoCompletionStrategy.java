package com.todoapp.service.strategy;

import com.todoapp.entity.Todo;


public interface TodoCompletionStrategy {
    
    
    boolean canComplete(Todo todo);
    
    
    void complete(Todo todo);
    
    
    String getCompletionStatus(Todo todo);
    
    
    boolean shouldAutoComplete(Todo todo);
}