package bitc.fullstack503.spring_project_blog.service;

import bitc.fullstack503.spring_project_blog.entity.MemberEntity;

public interface MemberService {

  void saveMember(MemberEntity member);

  boolean isMemberInfo(String id, String password) throws Exception;

  MemberEntity getMemberInfo(String userId) throws Exception;
}
