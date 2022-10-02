import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameTest {
	private Game game = new Game();

	@Test
	public void whenWordIsRecorded_thanLettersAreCheckedAsNotGuessed() {
		String enteredWord = "word";

		game.recordWord(enteredWord);

		int k = 0;
		for (Letter letter : game.getWord()) {
			assertEquals(enteredWord.charAt(k), letter.getLetter());
			assertFalse(letter.getGuessed());
			k++;
		}
	}

	@Test
	public void whenWordWithDoubleLettersIsRecorded_thanAllLettersAreAdded() {
		String enteredWord = "test";

		game.recordWord(enteredWord);

		int k = 0;
		for (Letter letter : game.getWord()) {
			assertEquals(enteredWord.charAt(k), letter.getLetter());
			assertFalse(letter.getGuessed());
			k++;
		}
	}

	@Test
	public void whenUppercaseLettersAreInWord_thenConvertedToLowercase() {
		String enteredWord = "wOrD";

		game.recordWord(enteredWord);

		int k = 0;
		for (Letter letter : game.getWord()) {
			assertEquals(enteredWord.toLowerCase().charAt(k), letter.getLetter());
			assertFalse(letter.getGuessed());
			k++;
		}
	}
}
