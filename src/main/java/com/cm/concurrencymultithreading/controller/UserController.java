package com.cm.concurrencymultithreading.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.concurrencymultithreading.beans.User;
import com.cm.concurrencymultithreading.repository.UserRepository;
import com.cm.concurrencymultithreading.runnable.UserProcessor;
import com.cm.concurrencymultithreading.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public ResponseEntity<?> saveUser() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		List<String> userRecords = getUserRecord();
		userRecords.forEach(ur -> {
			Future<Integer> future = executor.submit(new UserProcessor(ur, userService));
			try {
				log.info("-----Future value {}", future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		executor.shutdown();
		return ResponseEntity.status(HttpStatus.CREATED).body(new ModelMap().addAttribute("msg", "Successfuly Created")
				.addAttribute("response", userRepository.findAll()));
	}

	public List<String> getUserRecord() {
		List<String> userList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"/home/gaian/Videos/fbAccessTokenVeification/concurrency-multithreading/src/main/resources/new_users.txt"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				log.info("-----File {}", line);
				userList.add(line);
			}
		} catch (final FileNotFoundException ex) {
			log.info("-----File not found. Exception {}", ex.getMessage());
		} catch (final IOException ex) {
			log.info("-----IOException. Exception {}", ex.getMessage());
		}
		return userList;
	}

	@PostMapping("/user")
	public ResponseEntity<?> persistData(@RequestBody @Valid User user) {
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	// Binding happens only on this controller
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
//		dateFormatter.setLenient(false);
//		CustomDateEditor editor = new CustomDateEditor(dateFormatter, false);
//		binder.registerCustomEditor(Date.class, editor);
//
//	}
}
