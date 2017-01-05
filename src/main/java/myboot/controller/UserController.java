package myboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Map<String, Object> getUserList(HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("method", "getUserList");
		returnMap.put("data", userService.getUserList());
		return returnMap;
	}
	@RequestMapping(value = "/{userName}" ,method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserByName(@PathVariable("userName") String userName) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("method", "getUserByName");
		returnMap.put("data", userService.getUserByName(userName));
		return returnMap;
	}
}
