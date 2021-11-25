package com.kimjaeeun.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kimjaeeun.service.MemberService;
import com.kimjaeeun.vo.Member;

import lombok.AllArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller @AllArgsConstructor
public class MemberController {
	private MemberService service;
	
	//로그인 페이지
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	
	//로그인 하기
	@PostMapping("/login")
	public String login(Model model,String id,String pwd,HttpServletRequest req,HttpServletResponse resp) {
		String msg="";
		String redirectUrl = "login";
		Member member = new Member(id,pwd);
				if(service.login(member)) {
					HttpSession session = req.getSession();
					session.setAttribute("member", service.findBy(id));
					msg="성공";
					
					//아이디 저장
					Cookie cookie =new Cookie("savedId",id);
					cookie.setMaxAge(req.getParameter("savedId")==null?0:60*60*24*365);//1년
					resp.addCookie(cookie);
					
					//로그인 성공시 indxex.html로 넘어감
					redirectUrl="";
				}else {
					msg="로그인 실패";
			}
		model.addAttribute("msg",msg);
		return "redirect:/"+redirectUrl;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	//회원가입 페이지
	@GetMapping("/join")
	public String JoinForm() {
		return "member/join";
	}
	
	//회원가입
	@PostMapping("/join")
	public String join(Model model,String id,String pwd,String email,String name,HttpServletRequest req,HttpServletResponse resp) {
		Member member= new Member(id,pwd,email,name);
		service.join(member);
		return "member/login";
	}
	
	//회원정보 수정 페이지
	@GetMapping("/modify")
	public String modifyForm() {
		return "member/modify";
	}
	
	//회원정보 수정
	@PostMapping("/modify")
	public String modify(Model model,String id,String pwd,String email,String name,HttpServletRequest req,HttpServletResponse resp) {
		Member member= new Member(id,pwd,email,name);
		service.modify(member);
		return "redirect:/";
	}
	
	/*//회원탈퇴 페이지
	@GetMapping("/removeform")
	public String RemoveForm() {
		return "member/removeform";
	}
		*/
/*	//회원탈퇴
	@PostMapping("/modify")
	public String modify(Model model,String id,String pwd,String email,String name,HttpServletRequest req,HttpServletResponse resp) {
		Member member= new Member(id,pwd,email,name);
		service.modify(member);
		return "redirect:/";
	}*/
	

	
	
	
	
	
}

	
