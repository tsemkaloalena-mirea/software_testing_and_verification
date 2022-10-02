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
		assertEquals(enteredWord.length(), game.getUnguessedLettersLeft());
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
		assertEquals(enteredWord.length(), game.getUnguessedLettersLeft());
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
		assertEquals(enteredWord.length(), game.getUnguessedLettersLeft());
	}

	@Test
	public void whenLetterIsPresent_thanSuccessStatusReturned_and_attemptsNumberIsNotChanged_and_lettersQuantityDecreasedByOne() {

	}

	@Test
	public void whenDoubleLetterIsPresent_thanSuccessStatusReturned_and_attemptsNumberIsNotChanged_and_lettersQuantityDecreasedByTwo() {

	}

	@Test
	public void whenLetterIsAbsent_thanFailStatusReturned_and_attemptsNumberIsSmaller_and_lettersQuantityIsNotChanged() {

	}

	@Test
	public void whenLetterIsAlreadyGuessed_thenAlreadyGuessedStatusReturned_and_attemptsNumberIsNotChanged_and_lettersQuantityIsNotChanged() {

	}
}
