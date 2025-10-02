package com.todoapp.controller;

import com.todoapp.entity.TodoCollection;
import com.todoapp.entity.User;
import com.todoapp.repository.UserRepository;
import com.todoapp.service.TodoCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/collections")
@CrossOrigin(origins = "*")
public class TodoCollectionController {

    @Autowired
    private TodoCollectionService todoCollectionService;

    @Autowired
    private UserRepository userRepository;

    // For now, we'll use a hardcoded user ID (1) since we don't have JWT auth yet
    private User getCurrentUser() {
        return userRepository.findById(1L).orElse(null);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCollections() {
        User user = getCurrentUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "User not found"));
        }

        List<TodoCollection> collections = todoCollectionService.getAllCollectionsByUser(user);
        return ResponseEntity.ok(Map.of("success", true, "data", collections));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCollection(@PathVariable Long id) {
        User user = getCurrentUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "User not found"));
        }

        Optional<TodoCollection> collection = todoCollectionService.getCollectionByIdAndUser(id, user);
        if (collection.isPresent()) {
            return ResponseEntity.ok(Map.of("success", true, "data", collection.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCollection(@RequestBody Map<String, String> request) {
        User user = getCurrentUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "User not found"));
        }

        String name = request.get("name");
        String description = request.get("description");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Collection name is required"));
        }

        TodoCollection collection = todoCollectionService.createCollection(name, description, user);
        return ResponseEntity.ok(Map.of("success", true, "data", collection));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCollection(@PathVariable Long id, @RequestBody Map<String, String> request) {
        User user = getCurrentUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "User not found"));
        }

        String name = request.get("name");
        String description = request.get("description");

        TodoCollection collection = todoCollectionService.updateCollection(id, name, description, user);
        if (collection != null) {
            return ResponseEntity.ok(Map.of("success", true, "data", collection));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCollection(@PathVariable Long id) {
        User user = getCurrentUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "User not found"));
        }

        boolean deleted = todoCollectionService.deleteCollection(id, user);
        if (deleted) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Collection deleted successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}