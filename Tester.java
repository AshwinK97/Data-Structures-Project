import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

	public static void cli(Dictionary book) {
		boolean isUsing = true;
		Scanner in = new Scanner(System.in);
		String key, value, command;

		do {
			System.out.print("\n_ ");
			command = in.nextLine().toLowerCase().replaceAll(" ", ""); // clean the input
			if (command.equals("exit")) {
				isUsing = false;
			} else if (command.equals("clear")) {
				clear(50);
			} else if (command.equals("search")) {
				System.out.print("Enter word to search: ");
				System.out.println(book.search(in.nextLine()));
			} else if (command.equals("add")) {
				System.out.print("Enter word: ");
				key = in.nextLine();
				System.out.print("Enter definition: ");
				value = in.nextLine();
				book.add(key, value);
			} else if (command.equals("edit")) {
				System.out.print("Enter word to edit: ");
				key = in.nextLine();
				System.out.print("Enter new definition: ");
				value = in.nextLine();
				book.edit(key, value);
			} else if (command.equals("delete")) {
				System.out.println("Enter word to delete: ");
				book.delete(in.nextLine());
			} else if (command.equals("view")) {
				System.out.println(book.toString());
			} else if (command.equals("size")) {
				System.out.println("# of words: " + book.getWords());
			} else {
				System.out.println("error: command not found");
			}
		} while (isUsing);
		in.close();
		System.out.println("exiting");
	}
	
	public static Dictionary runTests() throws FileNotFoundException {
		Dictionary book;
		book = new Dictionary(100);
//		System.out.println(book.toString());
		book = new Dictionary(1000);
//		System.out.println(book.toString());
		book = new Dictionary(2500);
//		System.out.println(book.toString());
		book = new Dictionary(5000);
//		System.out.println(book.toString());
		book = new Dictionary(10000);
//		System.out.println(book.toString());
//		book = new Dictionary("all");
//		System.out.println(bookAll.toString());
		return book;
	}
	
	public static void clear(int n) { // clears the screen of 'n' lines
		for (int i=0; i<n; i++)
			System.out.println();
	}

	public static void main(String args[]) throws FileNotFoundException {
		Dictionary book = runTests(); // return dictionary with all entries in it
		cli(book);
	}
}
