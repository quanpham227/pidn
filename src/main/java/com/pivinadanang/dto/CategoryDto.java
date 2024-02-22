package com.pivinadanang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto extends AbstractDto<CategoryDto> implements Serializable {
    private String name;

}
