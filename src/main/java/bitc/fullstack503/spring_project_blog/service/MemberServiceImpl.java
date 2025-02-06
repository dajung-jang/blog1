package bitc.fullstack503.spring_project_blog.service;

import bitc.fullstack503.spring_project_blog.entity.MemberEntity;
import bitc.fullstack503.spring_project_blog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;
//  private PasswordEncoder passwordEncoder;

//  사용자 존재 여부 확인
  @Override
  public boolean isMemberInfo(String id, String password) {
    return memberRepository.findByIdAndPassword(id, password).isPresent();
  }

//  사용자 정보 가져오기
  @Override
  public MemberEntity getMemberInfo(String id) {
    return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
  }

  @Override
  public void saveMember(MemberEntity member) {
    memberRepository.save(member);
  }
}
