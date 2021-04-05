package com.cm.concurrencymultithreading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.concurrencymultithreading.beans.User;
import com.cm.concurrencymultithreading.repository.UserRepository;
import com.cm.concurrencymultithreading.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void persistUser(User user) {
		log.info("----User in db {}", userRepository.save(user));

	}

}
