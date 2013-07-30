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

import org.owasp.goatdroid.webservice.fourgoats.Constants;
import org.owasp.goatdroid.webservice.fourgoats.bean.HistoryBean;
import org.owasp.goatdroid.webservice.fourgoats.services.FGHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "fourgoats/api/v1/priv/history", produces = "application/json")
public class FGHistoryController {

	FGHistoryServiceImpl historyService;

	@Autowired
	public FGHistoryController(FGHistoryServiceImpl historyService) {
		this.historyService = historyService;
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public HistoryBean getHistory(
			@RequestHeader(Constants.AUTH_TOKEN_HEADER) String sessionToken) {
		try {
			return historyService.getHistory(sessionToken);
		} catch (NullPointerException e) {
			HistoryBean bean = new HistoryBean();
			bean.setSuccess(false);
			return bean;
		}
	}

	@RequestMapping(value = "get_user_history/{userName}", method = RequestMethod.GET)
	@ResponseBody
	public HistoryBean getHistory(
			@RequestHeader(Constants.AUTH_TOKEN_HEADER) String sessionToken,
			@PathVariable(value = "userName") String userName) {
		try {
			return historyService.getUserHistory(sessionToken, userName);
		} catch (NullPointerException e) {
			HistoryBean bean = new HistoryBean();
			bean.setSuccess(false);
			return bean;
		}
	}
}
