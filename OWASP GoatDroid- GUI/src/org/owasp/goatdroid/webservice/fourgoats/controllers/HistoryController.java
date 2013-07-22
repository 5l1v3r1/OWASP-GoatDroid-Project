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

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.owasp.goatdroid.webservice.fourgoats.Constants;
import org.owasp.goatdroid.webservice.fourgoats.bean.HistoryBean;
import org.owasp.goatdroid.webservice.fourgoats.services.HistoryServiceImpl;

@Controller
@Path("/fourgoats/api/v1/history")
public class HistoryController {

	@Path("list")
	@GET
	@Produces("application/json")
	public HistoryBean getHistory(
			@CookieParam(Constants.SESSION_TOKEN_NAME) String sessionToken) {
		try {
			return HistoryServiceImpl.getHistory(sessionToken);
		} catch (NullPointerException e) {
			HistoryBean bean = new HistoryBean();
			bean.setSuccess(false);
			return bean;
		}
	}

	@Path("get_user_history/{userName}")
	@GET
	@Produces("application/json")
	public HistoryBean getHistory(
			@CookieParam(Constants.SESSION_TOKEN_NAME) String sessionToken,
			@PathParam("userName") String userName) {
		try {
			return HistoryServiceImpl.getUserHistory(sessionToken, userName);
		} catch (NullPointerException e) {
			HistoryBean bean = new HistoryBean();
			bean.setSuccess(false);
			return bean;
		}
	}
}
