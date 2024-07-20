package com.trendtalk.app.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);
}
