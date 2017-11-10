package pieces;

import java.util.ArrayList;

import board.Constants;
import board.Main;
import board.Player;

public class Bishop extends Piece{
	public PieceType type;
	public Player owner;
	public int color;
	public Piece[][] board;
	public Bishop(int color) {
		this.color = color;
		if (color == 0) {
			type = PieceType.B;
		}
		else {
			type = PieceType.b;
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
					System.out.println("That is not your piece");
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
	public int getMaxDistance(int x, int y) {
		int maxDistance;
		int maxX = (Constants.BOARD_LENGTH-1)-x;
		int maxY = (Constants.BOARD_HEIGHT-1)-y;
		if (maxX>maxY) {
			maxDistance = maxY;
			return maxDistance;
		}
		else if (maxY>maxX) {
			maxDistance = maxX;
			return maxDistance;
		}
		else { //If they are the same
			maxDistance = maxX;
			return maxDistance;
		}
	}
	@Override
	public ArrayList<String> legalMoves(int x, int y) {
		ArrayList<String> legalMoves = new ArrayList<String>();
		//Sees the shortest distance to move, picks that
		int maxDistance = getMaxDistance(x,y);
		//Up and to the right
		for (int i = 0; i<maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if (isInRange(x+i,y+i)) {
				//If blank
				if (isBlank(x+i,y+i)) {
					legalMoves.add((x+i)+","+(y+i));
				}
				//If not friendly
				else if (canMove(x+i,y+i)) {
					legalMoves.add((x+i)+","+(y+i));
					break;
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
				//If blank
				if (isBlank(x-i,y+i)) {
					legalMoves.add((x-i)+","+(y+i));
				}
				//If not friendly
				else if (canMove(x-i,y+i)) {
					legalMoves.add((x-i)+","+(y+i));
					break;
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
				//If blank
				if (isBlank(x+i,y-i)) {
					legalMoves.add((x+i)+","+(y-i));
				}
				//If not friendly
				else if (canMove(x+i,y-i)) {
					legalMoves.add((x+i)+","+(y-i));
					break;
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
				//If blank
				if (isBlank(x-i,y-i)) {
					legalMoves.add((x-i)+","+(y-i));
				}
				//If not friendly
				else if (canMove(x-i,y-i)) {
					legalMoves.add((x-i)+","+(y-i));
					break;
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
		//If it is this type
		if (!(enemyType.equals(toString()))){
			//If white
			if (toString().equals("B")) {
				if (enemyType.equals("b")) {
					return false;
				}
			}
			//If black
			if (toString().equals("b")) {
				if (enemyType.equals("B")) {
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
