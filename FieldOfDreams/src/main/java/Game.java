import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {
	private final List<Letter> word = new ArrayList<>();
	private Integer unguessedLettersLeft;
	private Integer attemptsLeft = 10;

	public void play() {

	}

	public void recordWord(String enteredWord) {
		for (Character character : enteredWord.toLowerCase().toCharArray()) {
			this.word.add(new Letter(character));
		}
		unguessedLettersLeft = this.word.size();
	}

	public GuessedStatus guessLetter(Character enteredLetter) {
		GuessedStatus status = GuessedStatus.FAIL;

		return status;
	}
}
