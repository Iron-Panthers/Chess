package pieces;

import java.util.ArrayList;

import board.Constants;
import board.Main;
import board.Player;

public class Knight extends Piece{
	public int color;
	public PieceType type;
	public Player owner;
	public Piece[][] board;
	public Knight(int color) {
		this.color = color;
		if (color == 0) {
			type = PieceType.N;
		}
		else {
			type = PieceType.n;
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
	public boolean movePiece(ArrayList<String> legalMoves, int x, int y, int nextX, int nextY) {
//		if (legalMoves.contains(playerX+","+Y)), do the stuff
		String playerMovement = nextX+","+nextY;
		if (legalMoves.contains(playerMovement)) {
			//Moves knight to new space, replaces empty space with a blank
			board[nextX][nextY] = board[x][y]; 
			board[x][y] = Main.blank;
			return true;
		}
		return false;
	}
			

	public void capture(int x, int y, int moveX, int moveY) { //Not needed for knight, captures the same way it moves
		
	}
	@Override
	public String toString() {
		return type.toString();
		// TODO Auto-generated method stub
	}
	@Override
	public ArrayList<String> legalMoves(int x, int y) {
		ArrayList<String> legalMoves = new ArrayList<String>();
		int i = Constants.KNIGHT_MAX_MOVEMENT; //Max change in a value for a knight
		int j = Constants.KNIGHT_MIN_MOVEMENT; //Min change in a value for a knight
		// TODO Auto-generated method stub
		if (isInRange(x+i,y+j)) {
			if (isBlank(x+i,y+j)||canMove(x+i,y+j)) {
				legalMoves.add((x+i)+","+(y+j));
			}
		}
		if (isInRange(x+i,y-j)) {
			if (isBlank(x+i,y-j)||canMove(x+i,y-j)) {
				legalMoves.add((x+i)+","+(y-j));
			}
		}
		if (isInRange(x-i,y+j)) {
			if (isBlank(x-i,y+j)||canMove(x-i,y+j)) {
				legalMoves.add((x-i)+","+(y+j));
			}
		}
		if (isInRange(x-i,y-j)) {
			if (isBlank(x-i,y-j)||canMove(x-i,y-j)) {
				legalMoves.add((x-i)+","+(y-j));
			}
		}
		//Up/down two over one
		if (isInRange(x+j,y+i)) {
			if (isBlank(x+j,y+i)||canMove(x+j,y+i)) {
				legalMoves.add((x+j)+","+(y+i));
			}
		}
		if (isInRange(x-j,y+i)) {
			if (isBlank(x-j,y+i)||canMove(x-j,y+i)) {
				legalMoves.add((x-j)+","+(y+i));
			}
		}
		if (isInRange(x+j,y-i)) {
			if (isBlank(x+j,y-i)||canMove(x+j,y-i)) {
				legalMoves.add((x+j)+","+(y-i));
			}
		}
		if (isInRange(x-j,y-i)) {
			if (isBlank(x-j,y-i)||canMove(x-j,y-i)) {
				legalMoves.add((x-j)+","+(y-i));
			}
		}
//		String str = "1,2"; //X and y values
//		Str.substring(0,1); //-> 1  (Left of zero spot, left of one spot, 1)
//		Str.substring(2,3) //-> 2 (Left of two spot, left of three spot, 2)
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
			if (toString().equals("R")) {
				if (enemyType.equals("r")) {
					return false;
				}
			}
			//If black
			if (toString().equals("r")) {
				if (enemyType.equals("R")) {
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
