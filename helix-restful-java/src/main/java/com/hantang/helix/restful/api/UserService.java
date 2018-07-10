package com.hantang.helix.restful.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
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
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {

		// 创建用户，系统回随机生成一个唯一ID
		user.setUserId("21123123");

		// 数据验证 - 具体细节请看 validateUser 方法
		Map<String, String> validationResult = ValidationUtils.validateUser(user);

		if (null != validationResult) {
			// 这里会抛出异常 ， 这里只是简单抛出 Runtime 异常， 在项目中，我们会捕捉所有异常，然后把异常封装封装一个“数据返回对象” 。
			validationResult.forEach(((key, value) -> {
				throw new RuntimeException(value);
			}));
		}
		return user;
	}

	/**
	 * Get User information by User id , use HTTP Get method
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
	public User getUserBy(@PathVariable("userId") String userId) {

		User user = new User();
		user.setUserId(userId);
		return user;
	}

	/**
	 * 查询列表
	 *  1，这里用 User 对象来 获取查询条件，实际开发中，一个查询往往会有多个查询条件。 一般建议使用 一个对象获取条件。 但是这个使用的是
	 * HTTP 的 POST 方法。违背了 Rest 规则
	 *  2，通常涉及到分页，通常做法我们会自己些个分页数据对象，也可以使用 Pageable
	 * 这个对象来做分页数据获取（org.springframework.data.domain.Pageable） 。
	 * 用spring自带的pageable对象来得到分页信息,这个在 Spring Data 包下。
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(path = "/users", method = RequestMethod.POST)
	public List<User> queryUsers(@RequestBody User user) {

		System.out.println("Query Parameter " + user.getUserId());
		System.out.println("Query Parameter " + user.getUserName());
		User queryUser = new User();
		List<User> queryResult = new ArrayList<User>();

		queryUser.setUserId("");
		queryUser.setUserName("Allen2");
		queryResult.add(queryUser);

		queryUser.setAge(0);
		queryUser.setUserName("Allen1");
		queryResult.add(queryUser);
		return queryResult;
	}

	/**
	 * Delete User
	 *  Delete 有些时候会 涉及到逻辑删除
	 * @return
	 */
	@RequestMapping(path = "/user/{userId}", method = RequestMethod.DELETE)
	public User deleteUserByUserId(@PathVariable("userId") String userId) {
		User user = new User();

		return user;
	}

}
