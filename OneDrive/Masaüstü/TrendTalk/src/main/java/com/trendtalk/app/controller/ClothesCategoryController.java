package com.trendtalk.app.controller;

import com.trendtalk.app.repository.entity.ClothesCategory;
import com.trendtalk.app.service.interfaces.IClothesCategoryService;
import com.trendtalk.app.service.interfaces.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class ClothesCategoryController {

    private final IClothesCategoryService categoryService;
    private final IFileStorageService fileStorageService;

    @Autowired
    public ClothesCategoryController(IClothesCategoryService categoryService, IFileStorageService fileStorageService) {
        this.categoryService = categoryService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<ClothesCategory> addCategory(@RequestPart("file") MultipartFile file,
                                                       @RequestParam("name") String name) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        ClothesCategory category = new ClothesCategory();
        category.setImageUrl(fileDownloadUri);
        category.setName(name);
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothesCategory> updateCategory(@PathVariable Integer id,
                                                          @RequestPart("file") MultipartFile file,
                                                          @RequestParam("name") String name) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        ClothesCategory category = new ClothesCategory();
        category.setImageUrl(fileDownloadUri);
        category.setName(name);
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothesCategory> getCategoryById(@PathVariable Integer id) {
        Optional<ClothesCategory> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClothesCategory>> getAllCategories() {
        List<ClothesCategory> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
