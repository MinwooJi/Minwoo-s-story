package com.dima.test.dto;
public class Image {

    // IMAGE_NUMBER 
    private Integer imageNumber;

    // POST_NUMBER 
    private Integer postNumber;

    // IMAGE_PATH 
    private String imagePath;

    public Integer getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(Integer imageNumber) {
        this.imageNumber = imageNumber;
    }

    public Integer getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(Integer postNumber) {
        this.postNumber = postNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Image 모델 복사
    public void CopyData(Image param)
    {
        this.imageNumber = param.getImageNumber();
        this.postNumber = param.getPostNumber();
        this.imagePath = param.getImagePath();
    }
}