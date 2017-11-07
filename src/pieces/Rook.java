package pieces;

import board.Main;
import board.Player;

public class Rook extends Piece{
	public PieceType type;
	public Player owner;
	public int color;
	public Rook(int color) {
		this.color = color;
		type = PieceType.R;
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
