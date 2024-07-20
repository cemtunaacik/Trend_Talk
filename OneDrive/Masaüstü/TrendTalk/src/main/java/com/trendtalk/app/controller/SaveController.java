package com.trendtalk.app.controller;

import com.trendtalk.app.repository.entity.Save;
import com.trendtalk.app.service.interfaces.ISaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saves")
public class SaveController {

    private final ISaveService saveService;

    @Autowired
    public SaveController(ISaveService saveService) {
        this.saveService = saveService;
    }

    @PostMapping
    public ResponseEntity<Save> savePost(@RequestBody Save save) {
        return ResponseEntity.ok(saveService.savePost(save));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> unsavePost(@PathVariable Integer id) {
        saveService.unsavePost(id);
        return ResponseEntity.ok().build();
    }
}
