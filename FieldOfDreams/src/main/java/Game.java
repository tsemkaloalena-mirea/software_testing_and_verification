import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {
	private final List<Letter> word = new ArrayList<>();
	private Integer unguessedLettersLeft;
	private Integer attemptsLeft = 10;

	public Boolean play() {
		String enteredWord = ReadUtil.getEnteredWord();
		recordWord(enteredWord);
		System.out.println(showGuessedLetters());
		while (unguessedLettersLeft > 0 && attemptsLeft > 0) {
			System.out.println("You have " + attemptsLeft + " attempts left");
			Character enteredLetter = ReadUtil.getNextLetter();
			switch (guessLetter(enteredLetter)) {
				case SUCCESS:
					System.out.println("Congratulations! You guessed letter " + enteredLetter);
					break;
				case ALREADY_GUESSED:
					System.out.println("You already guessed letter " + enteredLetter);
					break;
				case FAIL:
					System.out.println("There is no " + enteredLetter + " in the hidden word");
					break;
			}
			System.out.println(showGuessedLetters() + "\n");
		}
		return unguessedLettersLeft == 0;
	}

	public void recordWord(String enteredWord) {
		for (Character character : enteredWord.toLowerCase().toCharArray()) {
			this.word.add(new Letter(character));
		}
		unguessedLettersLeft = this.word.size();
	}

	public GuessedStatus guessLetter(Character enteredLetter) {
		GuessedStatus status = GuessedStatus.FAIL;
		for (Letter letter : word) {
			if (enteredLetter.equals(letter.getLetter())) {
				if (letter.getGuessed()) {
					return GuessedStatus.ALREADY_GUESSED;
				}
				letter.checkGuessed();
				unguessedLettersLeft--;
				status = GuessedStatus.SUCCESS;
			}
		}
		if (GuessedStatus.FAIL.equals(status)) {
			attemptsLeft--;
		}
		return status;
	}

	public String showGuessedLetters() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Letter letter : this.word) {
			if (letter.getGuessed()) {
				stringBuilder.append(letter.getLetter());
			} else {
				stringBuilder.append("_");
			}
		}
		return stringBuilder.toString();
	}
}
