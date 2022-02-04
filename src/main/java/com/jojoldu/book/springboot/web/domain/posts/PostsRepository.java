package com.jojoldu.book.springboot.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("Select p From Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
