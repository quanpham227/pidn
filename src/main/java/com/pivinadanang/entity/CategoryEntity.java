package com.pivinadanang.entity;


import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
@Table (name = "category")
public class CategoryEntity extends AbstractEntity {

    @Column (name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "slug", nullable = false, length = 100)
    private String slug;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Date updateDate;


    public CategoryEntity (){

    }

    public CategoryEntity(Long id, String createdBy, String modifiedBy, String name, String slug, Date createDate, Date updateDate) {
        super(id, createdBy, modifiedBy);
        this.name = name;
        this.slug = slug;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void generateCategorySlug() {
        if (this.name == null || this.name.isEmpty()) {
            this.slug = "";
        } else {
            // Chuẩn hóa văn bản tiếng Việt và loại bỏ dấu thanh
            String normalized = Normalizer.normalize(this.name, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            String withoutAccents = pattern.matcher(normalized).replaceAll("");

            // Tạo slug từ văn bản đã được chuẩn hóa
            this.slug = withoutAccents
                    .toLowerCase() // Chuyển thành chữ thường
                    .replaceAll("[^a-z0-9\\s-]", "") // Loại bỏ các ký tự không mong muốn, giữ lại chữ, số, khoảng trắng, dấu gạch ngang
                    .replaceAll("\\s+", "-"); // Thay thế khoảng trắng bằng dấu gạch ngang
        }
    }
    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

    @PrePersist
    public void prePersist() {
        createDate = new Date();
    }

}
