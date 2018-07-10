package com.hantang.helix.restful.utils;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.internal.Maps;

import com.hantang.helix.restful.model.User;

public class ValidationUtils {
	/**
	 * 这是一个样例，实践开发中数据验证是必备可少的， 可以使用开源的验证框架例如 Hibernate的，也可以自己写正则去做。
	 * 另外，表单验证往往与国际化有关系，返回错误信息 需要国际化。
	 * 同时，验证也有异常处理有关系， 有些系统会定义错误码；
	 * 通常 我们额会封装一个 验证结果的 Java 对象，这里用了 HashMap 去做的。
	 * @param user
	 * @return
	 */
	public static Map<String, String> validateUser(User user) {
		Map<String, String> result = null;
		if(null!=user) {
			result = new HashMap<String, String>();
			if(null== user.getUserName() ||  user.getUserName()=="" || user.getUserName().equals("")) {
				result.put("00001", "User Name is empty");
			}
		}else {
			result = new HashMap<String, String>();
			result.put("00000", "Please input User Information");
		}
		
		return result;	
	}
}


