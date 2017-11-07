package pieces;

public class Blank extends Piece{
	public PieceType type;
	public int color;
	public Blank(int color) {
		this.color = color;
		type = PieceType.X;
	}
	@Override
	public void move(int x, int y, int moveX, int moveY) { //Cannot do either
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
}
