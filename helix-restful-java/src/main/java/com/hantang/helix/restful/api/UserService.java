package com.hantang.helix.restful.api;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hantang.helix.restful.model.User;
import com.hantang.helix.restful.utils.ValidationUtils;

@RestController
public class UserService {

	/**
	 * add user
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(path="/user",method=RequestMethod.POST)
	public User createUser(@RequestBody User user)  {
		
		// 创建用户，系统回随机生成一个唯一ID
		user.setUserId("21123123"); 
		
		// 数据验证 - 具体细节请看 validateUser 方法
		Map<String,String> validationResult = ValidationUtils.validateUser(user);
		
		if(null!=validationResult) { 
			// 这里会抛出异常 ， 这里只是简单抛出 Runtime 异常， 在项目中，我们会捕捉所有异常，然后把异常封装封装一个“数据返回对象” 。 
			validationResult.forEach(((key, value) -> {
				throw new RuntimeException(value);
		    }));
		}
		return user;
	}
	
}
