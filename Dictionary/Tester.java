import java.io.FileNotFoundException;
import java.util.Scanner;

class Tester {

    public static String getIn(String phrase) {
        System.out.print("Enter " + phrase + ": ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void clear(int n) {
        for (int i=0; i<n; i++)
            System.out.println();
    }

    public static void main(String args[]) {
        Dictionary book = new Dictionary("data.txt");
        Scanner in = new Scanner(System.in);
        String cmd, key, value;

        while(true) {
            System.out.println();
            cmd = getIn("command");

            if (cmd.equals("exit")) // exit
                System.exit(0);
            else if (cmd.equals("clear")) // clear screen
                clear(50);
            else if (cmd.equals("read")) // read all
                System.out.println(book.read());
            else if (cmd.equals("check")) // check specific word
                System.out.println(book.check(getIn("key")));
            else if (cmd.equals("add")) // add new word
                book.addEntry(getIn("key"), getIn("value"));
            else
                System.out.println(" <invalid> ");
        }
    }
}
