package com.pivinadanang.services.impl;

import com.pivinadanang.dto.CloudinaryDTO;
import com.pivinadanang.dto.PostDTO;
import com.pivinadanang.exception.NotFoundException;
import com.pivinadanang.repository.IPostRepository;
import com.pivinadanang.services.ICloudinaryService;
import com.pivinadanang.services.IPostService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private ICloudinaryService cloudinaryService;

    @Override
    public Page<PostDTO> findByNameContainsIgnoreCase(String title, Pageable pageable) {
        return postRepository.findByTitleContainsIgnoreCase(title, pageable);
    }

    @Override
    public Page<PostDTO> findAllPostsPaginged(Pageable pageable) {
        return postRepository.findByTitleContainsIgnoreCase(pageable);
    }

    @Override
    public PostDTO findByPostId(Long id) {
        PostDTO postDTO = postRepository.findByPostId(id).
                orElseThrow(() -> new NotFoundException("Canot find post with  "+ id));
        return postDTO;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PostDTO createPost(PostDTO postDTO) {
        String folderName = "posts_thumbnail";
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(postDTO.getThumbnailFile().getOriginalFilename());
        CloudinaryDTO cloudinaryDTO = cloudinaryService.upload(postDTO.getThumbnailFile(), folderName);


        return  null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PostDTO updatePost(PostDTO productDTO) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePostById(Long id) {

    }
}
