public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		if (game.play()) {
			System.out.println("You won!");
		} else {
			System.out.println("You failed :(");
		}
	}
}
