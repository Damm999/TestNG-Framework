package com.utils;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtils {

	private JSONObject configJsonObject;
	private JSONObject testDataJsonObject;

	
	/** 
	 * @return JSONObject
	 */
	public JSONObject getConfigJsonObject() {
		return configJsonObject;
	}

	
	/** 
	 * @param configJsonObject
	 */
	public void setConfigJsonObject(JSONObject configJsonObject) {
		this.configJsonObject = configJsonObject;
	}

	
	/** 
	 * @return JSONObject
	 */
	public JSONObject getTestDataJsonObject() {
		return testDataJsonObject;
	}

	
	/** 
	 * @param testDataJsonObject
	 */
	public void setTestDataJsonObject(JSONObject testDataJsonObject) {
		this.testDataJsonObject = testDataJsonObject;
	}

	/**
	 * Constructor
	 * @param testDataName
	 */
	public JsonUtils(String testDataName) {
		readConfig();
		readTestData(testDataName);
	}

	/**
	 * Read Execution Config
	 */
	private void readConfig() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Config.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
			setConfigJsonObject(jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/** 
	 * @param name
	 * @return String
	 */
	public String getConfigData(String name) {
		return getConfigJsonObject().get(name).toString();
	}

	
	/** 
	 * @param testDataName
	 * @return JSONObject
	 */
	private void readTestData(String testDataName)  {
		JSONParser parser = new JSONParser(); 
		try { 
			JSONObject testDataObj = (JSONObject) parser.parse(new FileReader("./src/test/resources/Test_Data/"+testDataName+".json"));
			setTestDataJsonObject(testDataObj);
		}catch (IOException io) {
			System.err.println("Error while opening file");
			io.printStackTrace();
		} catch (ParseException ps) {
			System.err.println("Error while Parsing json  file");
			ps.printStackTrace();
		}
	}
}
