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
package org.owasp.goatdroid.fourgoats.request;

import org.owasp.goatdroid.fourgoats.http.AuthenticatedRestClient;
import org.owasp.goatdroid.fourgoats.http.RequestMethod;
import org.owasp.goatdroid.fourgoats.misc.Utils;
import org.owasp.goatdroid.fourgoats.response.CommentsResponse;
import org.owasp.goatdroid.fourgoats.responseobjects.Comment;
import org.owasp.goatdroid.fourgoats.responseobjects.GenericResponseObject;
import org.owasp.goatdroid.fourgoats.responseobjects.ResponseObject;

import android.content.Context;

public class CommentsRequest {

	Context context;
	String destinationInfo;

	public CommentsRequest(Context context) {

		this.context = context;
		destinationInfo = Utils.getDestinationInfo(context);
	}

	public Comment getComments(String checkinID) throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo + "/fourgoats/api/v1/priv/comments/get");
		client.AddParam("checkinID", checkinID);
		client.Execute(RequestMethod.GET, context);

		return CommentsResponse.parseGetCommentsResponse(client.getResponse());
	}

	public GenericResponseObject addComment(String comment, String checkinID)
			throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo + "/fourgoats/api/v1/priv/comments/add");
		client.AddParam("comment", comment);
		client.AddParam("checkinID", checkinID);
		client.Execute(RequestMethod.POST, context);

		return CommentsResponse.parseAddComment(client.getResponse());
	}

	public ResponseObject removeComment(String commentID)
			throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo + "/fourgoats/api/v1/priv/comments/remove");
		client.AddParam("commentID", commentID);
		client.Execute(RequestMethod.POST, context);

		return CommentsResponse.parseAddComment(client.getResponse());
	}
}
