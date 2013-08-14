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
 * @author Walter Tighzert
 * @created 2012
 */
package org.owasp.goatdroid.fourgoats.response;

import org.owasp.goatdroid.fourgoats.responseobjects.Comment;
import org.owasp.goatdroid.fourgoats.responseobjects.GenericResponseObject;
import org.owasp.goatdroid.fourgoats.responseobjects.ResponseObject;

public class CommentsResponse extends BaseResponse {

	static public Comment parseGetCommentsResponse(String response) {
		return (Comment) parseJsonResponse(response, Comment.class);
	}

	static public GenericResponseObject parseAddComment(String response) {
		return parseJsonResponse(response, GenericResponseObject.class);
	}

	static public ResponseObject parseRemoveComment(String response) {
		return parseJsonResponse(response, GenericResponseObject.class);
	}
}
