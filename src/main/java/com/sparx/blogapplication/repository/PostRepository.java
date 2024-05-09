package com.sparx.blogapplication.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sparx.blogapplication.entities.Category;
import com.sparx.blogapplication.entities.Post;
import com.sparx.blogapplication.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
   List<Post> findByUser(User user);
   List<Post> findByCategory(Category category);
//   @Query("SELECT p FROM post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
//   List<Post> findByTitleContaining(@Param("key") String title);


}
