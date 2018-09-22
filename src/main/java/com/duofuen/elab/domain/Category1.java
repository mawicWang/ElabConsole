package com.duofuen.elab.domain;

import javax.persistence.*;


@Entity
public class Category1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String textTitle;
    private String textCn;
    private String textEn;
    private Integer image1;
    private Integer image2;
    private String comment;
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image1", insertable = false, updatable = false)
    private Image image1Ref;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image2", insertable = false, updatable = false)
    private Image image2Ref;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextCn() {
        return textCn;
    }

    public void setTextCn(String textCn) {
        this.textCn = textCn;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }

    public Integer getImage1() {
        return image1;
    }

    public void setImage1(Integer image1) {
        this.image1 = image1;
    }

    public Integer getImage2() {
        return image2;
    }

    public void setImage2(Integer image2) {
        this.image2 = image2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Image getImage1Ref() {
        return image1Ref;
    }

    public void setImage1Ref(Image image1Ref) {
        this.image1Ref = image1Ref;
    }

    public Image getImage2Ref() {
        return image2Ref;
    }

    public void setImage2Ref(Image image2Ref) {
        this.image2Ref = image2Ref;
    }
}
