import java.util.Scanner;

public class TicTacToe {
	/**
	* Current state of the TicTacToe board
	*/
	TicTacToeBoard gameBoard;

	/**
	* TicTacToeBoard Constructor
	*/
	public TicTacToe() {
		gameBoard = new TicTacToeBoard();
	}

	/** Starts the game for <code>TicTacToe</code> instance. */
	public void play() {
		boolean computerMove = false;
		Scanner reader = new Scanner(System.in);

		while(true) {
			if(computerMove) {
				char move = gameBoard.findBestMove();
				gameBoard.updateBoard(move, 'O');
				computerMove = false;
			}
			else {
				System.out.println(gameBoard);
				boolean result = false;

				do {
					System.out.println("Your move: ");
					String m = reader.nextLine();

					result = gameBoard.updateBoard(m.charAt(0), 'X');
					if(result == false) {
						System.out.println("Invalid move");
					}

				} while(result == false);

				computerMove = true;
			}

			char winner = gameBoard.findWinner();
			if(winner == 'O') {
				System.out.println("Computer Wins");
				System.out.println(gameBoard);
				break;
			}
			else if(winner == 'X') {
				System.out.println("Human Wins");
				System.out.println(gameBoard);
				break;
			}
			else if(winner == 'z') {
				System.out.println("Tie Game");
				System.out.println(gameBoard);
				break;
			}
		}
	}

	/**
	* Create a TicTacToe game, initiate play.
	*/
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}