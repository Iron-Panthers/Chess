package pieces;

import java.util.ArrayList;

import board.Main;
import board.Player;

public class Knight extends Piece{
	public int color;
	public PieceType type;
	public Player owner;
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
	public void checkMove(int x, int y, int moveX, int moveY) {
		if ((0<=moveX && moveX<=7)&&(0<=moveY && moveY<=7)) { //Checks if move is in bounds
			if (Main.board[x][y].type==(type)) { //It is a knight
				if (Main.board[x][y].color==color) { //Checks if player owns the piece
					if (!owner.isChecked()) {
						movePiece(legalMoves(x,y),x,y,moveX,moveY);
					}
					else {
						System.out.println("You cannot leave yourself in check");
					}
				}
				else {
					System.out.println("That is not your piece");
					owner.turn();
				}
			}	
		}
	}
	public void movePiece(ArrayList<String> legalMoves, int x, int y, int nextX, int nextY) {
//		if (legalMoves.contains(playerX+","+Y)), do the stuff
		String playerMovement = nextX+","+nextY;
		if (legalMoves.contains(playerMovement)) {
			//Moves knight to new space, replaces empty space with a blank
			Main.board[nextX][nextY] = Main.board[x][y]; 
			Main.board[x][y] = Main.blank;
		}
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
		int i = 2; //Max change in a value for a knight
		int j = 1; //Min change in a value for a knight
		// TODO Auto-generated method stub
		if (x+i<=7 && x+i>=0) { //X is in the range when 2 is added
			if (y+j<=7 && y+j>=0) { //Y is in range when 2 is added
				if (Main.board[x+i][y+j].color != owner.color) { //If opposite color, it can take the piece
					legalMoves.add((x+i)+","+(y+j));
				}
			}
			if (y-j<=7 && y-j>=0) {
				if (Main.board[x-i][y-j].color != owner.color) { //If opposite color, it can take the piece
					legalMoves.add((x-i)+","+(y-j));
				}
			}
		}
		if (x-i<=7 && x-i>=0) { //X is in range when 2 is subtracted
			if (y+j<=7 && y+j>=0) { //Y is in range when 2 is added
				if (Main.board[x-i][y+j].color != owner.color) { //If opposite color, it can take the piece
					legalMoves.add((x-i)+","+(y+j));
				}
			}
			if (y-1<=7 && y-1>=0) {
				if (Main.board[x-i][y-j].color != owner.color) { //If opposite color, it can take the piece
					legalMoves.add((x-i)+","+(y-j));
				}
			}
		}
		if (y+i<=7 && y+i>=0) {
			if (x+j<=7 && x+j>=0) {
				if (Main.board[y+i][x+j].color != owner.color) { //If opposite color, it can take the piece
					legalMoves.add((y+i)+","+(x+j));
				}
			}
			if (x-j<=7 && x-j<=0) {
				if (Main.board[y+i][x-j].color != owner.color) { //If opposite color, it can take the piece
					legalMoves.add((y+i)+","+(x-j));
				}
			}
		}
		if (y-i<=7 && y-i>=0) {
			if (x+j<=7 && x+j>=0) {
				if (Main.board[y-i][x+j].color != owner.color) { //If opposite color, it can take the piece
					legalMoves.add((y-i)+","+(x+j));
				}
			}
			if (x-j<=7 && x-j<=0) {
				if (Main.board[y-i][x-j].color != owner.color) { //If opposite color, it can take the piece
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
