import java.util.Scanner;

public class TicTacToe {

	private char currentPlayerMark;
	private char[][] board;
	Scanner rowInput;
	Scanner colInput;
	int newRowNum;
	int newColNum;
	String empty = "";
	
	public TicTacToe() {

		board = new char[3][3];
		currentPlayerMark = 'X';
		initializeBoard();

	}

	public void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				board[i][j] = '-';
			}
		}

	}

	public void printBoard() {

		System.out.println();
		System.out.println("-------------");

		for (int i = 0; i < 3; i++) {

			System.out.print("| ");

			for (int j = 0; j < 3; j++) {

				System.out.print(board[i][j] + " | ");

			}

			System.out.println();

			System.out.println("-------------");

		}
		System.out.println();

	}

	public boolean isBoardFull() {

		boolean isFull = true;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false;
				}

			}
		}

		return isFull;
	}

	public boolean checkForWin() {
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());

	}

	private boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;

	}

	private boolean checkDiagonalsForWin() {
		for (int i = 0; i < 3;) {
			return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(
					board[0][2], board[1][1], board[2][0]) == true));
		}
		return false;

	}

	private boolean checkRowCol(char c1, char c2, char c3) {

		return ((c1 != '-') && (c1 == c2) && (c2 == c3));

	}

	public void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	public boolean placeMark(int row, int col) {

		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayerMark;
					return true;
				}
			}
		}

		return false;
	}

	public void executeGame() {

		while (true) {
			rowInput = new Scanner(System.in);
			colInput = new Scanner(System.in);

			do {
				System.out.print("Player " + currentPlayerMark
						+ ", Please Input Row Position (0-2): ");
				String rowNum = rowInput.nextLine();

				System.out.print("Player " + currentPlayerMark
						+ ", Please Input Column Position (0-2): ");
				String colNum = colInput.nextLine();
				
				if(rowNum.isEmpty() || colNum.isEmpty()) {
					System.out.println("\n!!!!! Please Input A Number !!!!!");
					System.out.println();
					continue;
				}

				newRowNum = Integer.parseInt(rowNum);
				newColNum = Integer.parseInt(colNum);
				
				if ((placeMark(newRowNum,newColNum)) == false) {
					System.out.println("\n!!!!!!!!!! Spot Already Taken **Or** Number Not On Board !!!!!!!!!!");
					System.out.println();
					continue;
				}

				else if ((newRowNum >= 0 && newRowNum <= 2)
						&& (newColNum >= 0 && newColNum <= 2)) {
					break;
				}

				} while (true);

		
			placeMark(newRowNum, newColNum);
			printBoard();

			if (checkForWin()) {
				System.out.println("********** Congrats, Player "
					+ currentPlayerMark + ", You Are The Winner! **********");
				break;
			} else if (isBoardFull()) {
				System.out
					.println("########## We Have A Draw!  Please Play Again! ##########");
				break;
			} else {
				changePlayer();
			}
		}

	}
	
	public void introDesign() {
		
		System.out.println("\n#################### Tic Tac Toe Program ####################");
		System.out
				.println("================================================================");
		System.out.println();
	}

	public static void main(String[] args) {

		TicTacToe game = new TicTacToe();
		game.introDesign();
		game.executeGame();

	}

}
