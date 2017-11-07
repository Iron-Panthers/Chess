package board;
import java.util.ArrayList;

import exiled.Piece;
import pieces.PieceType;

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
	
	public static void main(String[] args) {
		//Board
		//Letters for pieces with a number to differentiate will be placed at certain parts of the board
		board = new Piece[8][8]; //8 by 8, stops at 7
		//Adds Clear pieces
		for (int i = 0; i<8; i++) { //2 equals no color
			for (int j = 0; j<8; j++) {
				Piece blank = new Piece(PieceType.X,2);
				board[j][i] = blank;
			}
		}
		
		//Two Piece Construction
		for (int i = 0; i<2; i++) { //For Color
			for (int j = 0; j<2; j++) { //For individual piece
				//Constructs two for each color
				Piece rook = new Piece(PieceType.R,i);
				Piece knight = new Piece(PieceType.N,i);
				Piece bishop = new Piece(PieceType.B,i);
				if (j == 0) {
					board[0][i*7] = rook;
					board[2][i*7] = bishop;
					board[1][i*7] = knight; //If zero, it is on first rank, if 1, it is on last rank
					
				}
				else if (j == 1) {
					board[7][i*7] = rook;
					board[5][i*7] = bishop;
					board[6][i*7] = knight; //If zero, it is on first rank, if 1, it is on last rank
				}
			}
		}
		//Royalty Construction
		for (int i = 0; i<2; i++) {
			Piece king = new Piece(PieceType.K,i); //Only one of each, so the number is zero
			Piece queen = new Piece(PieceType.Q,i);
			board[3][i*7] = queen;
			board[4][i*7] = king;
		}
		//Pawn Construction
		for (int i = 0; i<2; i++) { //For color
			for (int j = 0; j<8; j++) { //Creates 8 total pawns
				Piece pawn = new Piece(PieceType.P,i);
				board[j][i*5+1] = pawn; //Goes across the rank, file changes for colors
			}
		}
		
		//Players
		players = new ArrayList<Player>();
		for (int i = 0; i<2; i++) {
			Player player = new Player(i);
			players.add(player);
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
					players.get(i).turn();
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
		//Sees if any of the enemies pieces are in check
		return false;
	}
	public static void display() {
		for (int i = 7; i>=0; i--) { //For rows
			System.out.print(i+1);
			for (int j = 0; j<8; j++) { //For Columns
				System.out.print("["+board[j][i].type+"]");
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
