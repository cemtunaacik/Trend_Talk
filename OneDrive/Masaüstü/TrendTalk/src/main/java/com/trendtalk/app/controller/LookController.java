package com.trendtalk.app.controller;

import com.trendtalk.app.repository.entity.Look;
import com.trendtalk.app.service.interfaces.IFileStorageService;
import com.trendtalk.app.service.interfaces.ILookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/looks")
public class LookController {

    private final ILookService lookService;
    private final IFileStorageService fileStorageService;

    @Autowired
    public LookController(ILookService lookService, IFileStorageService fileStorageService) {
        this.lookService = lookService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<Look> createLook(@RequestParam("file") MultipartFile file, @RequestParam Integer userId) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        Look look = new Look();
        look.setImageUrl(fileDownloadUri);
        look.setUserId(userId);
        return ResponseEntity.ok(lookService.createLook(look));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Look> updateLook(@PathVariable Integer id,
                                           @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        Look look = new Look();
        look.setImageUrl(fileDownloadUri);
        return ResponseEntity.ok(lookService.updateLook(id, look));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLook(@PathVariable Integer id) {
        lookService.deleteLook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Look>> getLooksByUserId(@PathVariable Integer userId) {
        List<Look> looks = lookService.getLooksByUserId(userId);
        return ResponseEntity.ok(looks);
    }
}
