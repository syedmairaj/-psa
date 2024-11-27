package com.insta.Repository;

import com.insta.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
    //List<Comment> delByPostId(Long postId);
  //  List<Comment> deleteByPostId(Long postId, Comment comment);
}