package com.duofuen.elab.domain;

import javax.persistence.*;

@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer image;
    private Integer priority;
    private String comment;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image", insertable = false, updatable = false)
    private Image imageRef;

    public Image getImageRef() {
        return imageRef;
    }

    public void setImageRef(Image imageRef) {
        this.imageRef = imageRef;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
