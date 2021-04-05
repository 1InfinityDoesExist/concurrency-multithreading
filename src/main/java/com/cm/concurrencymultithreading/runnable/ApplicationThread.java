package com.cm.concurrencymultithreading.runnable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 1518
 *
 */
@Slf4j
public class ApplicationThread extends Thread {
	@Override
	public void run() {
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
	}
}
