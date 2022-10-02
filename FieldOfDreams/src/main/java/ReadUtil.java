import java.util.Scanner;

public class ReadUtil {
	private static final Scanner scanner = new Scanner(System.in);

	public static Character getNextLetter() {
		System.out.print("Enter a letter: ");
		return scanner.next().charAt(0);
	}

	public static String getEnteredWord() {
		System.out.print("What word did you puzzle out? ");
		return scanner.nextLine();
	}
}
