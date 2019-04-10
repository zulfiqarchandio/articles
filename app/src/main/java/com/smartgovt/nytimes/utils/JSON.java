package com.smartgovt.nytimes.utils;

import android.annotation.SuppressLint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

	public static String getStringValue(JSONObject obj, String key)
			throws JSONException {
		String val = "";
		if (obj.has(key)) {

			val = obj.getString(key);

			if (!JSON.isValidString(val)) {
				val = "";
			}
		}
		return val.trim();
	}

	public static int getIntValue(JSONObject obj, String key)
			throws JSONException {
		int val = 0;
		if (obj.has(key)) {
			String s = obj.getString(key);
			if (JSON.isValidString(s)) {
				try {
					if (!s.trim().equals(""))
						val = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return val;
	}

	public static float getFloatValue(JSONObject obj, String key)
			throws JSONException {
		float val = 0.0f;
		if (obj.has(key)) {
			String s = obj.getString(key);
			if (JSON.isValidString(s)) {
				val = Float.parseFloat(s);
			}
		}
		return val;
	}

	public static double getDoubleValue(JSONObject obj, String key)
			throws JSONException {
		double val = 0.0;
		if (obj.has(key)) {
			String s = obj.getString(key);
			if (JSON.isValidString(s)) {
				val = Double.parseDouble(s);
			}
		}
		return val;
	}

	public static boolean getBoolValue(JSONObject obj, String key)
			throws JSONException {
		boolean val = false;
		if (obj.has(key)) {
			String s = obj.getString(key);
			if (JSON.isValidString(s)) {
				val = Boolean.parseBoolean(s);
				if (s.equalsIgnoreCase("1"))
					val = true;
			}
		}
		return val;
	}

	public static JSONArray getJSONArray(JSONObject obj, String key)
			throws JSONException {
		if (obj.has(key)) {
			return obj.getJSONArray(key);
		}
		return new JSONArray();
	}

	@SuppressLint("NewApi")
	public static boolean isValidString(String s) {
		if (s.length() == 0 || s == null || s.isEmpty()
				|| s.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

}
