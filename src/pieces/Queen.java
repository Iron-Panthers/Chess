package pieces;

import board.Main;
import board.Player;

public class Queen extends Piece{
	public Player owner;
	public PieceType type;
	public int color;
	public Queen() {
		type = PieceType.Q;
		for (int i = 0; i<2; i++) { //Finds the player that matches the pieces color
			if (Main.players.get(i).color == color) {
				owner = Main.players.get(i);
				color = owner.color;
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
