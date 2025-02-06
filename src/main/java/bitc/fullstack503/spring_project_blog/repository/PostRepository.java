package bitc.fullstack503.spring_project_blog.repository;

import bitc.fullstack503.spring_project_blog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
  List<PostEntity> findAllByOrderByPostIdxDesc();

}
