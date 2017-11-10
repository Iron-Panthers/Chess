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
		type = PieceType.N;
		for (int i = 0; i<2; i++) { //Finds the player that matches the pieces color
			if (Main.players.get(i).color == color) {
				owner = Main.players.get(i);
				continue;
			}
		}
	}
	public boolean checkMove(int x, int y, int moveX, int moveY, Piece[][] board) {
		this.board = board;
//		System.out.println();
//		System.out.println(color);
		if ((0<=moveX && moveX<=7)&&(0<=moveY && moveY<=7)) { //Checks if move is in bounds
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
		if ((x+i)<=7 && (x+i)>=0) { //X is in the range when 2 is added
			if ((y+j)<=7 && (y+j)>=0) { //Y is in range when 2 is added
				if (board[x+i][y+j].color != color) { //If opposite color, it can take the piece
					legalMoves.add((x+i)+","+(y+j));
				}
			}
			if ((y-j)<=7 && (y-j)>=0) {
				if (board[x-i][y-j].color != color) { //If opposite color, it can take the piece
					legalMoves.add((x-i)+","+(y-j));
				}
			}
		}
		if ((x-i)<=7 && (x-i)>=0) { //X is in range when 2 is subtracted
			if ((y+j)<=7 && (y+j)>=0) { //Y is in range when 2 is added
				if (board[x-i][y+j].color != color) { //If opposite color, it can take the piece
					legalMoves.add((x-i)+","+(y+j));
				}
			}
			if ((y-j)<=7 && (y-j)>=0) {
				if (board[x-i][y-j].color != color) { //If opposite color, it can take the piece
					legalMoves.add((x-i)+","+(y-j));
				}
			}
		}
		//Up/down two over one
		if ((y+i)<=7 && (y+i)>=0) {
			if ((x+j)<=7 && (x+j)>=0) {
//				System.out.println("Should work");
//				System.out.println("This is "+color);
//				System.out.println(board[x+j][y+i].toString());
				if (board[x+j][y+i].color != color) { //If opposite color, it can take the piece
					legalMoves.add((y+i)+","+(x+j));
				}
			}
			if ((x-j)<=7 && (x-j)<=0) {
				if (board[x-j][y+i].color != color) { //If opposite color, it can take the piece
					legalMoves.add((y+i)+","+(x-j));
				}
			}
		}
		if ((y-i)<=7 && (y-i)>=0) {
			if ((x+j)<=7 && (x+j)>=0) {
				if (board[x+j][y-1].color != color) { //If opposite color, it can take the piece
					legalMoves.add((y-i)+","+(x+j));
				}
			}
			if ((x-j)<=7 && (x-j)<=0) {
				if (board[x-j][y-i].color != color) { //If opposite color, it can take the piece
					legalMoves.add((y-i)+","+(x-j));
				}
			}
		}
//		String str = "1,2"; //X and y values
//		Str.substring(0,1); //-> 1  (Left of zero spot, left of one spot, 1)
//		Str.substring(2,3) //-> 2 (Left of two spot, left of three spot, 2)
		return legalMoves;
	}
}
