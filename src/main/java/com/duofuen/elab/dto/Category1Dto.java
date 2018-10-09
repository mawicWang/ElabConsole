package com.duofuen.elab.dto;

import com.duofuen.elab.domain.Category1;
import org.springframework.web.multipart.MultipartFile;

public class Category1Dto extends Category1 {

    private MultipartFile imageFile1;
    private MultipartFile imageFile2;

    private String imageUrl1;
    private String imageUrl2;

    public Category1Dto() {
    }

    public Category1Dto(Category1 category1) {
        this.setId(category1.getId());
        this.setTextTitle(category1.getTextTitle());
        this.setTextCn(category1.getTextCn());
        this.setTextEn(category1.getTextEn());
        this.setImage1(category1.getImage1());
        this.setImage2(category1.getImage2());
        this.setComment(category1.getComment());
        this.setPriority(category1.getPriority());
    }

    public MultipartFile getImageFile1() {
        return imageFile1;
    }

    public void setImageFile1(MultipartFile imageFile1) {
        this.imageFile1 = imageFile1;
    }

    public MultipartFile getImageFile2() {
        return imageFile2;
    }

    public void setImageFile2(MultipartFile imageFile2) {
        this.imageFile2 = imageFile2;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }
}
