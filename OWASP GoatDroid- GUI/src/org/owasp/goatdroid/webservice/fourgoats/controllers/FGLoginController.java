/**
 * OWASP GoatDroid Project
 * 
 * This file is part of the Open Web Application Security Project (OWASP)
 * GoatDroid project. For details, please see
 * https://www.owasp.org/index.php/Projects/OWASP_GoatDroid_Project
 *
 * Copyright (c) 2012 - The OWASP Foundation
 * 
 * GoatDroid is published by OWASP under the GPLv3 license. You should read and accept the
 * LICENSE before you use, modify, and/or redistribute this software.
 * 
 * @author Jack Mannino (Jack.Mannino@owasp.org https://www.owasp.org/index.php/User:Jack_Mannino)
 * @created 2012
 */
package org.owasp.goatdroid.webservice.fourgoats.controllers;

import org.owasp.goatdroid.webservice.fourgoats.model.LoginModel;
import org.owasp.goatdroid.webservice.fourgoats.services.FGLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "fourgoats/api/v1/pub/login", produces = "application/json")
public class FGLoginController {

	FGLoginServiceImpl loginService;

	@Autowired
	public FGLoginController(FGLoginServiceImpl loginService) {
		this.loginService = loginService;
	}

	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	@ResponseBody
	public String validateCredentials(
			@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "password", required = true) String password) {
		try {
			return "tomatoes";
			// return loginService.validateCredentials(userName, password);
		} catch (NullPointerException e) {
			LoginModel bean = new LoginModel();
			bean.setSuccess(false);
			// return bean;
			return "potatoes";
		}
	}

	@RequestMapping(value = "validate_api", method = RequestMethod.POST)
	@ResponseBody
	public LoginModel validateCredentialsAPI(
			@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "password", required = true) String password) {
		try {
			return loginService.validateCredentialsAPI(userName, password);
		} catch (NullPointerException e) {
			LoginModel bean = new LoginModel();
			bean.setSuccess(false);
			return bean;
		}
	}
}
