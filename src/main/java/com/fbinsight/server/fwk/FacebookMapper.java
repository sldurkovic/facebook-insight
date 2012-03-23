package com.fbinsight.server.fwk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fbinsight.server.fwk.HttpManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FacebookMapper {

	private final String BASE_URL = "https://graph.facebook.com";
	private String ending;

	private HttpManager httpManager;

	public FacebookMapper(String token) {
		ending = "&access_token=" + token + "&format=json";
		httpManager = new HttpManager();
	}

	public JSONObject getFromContext(String userId, String context) {
		try {
			StringBuilder url = new StringBuilder();
			url.append(getBaseUserUrl(userId));
			if (context != null) {
				url.append("/" + context);
			}
			url.append(getEndingPars());
			String response = httpManager.executeGet(url.toString());
			return new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private JSONArray getDataFromContext(String userId, String context){
		return extractDataArray(getFromContext(userId, context));
	}

	private JSONArray extractDataArray(JSONObject json) {
		try {
			if (json.has("data")) {
				System.out.println(json.get("data"));
				return new JSONArray(json.get("data").toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getProfile(String userId) {
		return getFromContext(userId, null);
	}

	public JSONArray getLikes(String userId) {
		return getDataFromContext(userId, "likes");
	}

	public JSONArray getBooks(String userId) {
		return getDataFromContext(userId, "books");
	}

	public JSONArray getMovies(String userId) {
		return getDataFromContext(userId, "movies");
	}

	public JSONArray getFriends(String userId) {
		return getDataFromContext(userId, "friends");
	}

	public JSONArray getEvents(String userId) {
		return getDataFromContext(userId, "events");
	}

	public JSONArray getTelevision(String userId) {
		return getDataFromContext(userId, "television");
	}

	public JSONArray getMusic(String userId) {
		return getDataFromContext(userId, "music");
	}

	private String getBaseUserUrl(String userId) {
		return BASE_URL + "/" + userId;
	}

	private String getEndingPars() {
		return ending;
	}

}
