package org.owasp.goatdroid.webservice.fourgoats.services;

import org.owasp.goatdroid.webservice.fourgoats.model.Login;

public interface LoginService {

	public Login validateCredentials(String userName, String password);

	public Login validateCredentialsAPI(String userName, String password);

}
