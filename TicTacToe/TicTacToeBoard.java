public class TicTacToeBoard {
	private char[][] board;
	private int numRows = 3;
	private int numCols = 3;

	/**
	* TicTacToeBoard Constructor
	*/
	public TicTacToeBoard() {
		board = new char[numRows][numCols];
		char k = '1';

		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				board[row][col] = k++;
			}
		}
	}

	/**
	* Updates board in <code>TicTacToe</code> instance.
	* b is the intended spot value, c is either 'X' or 'O'
	* @param b <code>char</code> value containing the spot on the board '1'-'9'
	* @param c <code>char</code> value containing the move, 'X' or 'O'
	* @return true if the move is valid and board was updated, false otherwise.
	*/
	public boolean updateBoard(char b, char c) {
		//Add code here
	}

	/**
	* Finds the best move for the Computer to make in <code>TicTacToe</code> instance.
	* @return char representing the best available spot. Valid spots are '1'-'9'.
	* If no available spots, return 'z'.
	*/
	public char findBestMove() {

		char move = findMove('O'); // Aggresive strategy
		if (move != 'z') return move;
		move = findMove('X'); // Defensive strategy
		if (move != 'z') return move;

		char k = '1';

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] != 'X' && board[row][col] != 'O') return k;
				k++;
			}
		}

		return 'z';
	}

	/**
	* Finds the Win Position for the character passed in.
	* @param c <code>char</code> value containing either 'X' or 'O'
	* @return the win position for character passed in, '1'-'9'.
	* If not win position exists, return 'z'.
	*/
	public char findMove(char c) {
		for (int row = 0; row < 3; row++) {
			// return move if 3 in a row possible
		}
		for (int col = 0; col < 3; col++) {
			//return move if 3 in a col possible
		}
		//return move if a diagonal possible

		//otherwise return 'z';
	}

	/**
	* Checks board to see if game is over and there is a winner.
	* @return 'X' or 'O' to indicate the winner, return 'z' if no
	* winner but game is over due to no more spots open. Return 'y'
	* otherwise, indicates no winner, game continues.
	*/
	public char findWinner() {

		//Look for 3 in a col, return winning char

		//Look for 3 in a row, return winning char

		//Look for diagonal, return winning char

		//Look for empty position, return 'y'

		return 'z'; //no moves possible
	}

	/**
	* Converts the board state to a string format in the form:
	*	O | 2 | 3
	*	---------
	*	X | 5 | 6
	*	---------
	*	7 | 8 | 9
	* @return a <code>String</code> containing the board state
	*/
@Override
	public String toString() {

		/*return current board state e.g.  O | 2 | 3
   																     -----------
 																	      X | 5 | 6
																	     -----------
 																	      7 | 8 | 9 */

	}
}