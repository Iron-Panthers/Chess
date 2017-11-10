package pieces;

import java.util.ArrayList;

import board.Main;
import board.Player;

public class Queen extends Piece{
	public Player owner;
	public PieceType type;
	public int color;
	public Queen(int color) {
		this.color = color;
		type = PieceType.Q;
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
					movePiece(legalMoves(x,y),x,y,moveX,moveY);
				}
				else {
					System.out.println("That is not your piece");
					owner.turn();
				}
			}	
		}
	}
	@Override
	public void movePiece(ArrayList<String> legalMoves, int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		String playerMovement = moveX+","+moveY;
		if (legalMoves.contains(playerMovement)) {
			//Moves knight to new space, replaces empty space with a blank
			Main.board[moveX][moveY] = Main.board[x][y]; 
			Main.board[x][y] = Main.blank;
		}
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
		return null;
	}
}
