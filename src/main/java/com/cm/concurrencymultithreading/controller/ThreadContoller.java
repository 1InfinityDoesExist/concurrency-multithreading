package com.cm.concurrencymultithreading.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.concurrencymultithreading.customImpl.CustomThreadFactory;
import com.cm.concurrencymultithreading.runnable.ApplicationThread;
import com.cm.concurrencymultithreading.runnable.CleaningSchedular;
import com.cm.concurrencymultithreading.runnable.LogginProcess;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/thread")
public class ThreadContoller {

	@GetMapping("/test1")
	public void testingThread1() {
		ApplicationThread apt1 = new ApplicationThread();
		ApplicationThread apt2 = new ApplicationThread();
		ApplicationThread apt3 = new ApplicationThread();
		apt1.start();
		apt2.start();
		apt3.start();
	}

	@GetMapping("/test2")
	public void testingThread2() {
		Thread t1 = new Thread(() -> {
			try (BufferedReader br = new BufferedReader(new FileReader(
					"/home/gaian/Videos/fbAccessTokenVeification/concurrency-multithreading/src/main/resources/sample.txt"))) {
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(Thread.currentThread().getName() + line);
				}
			} catch (final FileNotFoundException ex) {
				log.info("-----File not found. Exception {}", ex.getMessage());
			} catch (final IOException ex) {
				log.info("-----IOException. Exception {}", ex.getMessage());
			}
		});
		t1.start();
	}

	@GetMapping("/test3")
	public void testingThread3() {
		Runnable runnable = () -> {
			try (BufferedReader br = new BufferedReader(new FileReader(
					"/home/gaian/Videos/fbAccessTokenVeification/concurrency-multithreading/src/main/resources/sample.txt"))) {
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(Thread.currentThread().getName() + line);
				}
			} catch (final FileNotFoundException ex) {
				log.info("-----File not found. Exception {}", ex.getMessage());
			} catch (final IOException ex) {
				log.info("-----IOException. Exception {}", ex.getMessage());
			}
		};
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(runnable);

	}

	@GetMapping("/log")
	public void loggerr() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Callable<Boolean>> loggers = new ArrayList<>();
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		loggers.add(new LogginProcess());
		List<Future<Boolean>> futures = executor.invokeAll(loggers);
		for (Future<Boolean> future : futures) {
			System.out.println(future.get());
		}

		System.out.println(executor.invokeAny(loggers));
		executor.shutdown();
		System.out.println(executor.awaitTermination(30, TimeUnit.SECONDS)); // returns boolean

		// executor.shutdownNow(); // in catch block
	}

	@GetMapping("/logs")
	public ResponseEntity<?> deleteOlderLogs() {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		// executor.schedule(new CleaningSchedular(), 5, TimeUnit.SECONDS);
		// executor.scheduleAtFixedRate(new CleaningSchedular(), 5, 4,
		// TimeUnit.SECONDS);
		executor.scheduleWithFixedDelay(new CleaningSchedular(), 5, 5, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", "Files got deleted"));
	}

	@GetMapping("/logs/log")
	public void testingCustomThread() {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new CustomThreadFactory());
		executor.scheduleWithFixedDelay(new CleaningSchedular(), 5, 5, TimeUnit.SECONDS);
	}

}
