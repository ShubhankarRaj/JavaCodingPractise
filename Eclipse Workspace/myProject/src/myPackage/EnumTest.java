package myPackage;

import java.util.Scanner;

public class EnumTest {

	enum MovieRating {E,A,B};
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String s1;
		s1 = keyboard.next();
		MovieRating rating;
		rating = MovieRating.valueOf(s1);
		
		switch (rating)
		{
		case E: //Excellent
		System.out.println("You must see this movie!");
		break;
		case A: //Average
		System.out.println("This movie is OK, but not great.");
		break;
		case B: //Bad
		System.out.println("Skip it!");
		break;
		default:
		System.out.println("Something is wrong.");
		}
	}

}
