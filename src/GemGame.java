import java.awt.Font;
import java.util.Scanner;

public class GemGame 
{
	private static GemList player1;
	private static GemList computer;

	// The maximum number of gems that will fit across the screen
	private static final int MAX_GEMS = 16;
	
	// Given a gem's 0-based index, calculate its x-position
	// in a unit box coordinate system. 
	static double indexToX(int i)
	{
		return (0.5 + i) * (1.0 / MAX_GEMS);
	}

	// Given a mouse x-position, figure out what gem index
	// we should insert before.
	private static int xToIndex(double x)
		{
			return (int) ((x + 0.5 / MAX_GEMS) / (1.0 / MAX_GEMS));
		}

	public static void main(String [] args)
	{
		// Constants that define how we draw various things on the screen
		final double PLAYER1_Y 			= 0.7;
		final double PLAYER2_Y 			= 0.3;
		final double COMPUTER_Y			= 0.3;
		final double PLAYER_HALF_HEIGHT = 0.15;
		final double TEXT_HEIGHT 		= 0.05;
		final double SCORE_Y 			= 0.95;
		final double INDICATOR_X 		= -0.025;
		final double INDICATOR_RADIUS 	= 0.01;


		Scanner kb = new Scanner(System.in);

		System.out.println("Would you like to play the game against a friend or against me");
		System.out.println("If you would like to play against me press C and enter");
		System.out.println("If you would like to play against another person press P and enter");

		String choice = kb.nextLine();

		while (!choice.equals("P") && !choice.equals("C")){
			System.out.println("Please choose again.");
			choice = kb.nextLine();
		}

		if(choice.equals("P")) {

			// Create a List ADT for each of the two players
			player1 = new GemList();
			GemList player2 = new GemList();

			Gem current = null;
			boolean mouseDown = false;
			boolean turn1 = true;

			int score1 = 0;
			int score2 = 0;

			// Game continues until we fill up the entire row of gems
			while ((player1.size() < MAX_GEMS) && (player2.size() < MAX_GEMS)) {
				StdDraw.clear();
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

				if (current == null)
					current = new Gem();

				// Check for a click of the mouse, wait for release of the button
				if (StdDraw.mousePressed()) {
					mouseDown = true;
				} else if (mouseDown) {
					// See if they clicked in the Player1 area
					if ((mouseY > PLAYER1_Y - PLAYER_HALF_HEIGHT) && (mouseY < PLAYER1_Y + PLAYER_HALF_HEIGHT)) {
						player1.insertBefore(current, xToIndex(mouseX));
						current = null;
						turn1 = !turn1;
					}
					// See if they clicked in the Player2 area
					else if ((mouseY > PLAYER2_Y - PLAYER_HALF_HEIGHT) && (mouseY < PLAYER2_Y + PLAYER_HALF_HEIGHT)) {
						player2.insertBefore(current, xToIndex(mouseX));
						current = null;
						turn1 = !turn1;
					}
					// Regardless, this click is over even if we didn't drop the gem
					mouseDown = false;
				}

				score1 = player1.score();
				score2 = player2.score();

				// Background to show each player
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledRectangle(0.5, PLAYER2_Y, 1.0, PLAYER_HALF_HEIGHT);
				StdDraw.filledRectangle(0.5, PLAYER1_Y, 1.0, PLAYER_HALF_HEIGHT);

				// Display the player labels plus their current score
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 16));
				StdDraw.textLeft(0.0, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, "Player 1: " + score1);
				StdDraw.textLeft(0.0, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, "Player 2: " + score2);

				// Draw a little blue dot to indicate whose turn it is
				StdDraw.setPenColor(StdDraw.BLUE);
				if (turn1)
					StdDraw.filledCircle(INDICATOR_X, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);
				else
					StdDraw.filledCircle(INDICATOR_X, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);

				// Draw the gems and then any gem currently being dragged around
				player1.draw(PLAYER1_Y);
				player2.draw(PLAYER2_Y);

				// See if we currently have a gem waiting to be dropped
				if (current != null)
					current.draw(mouseX, mouseY);

				StdDraw.show(10);
			}

