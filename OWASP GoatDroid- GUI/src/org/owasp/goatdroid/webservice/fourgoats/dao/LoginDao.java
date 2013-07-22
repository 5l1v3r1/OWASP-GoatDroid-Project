package org.owasp.goatdroid.webservice.fourgoats.dao;

import java.sql.SQLException;
import java.util.HashMap;

public interface LoginDao {

	public boolean validateCredentials(String userName, String password)
			throws SQLException;

	public void updateSessionInformation(String userName, String sessionToken,
			long sessionStartTime) throws SQLException;

	public HashMap<String, Boolean> getPreferences(String userName)
			throws SQLException;

	public void terminateSession(String sessionToken) throws SQLException;

	public String getSessionToken(String userName) throws SQLException;
}
