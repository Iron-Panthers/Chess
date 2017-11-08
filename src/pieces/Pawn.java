package pieces;

import java.util.ArrayList;

import board.Main;
import board.Player;

public class Pawn extends Piece{
	public PieceType type;
	public int color;
	public Player owner;
	public Pawn(int color) {
		this.color = color;
		type = PieceType.P;
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
