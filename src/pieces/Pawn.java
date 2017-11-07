package pieces;

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
	@Override
	public void move(int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void capture(int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		
	}
	
}
