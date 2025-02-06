package bitc.fullstack503.spring_project_blog.service;

import bitc.fullstack503.spring_project_blog.entity.PostEntity;

import java.util.List;

public interface PostService {
  List<PostEntity> selectPostList();

  PostEntity selectPostDetail(int postIdx);

  void savePost(PostEntity post);

  void deletePost(int postIdx);
}
