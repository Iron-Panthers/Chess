package board;
import java.util.Scanner;

import pieces.Piece;
import pieces.PieceType;

public class Player {
	Scanner input;
	public int color;
	public char[] pInputArray;
	
	public Player(int color) {
		this.color = color;
		input = new Scanner(System.in);
		//0 = white, 1 = black
	}
	public void turn() {
		//Asks for move
		Main.display();
		boolean isChoosing = true;
		while(isChoosing) {
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
			Main.board[x][y].checkMove(x, y, moveX, moveY);
			isChoosing = false;
		}
	}
	public void checkTurn() {
		System.out.println("You are in check. You need to get out of check");
	}
	public int getKingX() {
		for (int i = 0; i<Main.board.length; i++) {
			for (int j = 0; j<Main.board.length; j++) {
				String type = Main.board[j][i].toString();
				int thisColor = Main.currentPlayer.color;
				if ((Main.board[j][i].color == thisColor)&&(type.equals("K"))) {
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
				if ((Main.board[j][i].color == thisColor)&&(type.equals("K"))) {
					return i;
				}
			}
		}
		return (Integer) null;
	}
	public boolean isChecked() {
		int x = getKingX();
		int y = getKingY();
		return allTarget(x,y);
	}
	public boolean allTarget(int x, int y) {
		Piece tempBoard[][] = Main.board;
		for (int i = 0; i<Main.board.length; i++) {
			for (int j = 0; j<Main.board.length; j++) {
				if (tempBoard[j][i].color != color) {
					tempBoard[j][i].checkMove(j, i, x, y); //Moves all pieces to king
				}
			}
		}
		if (allTargetCheck(tempBoard)) {
			return true;
		}
		return false;
	}
	public boolean allTargetCheck(Piece tempBoard[][]) {
		for (int i = 0; i<Main.board.length; i++) {
			for (int j = 0; j<Main.board.length; j++) {
				if (!((Main.board[j][i].color == tempBoard[j][i].color)&&(Main.board[j][i].type == tempBoard[j][i].type))) {
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
