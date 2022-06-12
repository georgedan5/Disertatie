package com.fitnessclub.SERVICES;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long productId, MultipartFile file);
    void saveImageFile2(Long angajatId, MultipartFile file);
}
