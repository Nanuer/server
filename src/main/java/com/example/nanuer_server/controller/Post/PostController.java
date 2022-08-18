package com.example.nanuer_server.controller.Post;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.dto.Post.*;
import com.example.nanuer_server.service.PostService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.nanuer_server.config.BaseResponseStatus.*;

@Log4j2
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final PostService postService;

    @GetMapping("/test")
    public String testPostController() {
        return "success";
    }

    @GetMapping("/all")
    public BaseResponse<Map<String, List<PostEntity>>> getAllPosts() {
        try {
            Map<String, List<PostEntity>> response = new HashMap<String, List<PostEntity>>();
            response.put("postList", postService.getAllPosts());

            return new BaseResponse<>(response);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 게시물 리스트 조회 */
    @GetMapping("")
    @JsonIgnore
    public BaseResponse<Map<String, List<PostEntity>>> getPostList(@RequestParam int user_id, @RequestParam String query) {
        try {
            Map<String, List<PostEntity>> response = new HashMap<String, List<PostEntity>>();
            List<PostEntity> posts = postService.getPostList(user_id, query);
            response.put("postList", posts);

            return new BaseResponse<>(response);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 게시물 등록 */
    @PostMapping("")
    public BaseResponse<String> createPost(@RequestBody CreatePostReqDto createPostReqDto) {
        try {
            if(createPostReqDto.getTitle() == null || createPostReqDto.getTitle().length() > 200) {
                return new BaseResponse<>(POST_POST_INVALID_TITLE);
            }
            if(createPostReqDto.getContent() == null || createPostReqDto.getContent().length() > 1000) {
                return new BaseResponse<>(POST_POST_INVALID_CONTENT);
            }

            String result = "post_id = " + postService.createPost(createPostReqDto) + " 게시물 등록 성공";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 게시물 조회 */
    @GetMapping("/{post_id}")
    public BaseResponse<GetPostResDto> getPost(@PathVariable int post_id) {
        try {
            GetPostResDto post = postService.getPost(post_id);
            return new BaseResponse<>(post);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 게시물 수정 */
    @PutMapping("/{post_id}")
    public BaseResponse<String> updatePost(@PathVariable int post_id, @RequestBody UpdatePostReqDto updatePostReqDto) {
        try {
            String result = "post_id = " + postService.updatePost(post_id, updatePostReqDto) + " 수정 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    /* 게시물 삭제 */
    @PatchMapping("/{post_id}")
    public BaseResponse<String> deletePost(@PathVariable int post_id) {
        try {
            String result = "post_id = " + postService.deletePost(post_id) + " 삭제 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}