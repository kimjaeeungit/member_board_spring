package com.kimjaeeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kimjaeeun.service.BoardService;
import com.kimjaeeun.vo.Criteria;

import lombok.AllArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller 
@AllArgsConstructor
public class HomeController {
	private BoardService service;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("list", service.list(new Criteria(1,8,1)));
		return "/common/index";
	}
	
}
