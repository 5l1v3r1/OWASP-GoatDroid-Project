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

import java.util.ArrayList;
import java.util.HashMap;

import org.owasp.goatdroid.fourgoats.http.AuthenticatedRestClient;
import org.owasp.goatdroid.fourgoats.http.RequestMethod;
import org.owasp.goatdroid.fourgoats.misc.Utils;
import org.owasp.goatdroid.fourgoats.response.FriendResponse;

import android.content.Context;

public class FriendRequest {

	Context context;
	String destinationInfo;

	public FriendRequest(Context context) {

		destinationInfo = Utils.getDestinationInfo(context);
		this.context = context;
	}

	public HashMap<String, String> doFriendRequest(String userName)
			throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo
				+ "/fourgoats/api/v1/priv/friends/request-friend");
		client.AddParam("userName", userName);
		client.Execute(RequestMethod.POST, context);

		return FriendResponse.parseStatusAndErrors(client.getResponse());
	}

	public HashMap<String, String> getProfile(String userName) throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo
				+ "/fourgoats/api/v1/priv/friends/view_profile/" + userName);
		client.Execute(RequestMethod.GET, context);

		return FriendResponse.parseProfileResponse(client.getResponse());
	}

	public ArrayList<HashMap<String, String>> getPendingFriendRequests()
			throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo
				+ "/fourgoats/api/v1/priv/friends/get-pending-requests");
		client.Execute(RequestMethod.GET, context);

		return FriendResponse.parsePendingFriendRequestsResponse(client
				.getResponse());
	}

	public HashMap<String, String> acceptFriendRequest(String userName)
			throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo
				+ "/fourgoats/api/v1/priv/friends/accept-friend-request");
		client.AddParam("userName", userName);
		client.Execute(RequestMethod.POST, context);

		return FriendResponse.parseStatusAndErrors(client.getResponse());

	}

	public HashMap<String, String> removeFriendRequest(String userName)
			throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo
				+ "/fourgoats/api/v1/priv/friends/remove-friend");
		client.AddParam("userName", userName);
		client.Execute(RequestMethod.POST, context);

		return FriendResponse.parseStatusAndErrors(client.getResponse());

	}

	public HashMap<String, String> denyFriendRequest(String userName)
			throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo
				+ "/fourgoats/api/v1/priv/friends/deny-friend-request");
		client.AddParam("userName", userName);
		client.Execute(RequestMethod.POST, context);

		return FriendResponse.parseStatusAndErrors(client.getResponse());

	}

	public ArrayList<HashMap<String, String>> getFriends() throws Exception {

		AuthenticatedRestClient client = new AuthenticatedRestClient("https://"
				+ destinationInfo
				+ "/fourgoats/api/v1/priv/friends/list-friends");
		client.Execute(RequestMethod.GET, context);

		return FriendResponse.parseListFriendsResponse(client.getResponse());
	}
}
