package test;

import java.util.Iterator;
import java.util.Vector;

public class TestIterator {

	public static void main(String[] args) {

		Vector<String> test = new Vector<String>();
		test.add("0");
		test.add("1");
		test.add("2");
		Iterator<String> it = test.iterator();
		while (it.hasNext()) {
			String temp = it.next();
			if (temp.equals("1")) {
				it.remove();
			}
		}
		
		for(int i= 0; i< test.size() ; i++) {
			System.out.println(test.get(i));
		}
		for (String s : test) {
			System.out.println(s);
		}
	}

}
