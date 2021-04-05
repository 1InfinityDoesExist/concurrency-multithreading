package com.cm.concurrencymultithreading.runnable;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;

import com.cm.concurrencymultithreading.beans.User;
import com.cm.concurrencymultithreading.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Autowired does not work in Callable<Integer>
 * 
 * @author 1518
 *
 */
@Slf4j
public class UserProcessor implements Callable<Integer> {

	private String userRecord;
	private UserService userService;

	public UserProcessor(String userRecord, UserService userService) {
		this.userRecord = userRecord;
		this.userService = userService;
	}

	@Override
	public Integer call() throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(userRecord, ",");
		int count = 0;
		User user = null;
		while (tokenizer.hasMoreTokens()) {
			System.out.println(Thread.currentThread().getName());
			count++;
			user = new User();
			user.setEmail(tokenizer.nextToken());
			user.setName(tokenizer.nextToken());
			user.setUserId(Integer.valueOf(tokenizer.nextToken()));
			userService.persistUser(user);
			log.info("User Info {} ", user.toString());
		}
		return count;
	}

}
