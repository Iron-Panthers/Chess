package pieces;

import java.util.ArrayList;

import board.Constants;
import board.Main;
import board.Player;

public class King extends Piece{
	public int color;
	public PieceType type;
	public Player owner;
	public Piece[][] board;
	public boolean hasMoved;
	public King(int color) {
		this.color = color;
		hasMoved = false;
		if (color == 0) {
			type = PieceType.K;
		}
		else {
			type = PieceType.k;
		}
		for (int i = 0; i<2; i++) { //Finds the player that matches the pieces color
			if (Main.players.get(i).color == color) {
				owner = Main.players.get(i);
				continue;
			}
		}
	}
	public boolean isInRange(int x, int y) { //Checks if move is in bounds
		if (x<=(Constants.BOARD_LENGTH-1)&&x>=0) {
			if (y<=(Constants.BOARD_HEIGHT-1)&&y>=0) {
				return true;
			}
		}
		return false;
	}
	public boolean isRowEmptyQueen(int testRow, Piece[][] board) {
		for (int i = 1; i<Constants.KING_POSITION; i++) {
			//If not blank, the row is not empty
			if (!board[i][testRow].toString().equals("X")) {
				return false;
			}
		}
		return true;
	}
	public boolean isRowEmptyKing(int testRow, Piece[][] board) {
		for (int i = Constants.KING_POSITION; i<Constants.BOARD_LENGTH-2; i++) {
			//If not blank, the row is not empty
			if (!board[i][testRow].toString().equals("X")) {
				return false;
			}
		}
		return true;
	}
	public boolean canCastleKingside(int x, int y, int moveX, int moveY, Piece[][] board) {
		this.board = board;
		if (!hasMoved) { //Sees if the spaces are empty
			int testRow;
			if (toString().equals("K")) {
				testRow = 0;
				color = 0;
			}
			else {
				testRow = 7;
				color = 1;
			}
			//Kingside
			//White
			if (color == 0) {
				//If rook is in its natural position
				Piece testRook = board[Constants.ROOK_POSITION][testRow];
				if (testRook.toString().equals("R")){
					if (!testRook.hasMoved) {
						return isRowEmptyKing(testRow, board);
					}
				}
			}
		}
		
		return false;
	}
	public boolean canCastleQueenside(int x, int y, int moveX, int moveY, Piece[][] board) {
		this.board = board;
		if (!hasMoved) { //Sees if the spaces are empty
			int testRow;
			if (toString().equals("K")) {
				testRow = 0;
				color = 0;
			}
			else {
				testRow = 7;
				color = 1;
			}
			//Kingside
			//White
			if (color == 0) {
				//If rook is in its natural position
				Piece testRook = board[Constants.ROOK_POSITION][testRow];
				if (testRook.toString().equals("R")){
					if (!testRook.hasMoved) {
						return isRowEmptyQueen(testRow, board);
					}
				}
			}
		}
		
		return false;
	}
	public boolean castle(int x, int y, int moveX, int moveY, Piece[][] board) {
		this.board = board;
		if (canCastleQueenside(x,y,moveX,moveY,board)) {
			//White Castle
			if (color==0) {
				//Moves king
				board[moveX][moveY]=board[x][y];
				board[x][y]=Main.blank;
				board[moveX][moveY].hasMoved = true;
				//Moves rook
				board[moveX+1][moveY]=board[Constants.ROOK_POSITION][0];
				board[Constants.ROOK_POSITION][0]=Main.blank;
				board[moveX+1][moveY].hasMoved = true;
				return true;
			}
			//Black Castle
			else {
				//Moves king
				board[moveX][moveY]=board[x][y];
				board[x][y]=Main.blank;
				board[moveX][moveY].hasMoved = true;
				//Moves rook
				board[moveX+1][moveY]=board[Constants.ROOK_POSITION][Constants.BOARD_HEIGHT-1];
				board[Constants.ROOK_POSITION][Constants.BOARD_HEIGHT-1]=Main.blank;
				board[moveX+1][moveY].hasMoved = true;
				return true;
			}
		}
		else if (canCastleKingside(x,y,moveX,moveY,board)) {
			//White Castle
			if (color==0) {
				//Moves king
				board[moveX][moveY]=board[x][y];
				board[x][y]=Main.blank;
				board[moveX][moveY].hasMoved = true;
				//Moves rook
				board[moveX-1][moveY]=board[Constants.ROOK_POSITION][0];
				board[Constants.ROOK_POSITION][0]=Main.blank;
				board[moveX-1][moveY].hasMoved = true;
				return true;
			}
			//Black Castle
			else {
				//Moves king
				board[moveX][moveY]=board[x][y];
				board[x][y]=Main.blank;
				board[moveX][moveY].hasMoved = true;
				//Moves rook
				board[moveX-1][moveY]=board[Constants.ROOK_POSITION][Constants.BOARD_HEIGHT-1];
				board[Constants.ROOK_POSITION][Constants.BOARD_HEIGHT-1]=Main.blank;
				board[moveX-1][moveY].hasMoved = true;
				return true;
			}
		}
		return false;
		
	}
	public boolean checkMove(int x, int y, int moveX, int moveY, Piece[][] board, boolean isQuiet) {
		this.board = board;
		if (isInRange(moveX,moveY)) {
			if (board[x][y].toString().equals(toString())) {
				ArrayList<String> legalMoves = legalMoves(x,y);
				if(movePiece(legalMoves,x,y,moveX,moveY)) {
					return true;
				}
				else {
					if (isQuiet) {
						return false;
					}
					System.out.println("Invalid move");
					return false;
				}
			}	
		}
		return false;
	}
	@Override
	public boolean movePiece(ArrayList<String> legalMoves, int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		String playerMovement = moveX+","+moveY;
		if (legalMoves.contains(playerMovement)) {
			//Moves knight to new space, replaces empty space with a blank
			board[moveX][moveY] = board[x][y]; 
			board[x][y] = Main.blank;
			hasMoved = true;
			return true;
		}
		else if (moveX+2==x){ //Checks for kingside castle
			castle(x,y,moveX,moveY,board);
			
		}
		return false;
	}

