package main.java.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;	    
	}
	
	public static String readAlgorithmDescription(String filePath) throws IOException {
		
		String fileString = "";
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		
		try {
			StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    fileString = sb.toString();
		} finally {
		    br.close();
		}
		
		return fileString;
		
	}
	 
}
