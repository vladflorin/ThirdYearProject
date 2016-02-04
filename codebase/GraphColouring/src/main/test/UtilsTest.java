package main.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void TestList() {
		List<Integer> list = new ArrayList<Integer>();
		
		System.out.println(list.toString());
		
		list.add(1);
		list.add(2);
		list.add(3);
				
		System.out.println(list.toString());

		list.remove(list.size() - 1);
		
		System.out.println(list.toString());

		list.add(0, 0);
		
		System.out.println(list.toString());
		
		list.remove(0);
		
		System.out.println(list.toString());
	}
}
