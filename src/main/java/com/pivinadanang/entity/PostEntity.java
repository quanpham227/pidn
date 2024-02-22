package com.pivinadanang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "post")
public class PostEntity  extends AbstractEntity{

    @Column (name="title")
    private String title;

    @Column(name = "thumbnailurl", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments = new ArrayList<>();
}
