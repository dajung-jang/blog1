package bitc.fullstack503.spring_project_blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
@Setter

public class MemberEntity {

  @CreatedBy
  @Id
  @Column(length = 30, nullable = false)
  private String id;

  @Column(nullable = false, unique = true)
  private String password;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String address;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<PostEntity> postsList = new ArrayList<>();
}