	@Override
	public void capture(int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		
	}
	public String toString() {
		return type.toString();
		// TODO Auto-generated method stub
	}
	@Override
	public ArrayList<String> legalMoves(int x, int y) {
		// TODO Auto-generated method stub
		ArrayList<String> legalMoves = new ArrayList<String>();
		int maxDistance = Constants.KING_MAX_MOVEMENT;
		//Rook Moves
		//Horizontal to the right
		for (int i = 0; i<=maxDistance; i++) {
			if (isInRange(x+i,y)) {
				if (!(board[x+i][y].toString().equals(toString()))){
					legalMoves.add((x+i)+","+y);
				}
			}
			else {
				break;
			}
		}
		//Horizontal to the left
		for (int i = 0; i>=maxDistance; i--) {
			if (isInRange(x+i,y)) {
				if (!(board[x+i][y].toString().equals(toString()))) {
					legalMoves.add((x+i)+","+y);
				}
			}
			else {
				break;
			}
		}
		//Vertical up
		for (int i = 0; i<=maxDistance; i++) {
			if (isInRange(x,y+i)) {
				if (!(board[x][y+i].toString().equals(toString()))) {
					legalMoves.add(x+","+(y+i));
				}
			}
			else {
				break;
			}
		}
		//Vertical down
		for (int i = 0; i>=maxDistance; i--) {
			if (isInRange(x,y+i)) {
				if (!(board[x][y+i].toString().equals(toString()))) {
					legalMoves.add(x+","+(y+i));
				}
			}
			else {
				break;
			}
		}
		//Bishop moves
		//Up and to the right
		for (int i = 0; i<=maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if (isInRange(x+i,y+i)) {
				if (!(board[x+i][y+i].toString().equals(toString()))){
					legalMoves.add((x+i)+","+(y+i));
				}
			}
			else {
				break;
			}
		}
		//Up and to the left 
		for (int i = 0; i<=maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if (isInRange(x-i,y+i)) {
				if (!(board[x-i][y+i].toString().equals(toString()))){
					legalMoves.add((x-i)+","+(y+i));
				}
			}
			else {
				break;
			}
		}
		//Down and to the right
		for (int i = 0; i<=maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if (isInRange(x+i,y-i)) {
				if (!(board[x+i][y-i].toString().equals(toString()))){
					legalMoves.add((x+i)+","+(y-i));
				}
			}
			else {
				break;
			}
		}
		//Down and to the left
		for (int i = 0; i<=maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if (isInRange(x-i,y-i)) {
				if (!(board[x-i][y-i].toString().equals(toString()))){
					legalMoves.add((x-i)+","+(y-i));
				}
			}
			else {
				break;
			}
		}
		return legalMoves;
	}
	public boolean isBlank(int x, int y) {
		return (board[x][y].toString().equals("X"));
	}
	public boolean isFriendly(int x, int y) {
		String enemyType = board[x][y].toString();
		//If not this type, not blank
		if (isBlank(x,y)) {
			return false;
		}
		if (!(enemyType.equals(toString()))){
			//If white
			if (toString().equals("K")) {
				if (enemyType.equals("k")) {
					return false;
				}
			}
			//If black
			if (toString().equals("k")) {
				if (enemyType.equals("K")) {
					return false;
				}
			}	
		}
		//if this is lower case:
		if (toString().equals(toString().toLowerCase())) {
			//If enemy is lower case
			if (enemyType.equals(enemyType.toLowerCase())) {
				return true;
			}
			else {
				return false;
			}
		}
		//If this is upper case
		else {
			//If enemy is upper case
			if (enemyType.equals(enemyType.toUpperCase())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public boolean canMove(int x, int y) {
		return !isFriendly(x,y);
	}
}
