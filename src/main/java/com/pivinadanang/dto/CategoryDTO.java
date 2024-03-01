package com.pivinadanang.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO extends AbstractDTO<CategoryDTO> implements Serializable {
    @NotBlank(message = "category name is required")
    private String name;

    private  String slug;


    private Date createDate;

    private Date updateDate;
    private List<PostDTO> products = new ArrayList<>();
    private Boolean isEdit = false;

    public CategoryDTO(Long id, String createdBy, String modifiedBy, String name, String slug, Date createDate, Date updateDate) {
        super(id, createdBy, modifiedBy);
        this.name = name;
        this.slug = slug;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public CategoryDTO(Long id, String createdBy, String modifiedBy, String name, String slug,  Boolean isEdit) {
        super(id, createdBy, modifiedBy);
        this.name = name;
        this.slug = slug;
        this.isEdit = isEdit;
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

    public List<PostDTO> getProducts() {
        return products;
    }

    public void setProducts(List<PostDTO> products) {
        this.products = products;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }
}
