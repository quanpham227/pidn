package com.pivinadanang.services;

import com.pivinadanang.dto.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
    Page<PostDTO> findByNameContainsIgnoreCase(String title, Pageable pageable);
    Page<PostDTO> findAllPostsPaginged(Pageable pageable);

    PostDTO findByPostId(Long id);
    PostDTO createPost(PostDTO productDTO);
    PostDTO updatePost(PostDTO productDTO);
    void deletePostById(Long id);
}
