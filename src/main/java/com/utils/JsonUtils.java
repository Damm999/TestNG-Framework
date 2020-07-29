package com.utils;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtils {

	HashMap<String, String> testData;
	JSONObject json ;

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public JsonUtils(String testDataName) {
		readConfig();
		readTestData(testDataName);
	}

	public void readConfig() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Config.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
			setJson(jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getConfigData(String name) {
		return getJson().get(name).toString();
	}

	public void readTestData(String testDataName) {
		/*
		 * JSONParser parser = new JSONParser(); try { Object obj = parser.parse(new
		 * FileReader("./src/test/resources/Test_Data/"+testDataName+".json"));
		 * 
		 * // A JSON object. Key value pairs are unordered. JSONObject supports
		 * java.util.Map interface. JSONObject jsonObject = (JSONObject) obj;
		 * setJson(jsonObject); JSONArray companyList = (JSONArray)
		 * jsonObject.get("itemsList"); System.out.println(companyList.get(1));
		 * Iterator<JSONObject> iterator = companyList.iterator(); while
		 * (iterator.hasNext()) { System.out.println(iterator.next()); } } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
	}
}
