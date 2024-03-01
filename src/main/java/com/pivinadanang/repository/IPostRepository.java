package com.pivinadanang.repository;

import com.pivinadanang.dto.PostDTO;
import com.pivinadanang.entity.PostEntity;
import com.pivinadanang.entity.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity, Long> {
    @Query("SELECT new com.pivinadanang.dto.PostDTO(p.id, p.title, p.content, p.postName, p.createDate, p.updateDate, p.createdBy, p.modifiedBy, p.status, c.id, c.name, pi.url) " +
            "FROM PostEntity p " +
            "JOIN p.category c " +
            "JOIN p.image pi " +
            "ORDER BY p.createDate DESC")
    Page<PostDTO> findByTitleContainsIgnoreCase(Pageable pageable);



    @Query("SELECT new com.pivinadanang.dto.PostDTO(p.id, p.title, p.content, p.postName, p.createDate, p.updateDate, p.createdBy, p.modifiedBy, p.status, c.id, c.name, pi.url) " +
            "FROM PostEntity p " +
            "JOIN p.category c " +
            "JOIN p.image pi " +
            "WHERE LOWER(p.title) LIKE LOWER(concat('%', :title, '%'))" +
            "ORDER BY p.createDate DESC")
    Page<PostDTO> findByTitleContainsIgnoreCase(@Param("title") String title, Pageable pageable);



    @Query("SELECT new com.pivinadanang.dto.PostDTO(p.id, p.title, p.content, p.postName, p.createDate, p.updateDate, p.createdBy, p.modifiedBy, p.status, c.id, c.name, pi.url)" +
            "FROM PostEntity p " +
            "JOIN p.category c " +
            "JOIN p.image pi " +
            "WHERE p.id = :postId")
    Optional<PostDTO> findByPostId(@Param("postId") Long id);
}
