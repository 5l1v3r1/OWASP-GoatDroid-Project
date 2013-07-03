package org.owasp.goatdroid.fourgoats.phonegap.plugins;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs extends CordovaPlugin {

	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {

		if (action.equals("getCredentials")) {

			return true;
		} else if (action.equals("saveCredentials")) {

			return true;
		} else if (action.equals("getDestinationInfo")) {
			callbackContext.sendPluginResult(getDestinationInfo());
			return true;
		} else if (action.equals("getProxyInfo")) {

			return true;
		} else if (action.equals("writeDestinationInfo")) {

			return true;
		} else if (action.equals("writeProxyInfo")) {

			return true;
		}
		return false;
	}

	PluginResult getDestinationInfo() {

		SharedPreferences prefs = this.cordova.getActivity()
				.getSharedPreferences("destination_info",
						Context.MODE_WORLD_READABLE);

		Map destinationInfo = new HashMap<String, String>();
		destinationInfo.put("host", prefs.getString("host", ""));
		destinationInfo.put("port", prefs.getString("port", ""));
		return new PluginResult(Status.OK,
				new JSONObject(destinationInfo).toString());
	}

	void setDestinationInfo(String host, String port) {

		SharedPreferences destinationInfo = this.cordova.getActivity()
				.getSharedPreferences("destination_info",
						Context.MODE_WORLD_READABLE);
		SharedPreferences.Editor editor = destinationInfo.edit();
		editor.putString("host", host);
		editor.putString("port", port);
		editor.commit();
	}

	PluginResult getProxyInfo() {

		Map<String, String> map = new HashMap<String, String>();
		SharedPreferences prefs = this.cordova
				.getActivity()
				.getSharedPreferences("proxy_info", Context.MODE_WORLD_READABLE);
		map.put("proxyHost", prefs.getString("proxyHost", ""));
		map.put("proxyPort", prefs.getString("proxyPort", ""));
		return new PluginResult(Status.OK, new JSONObject(map));

	}

	void setProxyInfo(String host, String port) {

		SharedPreferences destinationInfo = this.cordova
				.getActivity()
				.getSharedPreferences("proxy_info", Context.MODE_WORLD_READABLE);
		SharedPreferences.Editor editor = destinationInfo.edit();
		editor.putString("proxyHost", host);
		editor.putString("proxyPort", port);
		editor.commit();
	}
}
