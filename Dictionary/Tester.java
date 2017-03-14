package dictionary;

import java.io.FileNotFoundException;

public class Tester {
	
	public static void main(String args[]) throws FileNotFoundException {
		Dictionary book100 = new Dictionary(100);
		System.out.println(book100.toString());
//		Dictionary book1000 = new Dictionary(1000);
//		System.out.println(book1000.toString());
//		Dictionary book2500 = new Dictionary(2500);
//		System.out.println(book2500.toString());
//		Dictionary book5000 = new Dictionary(5000);
//		System.out.println(book5000.toString());
//		Dictionary book10000 = new Dictionary(10000);
//		System.out.println(book10000.toString());
//		Dictionary bookAll = new Dictionary(-1);
//		System.out.println(bookAll.toString());
	}
}
