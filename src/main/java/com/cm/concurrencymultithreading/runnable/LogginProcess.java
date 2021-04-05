package com.cm.concurrencymultithreading.runnable;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogginProcess implements Callable<Boolean> {

	@Override
	public Boolean call() throws Exception {
		Logger.getLogger(LogginProcess.class.getName()).log(Level.INFO, "Just logging for testing purpose");
		return true;
	}

}
