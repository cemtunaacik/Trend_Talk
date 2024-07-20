package com.trendtalk.app.controller;

import com.trendtalk.app.repository.entity.Clothes;
import com.trendtalk.app.service.interfaces.IClothesService;
import com.trendtalk.app.service.interfaces.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/clothes")
public class ClothesController {

    private final IClothesService clothesService;
    private final IFileStorageService fileStorageService;

    @Autowired
    public ClothesController(IClothesService clothesService, IFileStorageService fileStorageService) {
        this.clothesService = clothesService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<Clothes> addClothes(@RequestPart("file") MultipartFile file,
                                              @RequestParam("userId") Integer userId,
                                              @RequestParam("categoryId") Integer categoryId) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        Clothes clothes = new Clothes();
        clothes.setImageUrl(fileDownloadUri);
        clothes.setUserId(userId);
        clothes.setCategoryId(categoryId);
        return ResponseEntity.ok(clothesService.addClothes(clothes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clothes> updateClothes(@PathVariable Integer id,
                                                 @RequestPart("file") MultipartFile file,
                                                 @RequestParam("categoryId") Integer categoryId) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        Clothes clothes = new Clothes();
        clothes.setImageUrl(fileDownloadUri);
        clothes.setCategoryId(categoryId);
        return ResponseEntity.ok(clothesService.updateClothes(id, clothes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothes(@PathVariable Integer id) {
        clothesService.deleteClothes(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Clothes>> getClothesByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(clothesService.getClothesByUserId(userId));
    }

    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<List<Clothes>> getClothesByUserIdAndCategoryId(@PathVariable Integer userId, @PathVariable Integer categoryId) {
        return ResponseEntity.ok(clothesService.getClothesByUserIdAndCategoryId(userId, categoryId));
    }
}