			// Display the final message saying who won the game
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
			if (score1 == score2)
				StdDraw.text(0.5, SCORE_Y, "Tie game!");
			else if (score1 > score2)
				StdDraw.text(0.5, SCORE_Y, "Player 1 wins!");
			else
				StdDraw.text(0.5, SCORE_Y, "Player 2 wins!");

			StdDraw.show(0);
		}


		else {

			//Creates a list for Player 1 and Computer
			player1 = new GemList();
			computer = new GemList();

			Gem current = null;
			boolean mouseDown = false;
			boolean turn1 = true;

			int score1 = 0;
			int score2 = 0;

			while ((player1.size() < MAX_GEMS) && (computer.size() < MAX_GEMS)){

				StdDraw.clear();

				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

				if (current == null){
					current = new Gem();
				}

				// Check for a click of the mouse, wait for release of the button
				if (StdDraw.mousePressed()) {
					mouseDown = true;
				} else if (mouseDown) {
					// See if they clicked in the Player1 area
					if ((mouseY > PLAYER1_Y - PLAYER_HALF_HEIGHT) && (mouseY < PLAYER1_Y + PLAYER_HALF_HEIGHT)) {
						player1.insertBefore(current, xToIndex(mouseX));
						current = null;
						turn1 = false;
					}
					// See if they clicked in the Player2 area
					else if ((mouseY > COMPUTER_Y - PLAYER_HALF_HEIGHT) && (mouseY < COMPUTER_Y + PLAYER_HALF_HEIGHT)) {
						computer.insertBefore(current, xToIndex(mouseX));
						current = null;
						turn1 = false;
					}
					// Regardless, this click is over even if we didn't drop the gem
					mouseDown = false;
				}

				if (current == null){
					current = new Gem();
				}

				// Draw a little blue dot to indicate whose turn it is
				StdDraw.setPenColor(StdDraw.BLUE);
				if (turn1)
					StdDraw.filledCircle(INDICATOR_X, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);
				else {
					StdDraw.filledCircle(INDICATOR_X, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);
					computerMove(current);
					current = null;
					turn1 = true;
				}

				score1 = player1.score();
				score2 = computer.score();

				// Background to show each player
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledRectangle(0.5, PLAYER2_Y, 1.0, PLAYER_HALF_HEIGHT);
				StdDraw.filledRectangle(0.5, PLAYER1_Y, 1.0, PLAYER_HALF_HEIGHT);

				// Display the player labels plus their current score
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 16));
				StdDraw.textLeft(0.0, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, "Player 1: " + score1);
				StdDraw.textLeft(0.0, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, "Computer: " + score2);

				// Draw the gems and then any gem currently being dragged around
				player1.draw(PLAYER1_Y);
				computer.draw(PLAYER2_Y);

				// See if we currently have a gem waiting to be dropped
				if (current != null)
					current.draw(mouseX, mouseY);

				StdDraw.setPenColor(StdDraw.BLUE);

				StdDraw.filledCircle(INDICATOR_X, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);


				StdDraw.show(10);

			}

			// Display the final message saying who won the game
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
			if (score1 == score2)
				StdDraw.text(0.5, SCORE_Y, "Tie game!");
			else if (score1 > score2)
				StdDraw.text(0.5, SCORE_Y, "Player 1 wins!");
			else
				StdDraw.text(0.5, SCORE_Y, "I win and you are a big fat loser!!");

			StdDraw.show(0);

		}

	}


	private static void computerMove(Gem current){
		int maxDiff = Integer.MIN_VALUE;
		boolean player = false;
		int index = 0;

		for (int i = 0; i <= computer.size(); i++) {
			computer.insertBefore(current, i);
			int diff = computer.score() - player1.score();
			computer.removeAt(i);

			if(diff > maxDiff){
				index = i;
				maxDiff = diff;
			}
		}

		for (int i = 0; i <= player1.size(); i++) {
			player1.insertBefore(current, i);
			int diff = computer.score() - player1.score();
			player1.removeAt(i);

			if(diff > maxDiff) {
				player = true;
				index = i;
				maxDiff = diff;
			}
		}

		if(player){
			player1.insertBefore(current, index);
			System.out.println("Computer played " + current + " on player at " + (index + 1));
		}
		else {
			computer.insertBefore(current, index);
			System.out.println("Computer played " + current + " on computer at " + (index +1));

		}
	}
}
