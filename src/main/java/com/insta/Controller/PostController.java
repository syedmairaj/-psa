package com.insta.Controller;

import com.insta.Service.PCService;
import com.insta.payload.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/post")
public class PostController {

    private PCService pcservice;

    public PostController(PCService pcservice) {
        this.pcservice = pcservice;
    }
//post adding one post to postman
//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public PostDto addpost(@RequestBody PostDto postdto){
//        PostDto savedDto = pcservice.addpost(postdto);
//        return savedDto;
//    }

    //post - posting more than one post from postman

    @PostMapping()

    public List<PostDto> addpost2(@RequestBody List<PostDto> postdto){
       List<PostDto> dtopostdata = pcservice.addpost2(postdto);
       return dtopostdata;

    }

}
