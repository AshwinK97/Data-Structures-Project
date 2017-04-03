import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

	public void useDictionary(Dictionary book) {
		boolean isUsing = true;
		Scanner in = new Scanner(System.in);
		String key, value, command;

		do {
			command = in.nextLine();
			if (command.toLowerCase().equals("exit")) {
				isUsing = false;
			} else if (command.toLowerCase().equals("search")) {
				System.out.print("Enter word to search: ");
				book.search(in.nextLine());
			} else if (command.toLowerCase().equals("add")) {
				System.out.print("Enter word: ");
				key = in.nextLine();
				System.out.println("Enter definition: ");
				value = in.nextLine();
				book.add(key, value);
			} else if (command.toLowerCase().equals("edit")) {
				System.out.println("Enter word to edit: ");
				key = in.nextLine();
				System.out.println("Enter new definition: ");
				value = in.nextLine();
				book.edit(key, value);
			} else if (command.toLowerCase().equals("delete")) {
				
			} else if (command.toLowerCase().equals("view")) {

			}
		} while (isUsing);
		in.close();
		System.out.println("exiting now ...");
	}

	public static void main(String args[]) throws FileNotFoundException {
//		Dictionary book100 = new Dictionary(100);
//		System.out.println(book100.toString());
		 Dictionary book1000 = new Dictionary(1000);
		 System.out.println(book1000.toString());
		// Dictionary book2500 = new Dictionary(2500);
		// System.out.println(book2500.toString());
		// Dictionary book5000 = new Dictionary(5000);
		// System.out.println(book5000.toString());
		// Dictionary book10000 = new Dictionary(100000);
		// System.out.println(book10000.toString());
		// Dictionary bookAll = new Dictionary("all");
		// System.out.println(bookAll.toString());
	}
}
