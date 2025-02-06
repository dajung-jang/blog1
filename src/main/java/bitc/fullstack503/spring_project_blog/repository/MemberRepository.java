package bitc.fullstack503.spring_project_blog.repository;

import bitc.fullstack503.spring_project_blog.entity.MemberEntity;
//import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.Optional;

//import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  Optional<MemberEntity> findByIdAndPassword(String id, String password);
  Optional<MemberEntity> findById(String id);
//  Optional<User> findByName(String name);
}
