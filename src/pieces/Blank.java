package pieces;

import java.util.ArrayList;

public class Blank extends Piece{
	public PieceType type;
	public int color;
	public Blank(int color) {
		this.color = color;
		type = PieceType.X;
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
	@Override
	public boolean checkMove(int x, int y, int moveX, int moveY, Piece[][] board, boolean isQuiet) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean movePiece(ArrayList<String> legalMoves, int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInRange(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
