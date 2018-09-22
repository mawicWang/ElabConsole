package com.duofuen.elab.service;

import com.duofuen.elab.domain.Image;
import com.duofuen.elab.domain.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveFromFile(MultipartFile imageFile) throws IOException {
        String fullName = imageFile.getOriginalFilename();
        int suffixIdx = fullName.lastIndexOf('.');
        String suffix = fullName.substring(suffixIdx + 1);

        Image image = new Image();
        image.setType(suffix);
        image.setContent(imageFile.getBytes());

        return imageRepository.save(image);
    }
}
