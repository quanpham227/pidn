package com.pivinadanang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity (name = "gallery")
public class GalleryEntity extends AbstractEntity{
    @Column (name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    public GalleryEntity (){

    }
    public GalleryEntity(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public GalleryEntity(Long id, String name, String url) {
        super(id);
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
