package com.pivinadanang.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post_image")
public class PostImageEntity extends AbstractEntity{
    @Column(name = "public_id")
    private String publicId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "url")
    private String url;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof PostImageEntity)) return false;
        PostImageEntity that = (PostImageEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
