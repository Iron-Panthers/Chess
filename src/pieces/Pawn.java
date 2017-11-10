package pieces;

import java.util.ArrayList;

import board.Constants;
import board.Main;
import board.Player;

public class Pawn extends Piece{
	public PieceType type;
	public int color;
	public Player owner;
	public Piece[][] board;
	public Pawn(int color) {
		this.color = color;
		if (color == 0) {
			type = PieceType.P;
		}
		else {
			type = PieceType.p;
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
	public boolean canDoubleMove(int y) {
		//Double Movement
		if (color == 0) {
			if (y==((color*Constants.PAWN_POSITION_MULTIPLIER)+1)) {
				//Can move two
				return true;
			}
		}
		else if (color == 1) {
			if (y==((color*Constants.PAWN_POSITION_MULTIPLIER)+1)) {
				//Can move two
				return true;
			}
		}
		return false;
	}
	public boolean isBlank(int x, int y) {
		return board[x][y].toString().equals("X");
	}
	public boolean canCapture(int moveX, int moveY) {
		if (isInRange(moveX,moveY)) {
			if (!isBlank(moveX,moveY)) {
				String enemyType = board[moveX][moveY].toString();
				//If lower case
				if (enemyType.equals(enemyType.toLowerCase())) {
					//If this is lower case, it is friendly
					if (toString().equals(toString().toLowerCase())) {
						return false;
					}
					else {
						return true;
					}
				}
				else {
					//Enemy is upper case
					//If this is lower case, it is friendly
					if (toString().equals(toString().toLowerCase())) {
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
		return false;
	}
	@Override
	public ArrayList<String> legalMoves(int x, int y) {
		// TODO Auto-generated method stub
		ArrayList<String> legalMoves = new ArrayList<String>();
		//Movement
		if (canDoubleMove(y)) {
			if ((isBlank(x,y+1))&&(isBlank(x,y+2))) {
				legalMoves.add(x+","+(y+2));
			}
		}
		if (isBlank(x,y+1)) {
			legalMoves.add(x+","+(y+1));
		}
		//Capture Left
		if (canCapture(x-1,y+1)) {
			legalMoves.add((x-1)+","+(y+1));
		}
		//Capture right
		if (canCapture((x+1),(y+1))){
			legalMoves.add((x+1)+","+(y+1));
		}
		return legalMoves;
	}
}
