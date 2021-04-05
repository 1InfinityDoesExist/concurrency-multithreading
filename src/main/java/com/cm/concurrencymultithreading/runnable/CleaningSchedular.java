package com.cm.concurrencymultithreading.runnable;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CleaningSchedular implements Runnable {

	@Override
	public void run() {
		log.info("-----Logging-----" + Thread.currentThread().getName());
		File file = new File("/home/gaian/logs");
		File[] listOfFiles = file.listFiles();
		for (File f : listOfFiles) {
			if (System.currentTimeMillis() - f.lastModified() > 5 * 60 * 1000) {
				log.info("-----About to delete {}", f.getName());
				f.delete();
			}
		}

	}

}
