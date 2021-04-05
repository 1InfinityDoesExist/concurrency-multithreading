package com.cm.concurrencymultithreading.service;

import org.springframework.stereotype.Service;

import com.cm.concurrencymultithreading.beans.User;

@Service
public interface UserService {
	public void persistUser(User user);
}
