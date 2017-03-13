package dictionary;

import java.io.FileNotFoundException;

public class Tester {
	
	public static void main(String args[]) throws FileNotFoundException {
		Dictionary book100 = new Dictionary(100);
		System.out.println(book100.toString());
//		Dictionary book1000 = new Dictionary(1000);
//		Dictionary book2500 = new Dictionary(2500);
//		Dictionary book5000 = new Dictionary(5000);
//		Dictionary book10000 = new Dictionary(10000);
//		Dictionary bookAll = new Dictionary(-1);
	}
}
