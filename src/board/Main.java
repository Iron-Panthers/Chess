package board;
import java.util.ArrayList;

import pieces.Bishop;
import pieces.Blank;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Main {
	//Players
	public static ArrayList<Player> players;
	public static Player currentPlayer;
	public static Player nextPlayer;
	
	//Booleans
	public static boolean checkmate=false;
	public static boolean isChecked=false;
	
	//Board
	public static Piece board[][];
	
	//Pieces
	public static Knight knight;
	public static Rook rook;
	public static Bishop bishop;
	public static Queen queen;
	public static King king;
	public static Pawn pawn;
	public static Blank blank;
	
	public static void main(String[] args) {
		//Board
		//Letters for pieces with a number to differentiate will be placed at certain parts of the board
		board = new Piece[Constants.BOARD_LENGTH][Constants.BOARD_HEIGHT]; //8 by 8, stops at 7
		//Adds Clear pieces
		for (int i = 0; i<Constants.BOARD_HEIGHT; i++) { //2 equals no color
			for (int j = 0; j<Constants.BOARD_LENGTH; j++) {
				blank = new Blank(2);
				board[j][i] = blank;
			}
		}
		//Players
		players = new ArrayList<Player>();
		for (int i = 0; i<2; i++) {
			Player player = new Player(i);
			players.add(player);
		}
		
		//Two Piece Construction
		for (int i = 0; i<2; i++) { //For Color
			for (int j = 0; j<2; j++) { //For individual piece
				//Constructs two for each color
				rook = new Rook(i);
				knight = new Knight(i);
				bishop = new Bishop(i);
				if (j == 0) {
					board[Constants.ROOK_POSITION][i*board.length-1] = rook;
					board[Constants.BISHOP_SECOND_POSITION][i*board.length-1] = bishop;
					board[Constants.KNIGHT_POSITION][i*board.length-1] = knight; //If zero, it is on first rank, if 1, it is on last rank
					
				}
				else if (j == 1) {
					board[Constants.ROOK_SECOND_POSITION][i*board.length-1] = rook;
					board[Constants.BISHOP_SECOND_POSITION][i*board.length-1] = bishop;
					board[Constants.KNIGHT_SECOND_POSITION][i*board.length-1] = knight; //If zero, it is on first rank, if 1, it is on last rank
				}
			}
		}
		//Royalty Construction
		for (int i = 0; i<2; i++) {
			king = new King(i); //Only one of each, so the number is zero
			queen = new Queen(i);
			board[Constants.QUEEN_POSITION][i*board.length-1] = queen;
			board[Constants.KING_POSITION][i*board.length-1] = king;
		}
		//Pawn Construction
		for (int i = 0; i<2; i++) { //For color
			for (int j = 0; j<Constants.PAWN_NUMBER; j++) { //Creates 8 total pawns
				Piece pawn = new Pawn(i);
				board[j][(i*board.length-3)+1] = pawn; //Goes across the rank, file changes for colors
			}
		}
		//Game loop
		while(!checkmate) {
			for (int i = 0; i<2; i=(i+1)%players.size()) {
				currentPlayer = players.get(i);
				nextPlayer = players.get((i+1)%players.size());
				if(isChecked) {
					currentPlayer.checkTurn();
					isChecked = false;
				}
				else {
					System.out.println(currentPlayer.color);
					currentPlayer.turn();
				}
				isChecked = checkForCheck();
			}
		}
		
		//Board, stores locations of all the pieces
		//Class for each piece, each have different possible move rules
		//Input for player
		//Turns
		//Special rules
		//Checkmate rules
		//Check rules
		//Threatening
		//Same starting position
		//Collisions for non-Knight pieces, can't go through other pieces (If it passes through the x and y of another piece, cancel move)
		//Each piece has a location
		//Exceptions for collisions, if there is an enemy piece in the way it can be taken
		//Check for check after each move, if an enemy piece can move to the king's square, then the defender must get out of check
	}
	public static boolean checkForCheck() {
		return nextPlayer.isChecked();
		//Sees if any of the enemies pieces are in check
	}
	public static void display() {
		for (int i = board.length-1; i>=0; i--) { //For rows
			System.out.print(i+1);
			for (int j = 0; j<board.length; j++) { //For Columns
				System.out.print("["+board[j][i].toString()+"]");
			}
			System.out.println();
		}
		System.out.print("  a ");
		System.out.print(" b ");
		System.out.print(" c ");
		System.out.print(" d ");
		System.out.print(" e ");
		System.out.print(" f ");
		System.out.print(" g ");
		System.out.println(" h ");
	}
}
