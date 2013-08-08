package org.owasp.goatdroid.webservice.fourgoats.services;

import org.owasp.goatdroid.webservice.fourgoats.model.BaseModel;
import org.owasp.goatdroid.webservice.fourgoats.model.GetUsersAdmin;

public interface AdminService {

	public BaseModel deleteUser(String authToken, String userName);

	public BaseModel resetPassword(String authToken, String userName,
			String newPassword);

	public GetUsersAdmin getUsers(String authToken);

	public BaseModel signOut(String authToken);
}
