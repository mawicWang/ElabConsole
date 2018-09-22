package com.duofuen.elab.dto;

import com.duofuen.elab.domain.Banner;

import java.io.File;

public class BannerDto extends Banner {

    private File imageFile;

    public BannerDto(Banner banner) {
        this.setId(banner.getId());
        this.setName(banner.getName());
        this.setComment(banner.getComment());
        this.setPriority(banner.getPriority());
        this.setUrl(banner.getUrl());
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
}
