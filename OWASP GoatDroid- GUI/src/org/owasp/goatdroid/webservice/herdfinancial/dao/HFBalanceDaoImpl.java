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
package org.owasp.goatdroid.webservice.herdfinancial.dao;

import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class HFBalanceDaoImpl extends BaseDaoImpl implements BalanceDao {

	@Autowired
	public HFBalanceDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public HashMap<String, Double> getBalances(String accountNumber)
			throws SQLException {
		HashMap<String, Double> result = new HashMap<String, Double>();
		String sql = "SELECT checkingBalance, savingsBalance FROM app.hf_users WHERE accountNumber = ?";
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, accountNumber);
		if (rs.next()) {
			result.put("checking", rs.getDouble("checkingBalance"));
			result.put("savings", rs.getDouble("savingsBalance"));
			return result;
		} else
			return result;
	}
}
