package board;

public class Constants {
	//Board constants
	public static final int BOARD_LENGTH = 8;
	public static final int BOARD_HEIGHT = 8;
	
	//Piece positions
	public static final int ROOK_POSITION = 0;
	public static final int BISHOP_POSITION = 2;
	public static final int KNIGHT_POSITION = 1;
	public static final int QUEEN_POSITION = 3;
	public static final int KING_POSITION = 4;
	public static final int BISHOP_SECOND_POSITION = 5;
	public static final int KNIGHT_SECOND_POSITION = 6;
	public static final int ROOK_SECOND_POSITION = 7;
	
	//Knight stuff
	public static final int KNIGHT_MAX_MOVEMENT = 2;
	public static final int KNIGHT_MIN_MOVEMENT = 1;
	
	//Pawn stuff
	public static final int PAWN_NUMBER = 8;
	//                                              Always add one to this
	public static final int PAWN_POSITION_MULTIPLIER = BOARD_HEIGHT-3; //Number to multiply i by to get the right position for white and black
	
	//King stuff
	public static final int KING_MAX_MOVEMENT = 1;
}
