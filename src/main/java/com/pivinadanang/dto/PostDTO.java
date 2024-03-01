package com.pivinadanang.dto;

import com.pivinadanang.entity.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDTO extends AbstractDTO<PostDTO> implements Serializable {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String postName;
    private String url;

    private CategoryDTO category;

    private PostImageDTO postImageDTO;
    private PostStatus status;
    private Long categoryId;
    private String categoryName;

    private Date createDate;
    private Date updateDate;

    private MultipartFile thumbnailFile;

    private List<CommentDTO> comments = new ArrayList<>();

    private Boolean isEdit = false;



    public PostDTO(Long id, String title, String content, String postName, Date createDate, Date updateDate, String createdBy,String modifiedBy,  PostStatus status, Long categoryId, String categoryName,String url) {
        super(id,createdBy,modifiedBy);
        this.title = title;
        this.content = content;
        this.postName = postName;
        this.url = url;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }


}
