package com.utils;

import java.io.File;

public class FileUitls {

	
	public static String getFolderPath(String folderName) {
		String path = "reports/"+folderName;
		String filePath= "";
		File file = new File(path);
		
	     if(file.exists()) {
	    	 file.mkdir();
	    	 filePath = file.getAbsolutePath();
	     }
	     else
	    	 filePath = file.getAbsolutePath();
	     
	     return filePath;
	}
}
