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
package org.owasp.goatdroid.webservice.herdfinancial.services;

import java.util.ArrayList;
import org.owasp.goatdroid.webservice.herdfinancial.Constants;
import org.owasp.goatdroid.webservice.herdfinancial.Validators;
import org.owasp.goatdroid.webservice.herdfinancial.bean.AuthorizeBean;
import org.owasp.goatdroid.webservice.herdfinancial.dao.AuthorizeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

	AuthorizeDaoImpl dao;

	@Autowired
	public AuthorizeServiceImpl() {
		dao = new AuthorizeDaoImpl();
	}

	public AuthorizeBean authorizeDevice(String deviceID, int sessionToken) {

		AuthorizeBean bean = new AuthorizeBean();
		ArrayList<String> errors = new ArrayList<String>();
		LoginServiceImpl loginService = new LoginServiceImpl();
		if (!loginService.isSessionValid(sessionToken))
			errors.add(Constants.SESSION_EXPIRED);
		else if (!Validators.validateDeviceID(deviceID))
			errors.add(Constants.INVALID_DEVICE_ID);

		try {
			if (!dao.isDeviceAuthorized(deviceID)) {
				dao.authorizeDevice(deviceID, sessionToken);
				bean.setSuccess(true);
			} else
				errors.add(Constants.DEVICE_ALREADY_AUTHORIZED);
		} catch (Exception e) {
			errors.add(Constants.UNEXPECTED_ERROR);
		} finally {
			bean.setErrors(errors);
		}
		return bean;
	}
}
