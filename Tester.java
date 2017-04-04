import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
	
	private static Dictionary book;

	public static void cli() throws FileNotFoundException {
		boolean isUsing = true; // Sentinel for do while loop
		Scanner in = new Scanner(System.in); // scanner object for input
		String key, value, command; // to store user input
		
		System.out.println("---WORD DICTIONARY---\ntype 'help' for list of commands"); // title
		do {
			System.out.print("\n>>> "); // print cursor
			command = in.nextLine().toLowerCase().replaceAll(" ", ""); // clean the input
			if (command.equals("exit")) { // exit the cli
				isUsing = false;
			} 
			else if (command.equals("clear")) { // clear the screen
				clear(50);
			}
			else if (command.equals("help")) { // show help screen
				showHelp();
			}
			else if (command.equals("new")) { // create a new book
				System.out.print("Enter size of book: ");
				book = new Dictionary(Integer.valueOf(in.nextLine()));
			}
			else if (command.equals("test")) { // run test
				System.out.print("Enter size of test: ");
				runTests(in.nextLine());
			}
			else if (command.equals("search")) { // search for word
				if (book!=null) {
					System.out.print("Enter word to search: ");
					System.out.println(book.search(in.nextLine()));
				}
				else
					System.out.println("no book found");
			} 
			else if (command.equals("add")) { // add new word
				if (book!=null) {
					System.out.print("Enter word: ");
					key = in.nextLine();
					System.out.print("Enter definition: ");
					value = in.nextLine();
					book.add(key, value);
				}
				else
					System.out.println("no book found");
			} 
			else if (command.equals("edit")) { // edit existing word
				if (book!=null) {
					System.out.print("Enter word to edit: ");
					key = in.nextLine();
					System.out.print("Enter new definition: ");
					value = in.nextLine();
					book.edit(key, value);
				}
				else
					System.out.println("no book found");
			} 
			else if (command.equals("delete")) { // remove existing word
				if (book!=null) {
					System.out.print("Enter word to delete: ");
					book.delete(in.nextLine());
				}
				else
					System.out.println("no book found");
			} 
			else if (command.equals("view")) { // view raw dictionary
				if (book!=null)
					System.out.println(book.toString());
				else
					System.out.println("no book found");
			} 
			else if (command.equals("size")) { // # of words in dictionary
				if (book!=null)
					System.out.println("# of words: " + book.getWords());
				else
					System.out.println("no book found");
			} 
			else { // if command is not recognized
				System.out.println("error: command not found");
			}
		} while (isUsing); // loop as long as isUsing == true
		in.close(); // close scanner
		System.out.println("<terminated>");
	}
	
	public static void runTests(String s) throws FileNotFoundException {
		if (s.toLowerCase().replaceAll(" ", "").equals("all")) {
			book = new Dictionary("all");
			book.runTests(-1);               ///////////////////////// get this to work
		}
		else {
			int i = Integer.valueOf(s);
			if (i > 0) {
				book = new Dictionary(i);
				book.runTests(i);
			}
			else
				System.out.println("book size error");
		}
		
	}
	
	public static void showHelp() { // show list of cli commands
		System.out.println("\nThis is a basic command line interface for our dictionary. Commands are not case sensitive.");
		System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("| test   | prompts user to enter a size, creates an empty dictionary of that size and runs test exercises on it.                                   |");
		System.out.println("| new    | prompts user to enter a size, creates an empty dictionary of that size.                                                                 |");
		System.out.println("| add    | prompts user to enter a word and definition, if that word exists add to its list of definitions, else create a new entry for that word. |");
		System.out.println("| delete | prompts user to enter a word, if that word exists in the dictionary, removes that particular entry.                                     |");
		System.out.println("| edit   | prompts user to enter a word and definition, if that word exists in the dictionary, replace its definition.                             |");
		System.out.println("| search | prompts user to enter a word, if that word exists in the dictionary, show a list of its definitions.                                    |");
		System.out.println("| size   | shows the number of words in the dictionary.                                                                                            |");
		System.out.println("| view   | shows the whole dictionary, nulls included. *Very slow for large dictionary sizes.                                                      |");
		System.out.println("| help   | shows this help screen.                                                                                                                 |");
		System.out.println("| clear  | clears the command line interface.                                                                                                      |");
		System.out.println("| exit   | terminates the command line interface.                                                                                                  |");
		System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void clear(int n) { // clears the screen of 'n' lines
		for (int i=0; i<n; i++)
			System.out.println();
	}

	public static void main(String args[]) throws FileNotFoundException {
		cli(); // run the command line interface
	}
}
