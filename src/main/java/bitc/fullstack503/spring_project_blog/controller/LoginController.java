package bitc.fullstack503.spring_project_blog.controller;

import bitc.fullstack503.spring_project_blog.entity.MemberEntity;
import bitc.fullstack503.spring_project_blog.entity.PostEntity;
import bitc.fullstack503.spring_project_blog.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;
import java.net.URLEncoder;

@Controller
@RequestMapping("/JDJBlog/login")
public class LoginController {

  @Autowired
  private MemberService memberService;

  @RequestMapping("/login.do")
  public String login(){
    return "page/login";
  }

//  로그인 처리
  @RequestMapping("/loginProcess.do")
  public String loginProcess(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request) throws Exception{
    boolean result = memberService.isMemberInfo(id, password);

    if (result == true) {
      MemberEntity member = memberService.getMemberInfo(id);

      HttpSession session = request.getSession();

      session.setAttribute("id", member.getId());
      session.setAttribute("name", member.getName());
      session.setAttribute("phone", member.getPhone());
      session.setAttribute("address", member.getAddress());
      session.setAttribute("email", member.getEmail());

      session.setMaxInactiveInterval(60 * 60 * 1);

      return "redirect:/JDJBlog/post";
    }
    else {
      return "redirect:/JDJBlog/login/login.do?errMsg=" + URLEncoder.encode("로그인 정보가 다릅니다.", "UTF-8");
    }
  }

//  로그아웃
  @RequestMapping("/logout.do")
  public String logout(HttpServletRequest request){
    HttpSession session = request.getSession();

    session.removeAttribute("id");
    session.removeAttribute("name");
    session.removeAttribute("phone");
    session.removeAttribute("address");
    session.removeAttribute("email");

    session.invalidate();

    return "redirect:/JDJBlog/post";
  }

//  회원가입
  @RequestMapping("/register.do")
  public String register(){
    return "page/register";
  }

  //  회원가입 처리
  @GetMapping("/registerProcess.do")
  public String insertRegister() {
    return "page/register";
  }

  @PostMapping("/registerProcess.do")
  public String insertRegister(MemberEntity member) {
    memberService.saveMember(member);

    return "redirect:/JDJBlog/post";
  }
}
