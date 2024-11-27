package com.insta.payload;

import com.insta.Entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class PostDto {


    private Long id;
    private String user;
    private String content;

//    private List<Comment> comments;
//
//    public PostDto(List<Comment> comments) {
//        this.comments = comments;
//    }
}
