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

import java.sql.Date;
import java.util.ArrayList;
import org.owasp.goatdroid.webservice.herdfinancial.Constants;
import org.owasp.goatdroid.webservice.herdfinancial.Validators;
import org.owasp.goatdroid.webservice.herdfinancial.bean.StatementBean;
import org.owasp.goatdroid.webservice.herdfinancial.dao.StatementDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatementServiceImpl implements StatementService {

	StatementDaoImpl dao;

	@Autowired
	public StatementServiceImpl() {
		dao = new StatementDaoImpl();
	}

	public StatementBean getStatement(String accountNumber, String startDate,
			String endDate, int sessionToken) {

		ArrayList<String> errors = new ArrayList<String>();
		StatementBean bean = new StatementBean();
		LoginServiceImpl loginService = new LoginServiceImpl();
		if (!loginService.isSessionValid(sessionToken))
			errors.add(Constants.SESSION_EXPIRED);

		else if (!Validators.validateDateTimeFormat(startDate)
				|| !Validators.validateDateTimeFormat(endDate))
			errors.add(Constants.INVALID_ACCOUNT_NUMBER);

		else if (!Validators.validateAccountNumber(accountNumber))
			errors.add(Constants.INVALID_ACCOUNT_NUMBER);

		try {
			if (errors.size() == 0) {
				bean.setStatementData(dao.getStatement(accountNumber,
						convertStringToDate(startDate),
						convertStringToDate(endDate)));
				bean.setSuccess(true);
			}
		} catch (Exception e) {
			errors.add(Constants.UNEXPECTED_ERROR);
		} finally {
			bean.setErrors(errors);
			try {
			} catch (Exception e) {
			}
		}
		return bean;
	}

	public StatementBean getStatementSinceLastPoll(String accountNumber,
			int sessionToken) {

		ArrayList<String> errors = new ArrayList<String>();
		StatementBean bean = new StatementBean();
		LoginServiceImpl loginService = new LoginServiceImpl();
		if (!loginService.isSessionValid(sessionToken))
			errors.add(Constants.SESSION_EXPIRED);

		else if (!Validators.validateAccountNumber(accountNumber))
			errors.add(Constants.INVALID_ACCOUNT_NUMBER);

		try {
			if (errors.size() == 0) {
				long timeStamp = dao.getLastPollTime(accountNumber);
				bean.setStatementData(dao.getTransactionsSinceLastPoll(
						accountNumber, timeStamp));
				dao.updateLastPollTime(accountNumber);
				bean.setSuccess(true);
			}
		} catch (Exception e) {
			errors.add(Constants.UNEXPECTED_ERROR);
		} finally {
			bean.setErrors(errors);
		}
		return bean;
	}

	Date convertStringToDate(String dateString) {

		return Date.valueOf(dateString);
	}
}
