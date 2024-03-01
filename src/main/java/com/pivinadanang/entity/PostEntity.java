package com.pivinadanang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "post")
public class PostEntity  extends AbstractEntity{

    @Column (name="title",columnDefinition = "TEXT")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Date updateDate;


    @Column(name = "status")
    private PostStatus status;

    @Column(name = "post_name", columnDefinition = "TEXT")
    private String postName;


    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_image_id")
    private PostImageEntity image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments = new ArrayList<>();


    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

    @PrePersist
    public void prePersist() {
        createDate = new Date();
    }

    public void generatePostName() {
        if (StringUtils.isEmpty(this.title)) {
            this.postName = "";
        } else {
            // Loại bỏ các ký tự không mong muốn, thay thế khoảng trắng bằng dấu gạch ngang
            this.postName = this.title.trim()
                    .toLowerCase()
                    .replaceAll("[^a-z0-9\\s-]", "")
                    .replaceAll("\\s+", "-");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostEntity that = (PostEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
