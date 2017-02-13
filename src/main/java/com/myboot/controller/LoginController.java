package com.myboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myboot.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserList(HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("method", "getUserList");
		returnMap.put("data", userService.getUserList());
		return returnMap;
	}
}
