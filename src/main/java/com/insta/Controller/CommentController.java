package com.insta.Controller;

import com.insta.Entity.Comment;
import com.insta.Service.PCService;
import com.insta.payload.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vi/post/comments")
public class CommentController {

    private PCService pcservice;

    public CommentController(PCService pcservice) {
        this.pcservice = pcservice;
    }

    @PostMapping()
   @ResponseStatus(HttpStatus.CREATED)
//    public CommentDto addcomments(@RequestBody CommentDto commentdto, @PathVariable Long postId){
//        CommentDto commentdata = pcservice.addcomments(commentdto, postId);
//        return commentdata;
//    }

//    public CommentDto addcomments(@RequestBody CommentDto commentdto){
//        //Long postId = commentdto.getPostId();
//        CommentDto commentdata = pcservice.addcomment(commentdto);
//        return commentdata;
//    }

    public List<CommentDto> addcomments(@RequestBody List<CommentDto> commentdto){

       return commentdto.stream().map(x->pcservice.addcomment(x)
       ).collect(Collectors.toList());

    }

    @GetMapping("{post_id}")
    public List<CommentDto> gdata(@PathVariable Long post_id){
        List<CommentDto> dtodata = pcservice.gdata(post_id);
        System.out.println("1000");
        System.out.println("9000");
        System.out.println("paypal payment fixed");
        return dtodata;
    }



    //Del all comments by giving post id

    @DeleteMapping("{post_id}")
   // @ResponseStatus(HttpStatus.GONE)
    public List<Comment> delcomments(@PathVariable Long post_id){
        List<Comment> deldata = pcservice.delcomments(post_id);
        System.out.println("paypal configured");
        return deldata;
    }


}
