package com.duofuen.elab.dto;

import com.duofuen.elab.domain.Banner;
import org.springframework.web.multipart.MultipartFile;

public class BannerDto extends Banner {

    private MultipartFile imageFile;
    private String imageUrl;

    public BannerDto() {
    }

    public BannerDto(Banner banner) {
        this.setId(banner.getId());
        this.setName(banner.getName());
        this.setComment(banner.getComment());
        this.setPriority(banner.getPriority());
        this.setUrl(banner.getUrl());
        this.setImage(banner.getImage());
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
