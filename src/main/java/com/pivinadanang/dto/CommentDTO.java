package com.pivinadanang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO extends AbstractDTO<CommentDTO> implements Serializable {
    private String content;
}
