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
	* Compares two arrays
	* @param a First array to compare
	* @param b Second array to compare
	* @return true if the arrays are equal, false if they are not equal
	*/
	private static boolean compareArrays(char[] a, char[] b) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	* Updates board in <code>TicTacToe</code> instance.
	* b is the intended spot value, c is either 'X' or 'O'
	* @param b <code>char</code> value containing the spot on the board '1'-'9'
	* @param c <code>char</code> value containing the move, 'X' or 'O'
	* @return true if the move is valid and board was updated, false otherwise.
	*/
	public boolean updateBoard(char b, char c) {
		int a = Character.getNumericValue(b) - 1;
		int row = a / 3;
		int col = a % 3;
		if(board[row][col] != 'X' && board[row][col] != 'O') {
			board[row][col] = c;
			return true;
		}
		return false;
	}

	/**
	* Finds the best move for the Computer to make in <code>TicTacToe</code> instance.
	* @return char representing the best available spot. Valid spots are '1'-'9'.
	* If no available spots, return 'z'.
	*/
	public char findBestMove() {
		char move = findMove('O'); // Aggresive strategy
		if(move != 'z') {
			return move;
		}
		move = findMove('X'); // Defensive strategy
		if(move != 'z') {
			return move;
		}

		char k = '1';
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				if(board[row][col] != 'X' && board[row][col] != 'O') {
					System.out.println("Comp move " + k);
					return k;
				}
				k++;
			}
		}

		return 'z';
	}

	/**
	* Finds the win position for the character passed in.
	* @param c <code>char</code> value containing either 'X' or 'O'
	* @return the win position for character passed in, '1'-'9'.
	* If not win position exists, return 'z'.
	*/
	public char findMove(char c) {
		char x = board[0][0];
		char y = board[1][1];
		char z = board[2][2];
		if(y == z && y == c && x != 'X' && x != 'O') {return x;}
		else if(x == z && x == c && y != 'X' && y != 'O') {return y;}
		else if(x == y && x == c && z != 'X' && z != 'O') {return z;}

		x = board[0][2];
		y = board[1][1];
		z = board[2][0];
		if(y == z && y == c && x != 'X' && x != 'O') {return x;}
		else if(x == z && x == c && y != 'X' && y != 'O') {return y;}
		else if(x == y && x == c && z != 'X' && z != 'O') {return z;}

		for(int row = 0; row < 3; row++) {
			x = board[row][0];
			y = board[row][1];
			z = board[row][2];
			if(y == z && y == c && x != 'X' && x != 'O') {return x;}
			else if(x == z && x == c && y != 'X' && y != 'O') {return y;}
			else if(x == y && x == c && z != 'X' && z != 'O') {return z;}
		}
		for(int col = 0; col < 3; col++) {
			x = board[0][col];
			y = board[1][col];
			z = board[2][col];
			if(y == z && y == c && x != 'X' && x != 'O') {return x;}
			else if(x == z && x == c && y != 'X' && y != 'O') {return y;}
			else if(x == y && x == c && z != 'X' && z != 'O') {return z;}
		}

		return 'z';
	}

	/**
	* Checks board to see if game is over and there is a winner.
	* @return 'X' or 'O' to indicate the winner, return 'z' if no winner.
	* Game is over due to no more spots open. Return 'y' otherwise, indicates no winner, game continues.
	*/
	public char findWinner() {
		char[] xWin = {'X', 'X', 'X'};
		char[] oWin = {'O', 'O', 'O'};
		for(int row = 0; row < board.length; row++) {
			if(compareArrays(board[row], xWin)) {return 'X';}
			if(compareArrays(board[row], oWin)) {return 'O';}
		}

		for(int row = 0; row < board.length; row++) {
			int x = 0;
			int o = 0;
			for(int col = 0; col < board[row].length; col++) {
				if(board[col][row] == 'X') {x++;}
				else if(board[col][row] == 'O') {o++;}

				if(x == 3) {return 'X';}
				if(o == 3) {return 'O';}
			}
		}

		boolean possibleMoves = false;
		char[] diagonal1 = new char[3];
		char[] diagonal2 = new char[3];
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(row == col) {diagonal1[row] = board[row][col];}
				if(2 - row == col) {diagonal2[row] = board[row][col];}
				if(board[row][col] != 'X' && board[row][col] != 'O') {possibleMoves = true;}
			}
		}

		if(compareArrays(diagonal1, xWin) || compareArrays(diagonal2, xWin)) {return 'X';}
		if(compareArrays(diagonal1, oWin) || compareArrays(diagonal2, oWin)) {return 'O';}

		return possibleMoves ? 'y' : 'z'; // If no moves are possible, game ends
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
		String output = "";
		for(char[] row : board) {
			output += " ";
			for(char c : row) {
				output += c + " | ";
			}
			output = output.substring(0, output.length() - 3) + "\n" + "-----------" + "\n";
		}

		return output.substring(0, output.length() - 12);
	}
}