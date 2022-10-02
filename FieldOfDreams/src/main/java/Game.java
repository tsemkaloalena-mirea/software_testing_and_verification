import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Game {
	@Getter
	private final List<Letter> word = new ArrayList<>();

	public void play() {

	}

	public void recordWord(String enteredWord) {
		for (Character character : enteredWord.toLowerCase().toCharArray()) {
			this.word.add(new Letter(character));
		}
	}
}
