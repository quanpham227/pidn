package com.pivinadanang.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "category")
public class CategoryEntity extends AbstractEntity {

    @Column (name = "name", nullable = false, length = 100)
    private String name;

    public CategoryEntity (){

    }

    public CategoryEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
