package com.example.helloworld;

public class dataModel {
    int images;
    String header, desc;

    public dataModel(int images, String header, String desc) {
        this.images = images;
        this.header = header;
        this.desc = desc;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
