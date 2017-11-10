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
	public King(int color) {
		this.color = color;
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
	public boolean checkMove(int x, int y, int moveX, int moveY, Piece[][] board) {
		this.board = board;
//		System.out.println();
//		System.out.println(color);
		if (isInRange(moveX,moveY)) {
			if (board[x][y].toString().equals(toString())) {
				ArrayList<String> legalMoves = legalMoves(x,y);
				if(movePiece(legalMoves,x,y,moveX,moveY)) {
					return true;
				}
				else {
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
			return true;
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
		for (int i = 0; i<maxDistance; i++) {
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
		for (int i = 0; i>maxDistance; i--) {
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
		for (int i = 0; i<maxDistance; i++) {
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
		for (int i = 0; i>maxDistance; i--) {
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
		for (int i = 0; i<maxDistance; i++) {
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
		for (int i = 0; i<maxDistance; i++) {
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
		for (int i = 0; i<maxDistance; i++) {
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
		for (int i = 0; i<maxDistance; i++) {
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
		return isFriendly(x,y);
	}
}
