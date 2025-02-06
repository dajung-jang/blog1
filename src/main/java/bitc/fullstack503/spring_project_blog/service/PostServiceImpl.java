package bitc.fullstack503.spring_project_blog.service;

import bitc.fullstack503.spring_project_blog.entity.PostEntity;
import bitc.fullstack503.spring_project_blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

  @Autowired
  private PostRepository postRepository;

  @Override
  public List<PostEntity> selectPostList() {
    return postRepository.findAllByOrderByPostIdxDesc();
  }

  @Override
  public PostEntity selectPostDetail(int postIdx) {
    Optional<PostEntity> optPost = postRepository.findById(postIdx);

    if(optPost.isPresent()){
      PostEntity post = optPost.get();
      post.setHitCnt(post.getHitCnt() + 1);
      postRepository.save(post);

      return post;
    }
    else {
      throw new NullPointerException();
    }
  }

  @Override
  public void savePost(PostEntity post) {
    postRepository.save(post);
  }

  @Override
  public void deletePost(int postIdx) {
    postRepository.deleteById(postIdx);
  }
}
