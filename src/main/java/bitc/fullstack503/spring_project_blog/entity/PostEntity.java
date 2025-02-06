package bitc.fullstack503.spring_project_blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class PostEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int postIdx;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(length = 10000, nullable = false)
  private String contents;

  @Column(nullable = false)
  @ColumnDefault("0")
  private int hitCnt;

  @Column(nullable = false)
  @ColumnDefault("0")
  private int likeCnt;

  @ManyToOne
  @JoinColumn(name = "id")
  private MemberEntity member;
}
