import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
	private Game game = new Game();
	private MockedStatic<ReadUtil> readUtility;

	@BeforeEach
	public void registerUtility() {
		readUtility = Mockito.mockStatic(ReadUtil.class);
	}

	@AfterEach
	public void deregisterUtility() {
		readUtility.close();
	}

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
		String enteredWord = "scanner";
		Character enteredLetter = 'a';
		Integer unguessedLettersLeft = enteredWord.length();
		Integer attemptsLeft = 10;

		game.recordWord(enteredWord);

		assertEquals(GuessedStatus.SUCCESS, game.guessLetter(enteredLetter));

		assertEquals(attemptsLeft, game.getAttemptsLeft());
		assertEquals(unguessedLettersLeft - 1, game.getUnguessedLettersLeft());
	}

	@Test
	public void whenDoubleLetterIsPresent_thanSuccessStatusReturned_and_attemptsNumberIsNotChanged_and_lettersQuantityDecreasedByTwo() {
		String enteredWord = "scanner";
		Character enteredLetter = 'n';
		Integer unguessedLettersLeft = enteredWord.length();
		Integer attemptsLeft = 10;

		game.recordWord(enteredWord);

		assertEquals(GuessedStatus.SUCCESS, game.guessLetter(enteredLetter));

		assertEquals(attemptsLeft, game.getAttemptsLeft());
		assertEquals(unguessedLettersLeft - 2, game.getUnguessedLettersLeft());
	}

	@Test
	public void whenLetterIsAbsent_thanFailStatusReturned_and_attemptsNumberIsDecreased_and_lettersQuantityIsNotChanged() {
		String enteredWord = "scanner";
		Character enteredLetter = 'l';
		Integer unguessedLettersLeft = enteredWord.length();
		Integer attemptsLeft = 10;

		game.recordWord(enteredWord);

		assertEquals(GuessedStatus.FAIL, game.guessLetter(enteredLetter));

		assertEquals(attemptsLeft - 1, game.getAttemptsLeft());
		assertEquals(unguessedLettersLeft, game.getUnguessedLettersLeft());
	}

	@Test
	public void whenLetterIsAlreadyGuessed_thenAlreadyGuessedStatusReturned_and_attemptsNumberIsNotChanged_and_lettersQuantityIsNotChanged() {
		String enteredWord = "scanner";
		Character enteredLetter = 'a';
		Integer unguessedLettersLeft = enteredWord.length();
		Integer attemptsLeft = 10;

		game.recordWord(enteredWord);
		game.guessLetter(enteredLetter);
		unguessedLettersLeft--;

		assertEquals(GuessedStatus.ALREADY_GUESSED, game.guessLetter(enteredLetter));

		assertEquals(attemptsLeft, game.getAttemptsLeft());
		assertEquals(unguessedLettersLeft, game.getUnguessedLettersLeft());
	}

	@Test
	public void whenLetterIsPresent_thenShowGuessedLettersWithNewOne() {
		String enteredWord = "scanner";
		game.recordWord(enteredWord);

		game.guessLetter('a');
		game.guessLetter('r');
		String result = game.showGuessedLetters();

		assertEquals("__a___r", result);
	}

	@Test
	public void whenDoubleLetterIsPresent_thenShowWithBothLetters() {
		String enteredWord = "scanner";
		game.recordWord(enteredWord);

		game.guessLetter('n');
		String result = game.showGuessedLetters();

		assertEquals("___nn__", result);
	}

	@Test
	public void whenLetterIsAbsent_thenShowResultIsNotChanged() {
		String enteredWord = "scanner";
		game.recordWord(enteredWord);

		game.guessLetter('d');
		String result = game.showGuessedLetters();

		assertEquals("_______", result);
	}

	@Test
	public void whenLetterIsAlreadyGuessed_thenShowResultIsNotChanged() {
		String enteredWord = "scanner";
		game.recordWord(enteredWord);

		game.guessLetter('a');
		String resultBefore = game.showGuessedLetters();
		game.guessLetter('a');
		String resultAfter = game.showGuessedLetters();

		assertEquals(resultBefore, resultAfter);
	}

	@Test
	public void whenAttemptsAreNotFinished_and_unguessedLettersLeftIsZero_thenTrueIsReturned() {
		List<Character> characters = new ArrayList<>();
		characters.add('c');
		characters.add('s');
		characters.add('f');
		characters.add('d');
		characters.add('a');
		characters.add('n');
		characters.add('e');
		characters.add('r');
		readUtility.when(ReadUtil::getEnteredWord).thenReturn("scanner");
		readUtility.when(ReadUtil::getNextLetter).then(invocationOnMock -> {
			Character character = characters.get(0);
			characters.remove(0);
			return character;
		});

		Boolean result = game.play();

		assertTrue(result);
	}

	@Test
	public void whenAttemptsAreFinished_and_unguessedLettersLeftIsZero_thenTrueIsReturned() {
		List<Character> characters = new ArrayList<>();
		characters.add('c');
		characters.add('s');
		for (int i = 0; i < 9; i++) {
			characters.add('f');
		}
		characters.add('a');
		characters.add('n');
		characters.add('e');
		characters.add('r');
		readUtility.when(ReadUtil::getEnteredWord).thenReturn("scanner");
		readUtility.when(ReadUtil::getNextLetter).then(invocationOnMock -> {
			Character character = characters.get(0);
			characters.remove(0);
			return character;
		});

		Boolean result = game.play();

		assertTrue(result);
	}

	@Test
	public void whenAttemptsAreFinished_and_unguessedLettersLeftIsMoreThenZero_thenFalseIsReturned() {
		List<Character> characters = new ArrayList<>();
		characters.add('c');
		characters.add('s');
		for (int i = 0; i < 10; i++) {
			characters.add('f');
		}
		readUtility.when(ReadUtil::getEnteredWord).thenReturn("scanner");
		readUtility.when(ReadUtil::getNextLetter).then(invocationOnMock -> {
			Character character = characters.get(0);
			characters.remove(0);
			return character;
		});

		Boolean result = game.play();

		assertFalse(result);
	}
}
