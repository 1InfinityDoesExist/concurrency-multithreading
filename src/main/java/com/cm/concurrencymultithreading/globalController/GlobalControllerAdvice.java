package com.cm.concurrencymultithreading.globalController;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.cm.concurrencymultithreading.controller.UserController;

@ControllerAdvice(assignableTypes = { UserController.class })
public class GlobalControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		// dateFormatter.setLenient(false);
		CustomDateEditor editor = new CustomDateEditor(dateFormatter, false);
		binder.registerCustomEditor(Date.class, editor);

	}
}
