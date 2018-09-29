package com.duofuen.elab.dto;

import com.duofuen.elab.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public class PageDto extends Page {
    private MultipartFile file;

    public PageDto() {
    }

    public PageDto(Page page) {
        this.setId(page.getId());
        this.setComment(page.getComment());
        this.setFileName(page.getFileName());
        this.setUrl(page.getUrl());
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
