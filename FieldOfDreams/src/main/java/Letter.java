import lombok.Getter;

@Getter
public class Letter {
	private final Character letter;
	private Boolean guessed;

	public Letter(Character letter) {
		this.letter = letter;
		this.guessed = false;
	}

	public void checkGuessed() {
		this.guessed = true;
	}

	@Override
	public String toString() {
		String guessedMessage;
		if (guessed) {
			guessedMessage = "guessed";
		} else {
			guessedMessage = "not guessed";
		}
		return letter + ": " + guessedMessage;
	}
}
