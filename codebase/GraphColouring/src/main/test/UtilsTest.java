package main.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import main.java.utils.Utils;

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
	
	@Test
	public void isInteger() {
		Assert.assertEquals(Utils.isInteger("A"), false);
		Assert.assertEquals(Utils.isInteger(""), false);
		Assert.assertEquals(Utils.isInteger("01"), true);
		Assert.assertEquals(Utils.isInteger("1"), true);
	}
}
