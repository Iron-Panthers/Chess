package board;
import java.util.Scanner;

import pieces.Bishop;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Player {
	Scanner input;
	public int color;
	public char[] pInputArray;	
	public Piece[][] tempBoard;
	
	public Player(int color) {
		this.color = color;
		input = new Scanner(System.in);
		//0 = white, 1 = black
	}
	public void turn() {
		//Asks for move
		Main.display();
		color = Main.currentColor;
		tempBoard = Main.board;
//		System.out.println(color);
		boolean isChoosing = true;
		boolean hasAgreed = false;
		boolean isMoving = true;
		while(isChoosing) {
			//Sees if player agrees to a draw
			while (!hasAgreed) {
				System.out.print("Would you like to agree to a draw, ");
				if (color == 0) {
					System.out.println("white?");
				}
				else {
					System.out.println("black");
				}
				String drawChoice = input.nextLine();
				if (drawChoice.equalsIgnoreCase("yes")) {
					Main.drawAgreement += 1; 
					hasAgreed = true;
					isMoving = false;
					isChoosing = false;
					isMoving = false;
					break;
				}
				else if (drawChoice.equalsIgnoreCase("no")) {
					Main.drawAgreement = 0;
					hasAgreed = true;
					break;
				}
				else {
					System.out.println("Invalid Choice");
				}
			}
			if (isMoving) {
				System.out.print("Where is the piece you would like to move, ");
				if (color == 0) {
					System.out.println("white?");
				}
				else {
					System.out.println("black?");
				}
				int x = getX();
				int y = getY();
				int moveX = moveX();
				int moveY = moveY();
	//			Main.board[moveX][moveY] = Main.board[x][y];
	//			Main.board[x][y] = Main.blank;
				if(tempBoard[x][y].checkMove(x, y, moveX, moveY, tempBoard)) {
					if (isChecked()) {
						break;
					}
					else {
						//If they did not put themselves in check, move
						Main.board[x][y].checkMove(x, y, moveX, moveY, Main.board);
						String pawnString;
						if (color == 0) {
							pawnString = "P";
						}
						else {
							pawnString = "p";
						}
						if (Main.board[moveX][moveY].toString().equals(pawnString)) {
							promote(moveX,moveY);
						}
						isChoosing = false;
						break;
					}
				}
			}
		}
	}
	public void promote(int pawnX, int pawnY) {
		boolean isPromoting = true;
		System.out.println("What would you like to promote your pawn to? Q for queen, N for Knight, R for Rook, and B for Bishop.");
		while (isPromoting) {
			String promotionPiece = input.nextLine();
			if (promotionPiece.equals("Q")) {
				Queen queen = new Queen(color);
				Main.board[pawnX][pawnY] = queen;
				isPromoting = false;
				break;
			}
			else if (promotionPiece.equals("N")) {
				Knight knight = new Knight(color);
				Main.board[pawnX][pawnY] = knight;
				isPromoting = false;
				break;
			}
			else if (promotionPiece.equals("R")) {
				Rook rook = new Rook(color);
				Main.board[pawnX][pawnY] = rook;
				isPromoting = false;
				break;
			}
			else if (promotionPiece.equals("B")) {
				Bishop bishop = new Bishop(color);
				Main.board[pawnX][pawnY] = bishop;
				isPromoting = false;
				break;
			}
		}
	}
	public void checkTurn() {
		System.out.println("You are in check. You need to get out of check");
		turn();
	}
	public int getKingX() {
		for (int i = 0; i<Main.board.length; i++) {
			for (int j = 0; j<Main.board.length; j++) {
				String type = Main.board[j][i].toString();
				int thisColor = Main.currentPlayer.color;
				if (Main.board[j][i].color == 0) {
					if (type.equals("K"));
					return j;
				}
				else {
					if (type.equals("k"));
					return j;
				}
			}
			System.out.println();
		}
		return (Integer) null;
	}
	public int getKingY() {
		for (int i = 0; i<Main.board.length; i++) {
			for (int j = 0; j<Main.board.length; j++) {
				String type = Main.board[j][i].toString();
				int thisColor = Main.currentPlayer.color;
				if (Main.board[j][i].color == 0) {
					if (type.equals("K"));
					return i;
				}
				else {
					if (type.equals("k"));
					return i;
				}
			}
			System.out.println();
		}
		return (Integer) null;
	}
	public boolean isChecked() {
		int x = getKingX();
		int y = getKingY();
		return allTarget(x,y);
	}
	public int getPieceColor(int x, int y, Piece[][] board) {
		//Lower case, it is black
		if (board[x][y].toString().equals(board[x][y].toString().toLowerCase())) {
			return 1;
		}
		//X, blank
		else if (board[x][y].toString().equals("X")){
			return 2;
		}
		//Upper case, it is white
		else {
			return 0;
		}
	}
	public boolean allTarget(int x, int y) {
		Piece tempBoard[][] = Main.board;
		for (int i = 0; i<Main.board.length; i++) {
			for (int j = 0; j<Main.board.length; j++) {
				if (getPieceColor(j,i,tempBoard) != color) {
					tempBoard[j][i].checkMove(j, i, x, y,tempBoard); //Moves all pieces to king
				}
			}
		}
		if (allTargetCheck(tempBoard)) {
			return true;
		}
		return false;
	}
	public boolean allTargetCheck(Piece tempBoard[][]) {
		//Checks if boards are different
		for (int i = 0; i<Main.board.length; i++) {
			for (int j = 0; j<Main.board.length; j++) {
				if (!(Main.board[j][i].toString().equals(tempBoard[j][i].toString()))) {
					return true;
				}
			}
		}
		return false;
	}
	public int moveY() {
		int moveY;
		boolean isChoosing = true;
		while(isChoosing) {
			System.out.println("To which rank or row would you like to move the piece?");
			String moveInput = input.nextLine();
			switch (moveInput) {
			case "1":
				moveY = 0;
				return moveY;
			case "2":
				moveY = 1;
				return moveY;
			case "3":
				moveY = 2;
				return moveY;
			case "4":
				moveY = 3;
				return moveY;
			case "5":
				moveY = 4;
				return moveY;
			case "6":
				moveY = 5;
				return moveY;
			case "7":
				moveY = 6;
				return moveY;
			case "8":
				moveY = 7;
				return moveY;
			default:	
				System.out.println("Invalid Square");
				break;
			}
		}
		return (Integer) null;
		
	}
	public int moveX() {
		int moveX;
		boolean isChoosing = true;
		while (isChoosing) {
			System.out.println("To which file or column would you like to move the piece?");
			String moveInput = input.nextLine();
			switch (moveInput) {
				case "a":
					moveX = 0;
					return moveX;
				case "b":
					moveX = 1;
					return moveX;
				case "c":
					moveX = 2;
					return moveX;
				case "d":
					moveX = 3;
					return moveX;
				case "e":
					moveX = 4;
					return moveX;
				case "f":
					moveX = 5;
					return moveX;
				case "g":
					moveX = 6;
					return moveX;
				case "h":
					moveX = 7;
					return moveX;
				default:
					System.out.println("Invalid Square");
					break;
			}
		}	
		return (Integer) null;
		//Moves piece to square specified	
	}
	public int getY() {
		int y;
		boolean isChoosing = true;
		while(isChoosing) {
			System.out.println("Which rank or row is your piece in?");
			String file = input.nextLine();
			switch(file) {
				//Arrays start at zero, uses the 2d array made in main
				case "1":
					y = 0;
					return y;
				case "2":
					y = 1;
					return y;
				case "3":
					y = 2;
					return y;
				case "4":
					y = 3;
					return y;
				case "5":
					y = 4;
					return y;
				case "6":
					y = 5;
					return y;
				case "7":
					y = 6;
					return y;
				case "8":
					y = 7;
					return y;
				default:
					System.out.println("Invalid position.");
					break;
			}
		}
		return (Integer) null;
	}
	public int getX() {
		int x;
		boolean isChoosing = true;
		while(isChoosing) {
			System.out.println("Which file or column is your piece in?");
			String file = input.next();
			switch(file) {
				//letters for columns
				case "a":
					x = 0;
					return x;
				case "b":
					x = 1;
					return x;
				case "c":
					x = 2;
					return x;
				case "d":
					x = 3;
					return x;
				case "e":
					x = 4;
					return x;
				case "f":
					x = 5;
					return x;
				case "g":
					x = 6;
					return x;
				case "h":
					x = 7;
					return x;
				default:
					System.out.println("Invalid position.");
					break;
			}
		}
		return (Integer) null;
	}
}
