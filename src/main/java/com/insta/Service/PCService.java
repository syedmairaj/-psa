package com.insta.Service;

import com.insta.Entity.Comment;
import com.insta.Entity.Post;
import com.insta.Repository.CommentRepository;
import com.insta.Repository.PostRepository;
import com.insta.payload.CommentDto;
import com.insta.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PCService {

    @Autowired
    private ModelMapper mapper;

    private CommentRepository cRepo;
    private PostRepository pRepo;


    public PCService(PostRepository pRepo, CommentRepository cRepo) {
        this.pRepo = pRepo;
        this.cRepo = cRepo;


    }

    //post -add one post to postman
//    public PostDto addpost(PostDto postdto) {
//        Post postdata = PDtoToEntity(postdto);
//        Post postdbsaving = pRepo.save(postdata);
//        PostDto convEntity = EntityToPDto(postdbsaving);
//        return convEntity;
//
//    }
    //------------------------------------------------------------------
    //post - adding more than one post to postman

    public List<PostDto> addpost2(List<PostDto> postdto) {
        List<Post> postdata = postdto.stream().map(this::PDtoToEntity).collect(Collectors.toList());
        List<Post> reposaved = pRepo.saveAll(postdata);
        List<PostDto> pdtpdata = postdata.stream().map(this::EntityToPDto).collect(Collectors.toList());

        return pdtpdata;

    }


//comment -add
//    public CommentDto addcomments(CommentDto commentdto, Long postId) {
//        Post post = pRepo.findById(postId).orElseThrow(
//                ()-> new RuntimeException("ID not found" + postId));
//        Comment convertCData = CDtoToEntity(commentdto);
//        convertCData.setPost(post);
//        Comment saveddata = cRepo.save(convertCData);
//        CommentDto convertDto = EntityToCDto(saveddata);
//        return convertDto;
//
//    }

    //Post comments
    public CommentDto addcomment(CommentDto commentdto) {
        Long postId = commentdto.getPostId();
        Post post = pRepo.findById(postId).orElseThrow(
                () -> new RuntimeException("Id not found" + postId));
        commentdto.setPostId(postId);
        Comment comdata = CDtoToEntity(commentdto);
        Comment savdata = cRepo.save(comdata);
        CommentDto dtodata = EntityToCDto(savdata);
        return dtodata;
    }
//-------------------------------------------------------------------------------

    //getcomments

//    public CommentDto gdata(Long post_id) {
//
//        Comment cdatabase = cRepo.findById(post_id).orElseThrow(
//                ()->new RuntimeException("ID not found"+post_id));
//        CommentDto dtodata = EntityToCDto(cdatabase);
//        return dtodata;
//
//
//    }

    //Post -- PDtoToEntity
    public Post PDtoToEntity(PostDto postdto) {
        Post postmapper = mapper.map(postdto, Post.class);
        return postmapper;
    }

    //EntityToPDto
    public PostDto EntityToPDto(Post post) {
        PostDto postmapper2 = mapper.map(post, PostDto.class);
        return postmapper2;
    }


    //Comment -Mapper - CDtoToEntity
    public Comment CDtoToEntity(CommentDto commentdto) {
        Comment cdata = mapper.map(commentdto, Comment.class);
        return cdata;
    }

    //Comment -Mapper - EntityToCDto

    public CommentDto EntityToCDto(Comment comment) {
        CommentDto Edata = mapper.map(comment, CommentDto.class);
        return Edata;
    }
//get list of comments associated with post

    public List<CommentDto> gdata(Long postId) {
        List<Comment> cdata = cRepo.findByPostId(postId);
        if (cdata.isEmpty()) {
            throw new RuntimeException("Id is not found :" + postId);
        } else {
            List<CommentDto> saveddata = cdata.stream().map(this::EntityToCDto).collect(Collectors.toList());
            return saveddata;


        }
    }

    //del comments
    public List<Comment> delcomments(Long postId) {
        List<Comment> fdata = cRepo.findByPostId(postId);
        if(fdata.isEmpty()){
            return Collections.emptyList();
        }else{
           // cRepo.deleteAll(fdata);
            pRepo.deleteById(postId);
        } return fdata;



}



}