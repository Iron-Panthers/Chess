package pieces;

import java.util.ArrayList;

import board.Constants;
import board.Main;
import board.Player;

public class Rook extends Piece{
	public PieceType type;
	public Player owner;
	public int color;
	public Piece[][] board;
	public Rook(int color) {
		this.color = color;
		if (color == 0) {
			type = PieceType.R;
		}
		else {
			type = PieceType.r;
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
			if (y<=(Constants.BOARD_LENGTH-1)&&y>=0) {
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
				if (board[x][y].color==color) { //Checks if player owns the piece
					ArrayList<String> legalMoves = legalMoves(x,y);
					if(movePiece(legalMoves,x,y,moveX,moveY)) {
						return true;
					}
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
	@Override
	public ArrayList<String> legalMoves(int x, int y) {
		// TODO Auto-generated method stub
		ArrayList<String> legalMoves = new ArrayList<String>();
		//Horizontal to the right
		for (int i = 0; i<(Constants.BOARD_LENGTH-1)-x; i++) {
			if ((board[x+i][y].color != color)&&(((x+i)>=0))&&((x+i)<=7)) {
				legalMoves.add((x+i)+","+y);
			}
			else {
				break;
			}
		}
		//Horizontal to the left
		for (int i = 0; i>-(Constants.BOARD_HEIGHT-1)-x; i--) {
			if ((board[x+i][y].color != color)&&(((x+i)>=0))&&((x+i)<=7)) {
				legalMoves.add((x+i)+","+y);
			}
			else {
				break;
			}
		}
		//Vertical up
		for (int i = 0; i<(Constants.BOARD_HEIGHT-1)-y; i++) {
			if ((board[x][y+i].color != color)&&(((y+i)>=0))&&((y+i)<=7)) {
				legalMoves.add(x+","+(y+i));
			}
			else {
				break;
			}
		}
		//Vertical down
		for (int i = 0; i>-(Constants.BOARD_HEIGHT-1)-y; i--) {
			if ((board[x][y+i].color != color)&&(((y+i)>=0))&&((y+i)<=7)) {
				legalMoves.add(x+","+(y+i));
			}
			else {
				break;
			}
		}
		return legalMoves;
	}
}
