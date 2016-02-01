package main.java.utils;

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
	
	public static boolean printOK(int size) {
		for (int index = 0; index <= size; index++) {
			System.out.println(index);
		}
		return true;
	}
	
	public static boolean printNOK(int size) {
		for (int index = 0; index <= size; index++) {
			System.out.println(index);
		}
		return false;
	}
	 
}
