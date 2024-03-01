package com.pivinadanang.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostImageDTO extends AbstractDTO<PostImageDTO>{

    private String publicId;

    private String fileName;

    private String url;

}
