/**
* A program to play TicTacToe between human and computer.
* This version:
*<ul><li>
* 		Uses a TicTacToeBoard to hold current board. Initial
*    state is characters '1''-0'
*</li><li>
* 		Computer's first choice will be a win move, if none available a defensive block,
*    otherwise first available slot.
*</li><li>
* 		Human goes first is X's, Computer is O's.
*</li>
*
*</ul>
*/


import java.util.Scanner;

public class TicTacToe {

	/**
	* Board holds the current state of the TicTacToe game
	*/

	TicTacToeBoard gameBoard;
	/**
	* Creates a new <code>TicTacToe</code> instance.
	*
	* Creates and initializes 3x3 TicTacToeBoard
	*/


	public TicTacToe() {
		gameBoard = new TicTacToeBoard();
	}

	/** Runs the game for <code>TicTacToe</code> instance. */


	public void play() {
		boolean computerMove = false;
		Scanner reader = new Scanner(System. in );

		while (true) {
			if (computerMove) {
				char move = gameBoard.findBestMove();
				gameBoard.updateBoard(move, 'O');
				computerMove = false;
			} else {
				System.out.println(gameBoard);

				boolean result = false;

				do {
					System.out.println("Your move: ");

					String m = reader.nextLine();

					result = gameBoard.updateBoard(m.charAt(0), 'X');
					if (result == false) System.out.println("Invalid move");

				} while (result == false);

				computerMove = true;
			}

			char winner = gameBoard.findWinner();
			if (winner == 'O') {
				System.out.println("Computer Wins - Yay!");
				System.out.println(gameBoard);

				break;
			} else if (winner == 'X') {
				System.out.println("Human Wins - Boo!");
				System.out.println(gameBoard);
				break;
			} else if (winner == 'z') {
				System.out.println("Noone wins - game over!");
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