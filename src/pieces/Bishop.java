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
				if ((board[x+i][y+i].color != color)){
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
				if ((board[x-i][y+i].color != color)){
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
				if ((board[x+i][y-i].color != color)){
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
				if ((board[x-i][y-i].color != color)){
					legalMoves.add((x-i)+","+(y-i));
				}
			}
			else {
				break;
			}
		}
		return legalMoves;
	}
}
