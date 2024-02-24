package com.pivinadanang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO extends AbstractDTO<CategoryDTO> implements Serializable {
    private String name;
    private List<PostDTO> products = new ArrayList<>();

    private Boolean isEdit = false;

    public CategoryDTO(String name) {
        this.name = name;
    }
    public CategoryDTO(Long id, String name) {
        super(id);
        this.name = name;
    }
    public CategoryDTO(Long id, String name, Boolean isEdit) {
        super(id);
        this.name = name;
        this.isEdit = isEdit;
    }
}
