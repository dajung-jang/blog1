package bitc.fullstack503.spring_project_blog.controller;

import bitc.fullstack503.spring_project_blog.entity.PostEntity;
import bitc.fullstack503.spring_project_blog.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/JDJBlog")
public class PostController {

  @Autowired
  private PostService postService;

//  서버 작동 테스트
  @RequestMapping("/")
  public String index() {
    return "index";
  }

@RequestMapping("/header")
public String header() {
    return "/layout/header";
}

//  게시물 목록 (메인페이지)
  @GetMapping("/post")
  public ModelAndView selectPostList() throws Exception {
    ModelAndView mv = new ModelAndView("/page/postList");

    List<PostEntity> postList = postService.selectPostList();
    mv.addObject("postList", postList);

    return mv;
  }

//  게시물 상세보기
//  로그인 시에만 사용 가능
  @GetMapping("/post/{postIdx}")
  public ModelAndView selectPostDetail(@PathVariable("postIdx") int postIdx, HttpServletRequest request) throws Exception {

    HttpSession session = request.getSession();
    ModelAndView mv = new ModelAndView();

    if (session.getAttribute("id") == null) {
      mv.setViewName("redirect:/JDJBlog/login/login.do");
    }
    else {
      mv.setViewName("page/postDetail");
      PostEntity post = postService.selectPostDetail(postIdx);
      mv.addObject("post", post);
    }
    return mv;
  }

//  게시물 등록
  @GetMapping("/post/postWrite")
//  public String insertPost() {
//    return "page/postWrite";
//  }

  public String insertPost(HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();

    if(session.getAttribute("id") == null) {
      return "redirect:/JDJBlog/login/login.do";
    }
    else{
      return "page/postWrite";
    }
  }
  @PostMapping("/post/postWrite")
  public String insertPost(PostEntity post) {
    postService.savePost(post);

    return "redirect:/JDJBlog/post";
  }

//  게시물 수정
  @PutMapping("post/{postIdx}")
  public String updatePost(@PathVariable("postIdx") int postIdx, PostEntity post) {
    postService.savePost(post);

    return "redirect:/JDJBlog/post";
  }

//  게시물 삭제
  @DeleteMapping("post/{postIdx}")
  public String deletePost(@PathVariable("postIdx") int postIdx) {
    postService.deletePost(postIdx);

    return "redirect:/JDJBlog/post";
  }
}
