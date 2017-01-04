package myboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import myboot.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUser(HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("user", "yangyx");
		returnMap.put("data", userService.getUserList());
		return returnMap;
	}
}
