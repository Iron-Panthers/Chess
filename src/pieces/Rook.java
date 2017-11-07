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
		if (Main.board[x][y].type.equals(PieceType.R)) {
			//Does the rook move, if Math.abs(x-moveX)==Math.abs(y-moveY)&&!(x-moveX)==0, move
		}
	}

	@Override
	public void capture(int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		
	}

}
