package test;

import game.Board;
import game.blocks.Shape;

public class TestBoard {

	public static void main(String[] args) {
		int x = 10, y = 10;
		Board b = new Board(x,y);
		b.setSlot(0, 0, (byte)10);
		System.out.println(Boolean.toString(b.checkSlot(0, 0))); 
		System.out.println(Boolean.toString(b.checkSlot(1, 1)));
		for(int i = 0; i< x; i++){
			for(int j = 0; j < y; j++){
				if(j == 0 || j == y-1){
				System.out.print("|");	
				}
				if(b.checkSlot(i, j))
					System.out.print(" ");
				else
					System.out.print("#");
			}
			System.out.println(" ");
		}
	}

}
