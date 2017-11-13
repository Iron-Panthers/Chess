package board;
import java.util.ArrayList;
import java.util.Scanner;

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
	public static int currentColor;
	
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
	
	//Constructors
	public static Scanner input;
	
	//Ints for game
	public static int drawAgreement;
	
	public static void main(String[] args) {
		input = new Scanner(System.in);
		//Draw variables
		drawAgreement = 0;
		
		//Board
		//Letters for pieces with a number to differentiate will be placed at certain parts of the board
		board = new Piece[Constants.BOARD_LENGTH][Constants.BOARD_HEIGHT]; //8 by 8, stops at 7
		//Adds Clear pieces
		for (int i = 0; i<Constants.BOARD_HEIGHT; i++) { //2 equals no color
			for (int j = 0; j<Constants.BOARD_LENGTH; j++) {
				blank = new Blank(2);
//				System.out.println(blank.color);
				board[j][i] = blank;
//				System.out.println(board[j][i].color);
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
					
					board[Constants.ROOK_POSITION][i*(Constants.BOARD_LENGTH-1)] = rook;
//					Piece rookPlace = board[Constants.ROOK_POSITION][i*(Constants.BOARD_LENGTH-1)];
//					System.out.println(rookPlace.color = i);
					board[Constants.BISHOP_POSITION][i*(Constants.BOARD_LENGTH-1)] = bishop;
					board[Constants.KNIGHT_POSITION][i*(Constants.BOARD_LENGTH-1)] = knight; //If zero, it is on first rank, if 1, it is on last rank
					
				}
				else if (j == 1) {
					board[Constants.ROOK_SECOND_POSITION][i*(Constants.BOARD_LENGTH-1)] = rook;
					board[Constants.BISHOP_SECOND_POSITION][i*(Constants.BOARD_LENGTH-1)] = bishop;
					board[Constants.KNIGHT_SECOND_POSITION][i*(Constants.BOARD_LENGTH-1)] = knight; //If zero, it is on first rank, if 1, it is on last rank
				}
			}
		}
		//Royalty Construction
		for (int i = 0; i<2; i++) {
			king = new King(i); //Only one of each, so the number is zero
			queen = new Queen(i);
			board[Constants.QUEEN_POSITION][i*(Constants.BOARD_LENGTH-1)] = queen;
			board[Constants.KING_POSITION][i*(Constants.BOARD_LENGTH-1)] = king;
		}
		//Pawn Construction
		for (int i = 0; i<2; i++) { //For color
			for (int j = 0; j<Constants.PAWN_NUMBER; j++) { //Creates 8 total pawns
				Piece pawn = new Pawn(i);
				board[j][(i*(board.length-3))+1] = pawn; //Goes across the rank, file changes for colors
			}
		}
		//Game loop
		while(!checkmate) {
			for (int i = 0; i<2; i=(i+1)%players.size()) {
				currentPlayer = players.get(i);
				currentColor = currentPlayer.color;
				nextPlayer = players.get((i+1)%players.size());
				if(isChecked) {
					currentPlayer.checkTurn();
					isChecked = false;
				}
				else {
					currentPlayer.turn();
				}
				isChecked = checkForCheck();
				if(!hasKing(nextPlayer)) {
					gameOver(nextPlayer);
					input.next();
					break;
				}
				else if(!hasInsufficientMaterial()) {
					draw();
					break;
				}
				else if(drawAgreement>=2) {
					draw();
				}
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
	public static void draw() {
		System.out.println("It is a draw");
		input.next();
	}
	public static int getPieceColor(Piece piece) {
		if (piece.toString().equals(piece.toString().toLowerCase())) {
			return 1;
		}
		return 0;
	}
	public static boolean hasInsufficientMaterial() {
		ArrayList<Piece> blackPieces = new ArrayList<Piece>();
		ArrayList<Piece> whitePieces = new ArrayList<Piece>();
		for (int i = 0; i<Constants.BOARD_HEIGHT; i++) {
			for (int j = 0; j<Constants.BOARD_LENGTH; j++) {
				Piece testPiece = board[j][i];
				if (getPieceColor(testPiece) == 0) {
					whitePieces.add(testPiece);
				}
				else {
					blackPieces.add(testPiece);
				}
			}
		}
		if (isInsufficient(whitePieces,blackPieces)) {
			return false;
		}
		return true;
	}
	public static boolean isInsufficient(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {
		boolean whitePiecesHasPawn = false;
		boolean blackPiecesHasPawn = false;
		//Finds white pawn
		for (int i = 0; i<whitePieces.size()-1; i++) {
			if (whitePieces.get(i).toString().equals("P")) {
				whitePiecesHasPawn = true;
				break;
			}
		}
		//Finds black pawn
		for (int i = 0; i<blackPieces.size()-1; i++) {
			if (blackPieces.get(i).toString().equals("p")) {
				blackPiecesHasPawn = true;
				break;
			}
		}
		if (!whitePiecesHasPawn) {
			return getIsInsufficient(whitePieces,blackPieces);
		}
		else if (!blackPiecesHasPawn) {
			return getIsInsufficient(whitePieces,blackPieces);
		}
		
		return false;
	}
	public static boolean getIsInsufficient(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {
		//K v K
		if (whitePieces.size()==1 && blackPieces.size()==1) {
			if (whitePieces.get(0).toString().equals("K") && blackPieces.get(0).toString().equals("k")) {
				return true;
			}
		}
		//K & N v K
		else if (whitePieces.size()==2 && blackPieces.size()==1) {
			for (int i = 0; i<whitePieces.size()-1; i++) {
				//Other has to be a king
				if (whitePieces.get(i).toString().equals("N")) {
					return true;
				}
			}
		}
		else if (whitePieces.size()==1 && blackPieces.size()==2) {
			for (int i = 0; i<blackPieces.size()-1; i++) {
				//Other has to be a king
				if (blackPieces.get(i).toString().equals("n")) {
					return true;
				}
			}
		}
		//K & B v K
		else if (whitePieces.size()==2 && blackPieces.size()==1) {
			for (int i = 0; i<whitePieces.size()-1; i++) {
				//Other has to be a king
				if (whitePieces.get(i).toString().equals("B")) {
					return true;
				}
			}
		}
		else if (whitePieces.size()==1 && blackPieces.size()==2) {
			for (int i = 0; i<blackPieces.size()-1; i++) {
				//Other has to be a king
				if (blackPieces.get(i).toString().equals("b")) {
					return true;
				}
			}
		}
		//K & NN v K
		else if (whitePieces.size()==2 && blackPieces.size()==1) {
			int knightNumber = 0;
			for (int i = 0; i<whitePieces.size()-1; i++) {
				//Other has to be a king
				if (whitePieces.get(i).toString().equals("N")) {
					knightNumber++;
				}
			}
			if (knightNumber==2) {
				return true;
			}
		}
		else if (whitePieces.size()==1 && blackPieces.size()==2) {
			int knightNumber = 0;
			for (int i = 0; i<blackPieces.size()-1; i++) {
				//Other has to be a king
				if (blackPieces.get(i).toString().equals("n")) {
					knightNumber++;
				}
			}
			if (knightNumber==2) {
				return true;
			}
		}
		//K & BB v K
		else if (whitePieces.size()==2 && blackPieces.size()==1) {
			int bishopNumber = 0;
			for (int i = 0; i<whitePieces.size()-1; i++) {
				//Other has to be a king
				if (whitePieces.get(i).toString().equals("B")) {
					bishopNumber++;
				}
			}
			if (bishopNumber==2) {
				return true;
			}
		}
		else if (whitePieces.size()==1 && blackPieces.size()==2) {
			int bishopNumber = 0;
			for (int i = 0; i<blackPieces.size()-1; i++) {
				//Other has to be a king
				if (blackPieces.get(i).toString().equals("b")) {
					bishopNumber++;
				}
			}
			if (bishopNumber==2) {
				return true;
			}
		}
		return false;
	}
	public static boolean hasKing(Player player) {
		for (int i = 0; i<Constants.BOARD_HEIGHT; i++) {
			for (int j = 0; j<Constants.BOARD_LENGTH; j++) {
				if (player.color == 0) {
					if (board[j][i].toString().equals("K")) {
						return true;
					}
				}
				else {
					if (board[j][i].toString().equals("k")) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public static void gameOver(Player player) {
		System.out.println("You lose, "+player);
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
//				System.out.print("["+board[j][i].color+"]");
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
