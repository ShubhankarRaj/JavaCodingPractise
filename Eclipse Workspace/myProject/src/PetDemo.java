import java.util.Scanner;

public class PetDemo extends Pet
{
	public static void main(String[] args)
	{
		Pet yourPet = new Pet(0.00);
		Pet2 newPet = new Pet2(0);
		System.out.println("My records on your pet are inaccurate.");
		System.out.println("Here is what they currently say:");
		yourPet.writeOutput();
		newPet.writeOutput();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the correct pet name:");
		String correctName = keyboard.nextLine();
		yourPet.setName(correctName);
		newPet.setName(correctName);
		System.out.println("Please enter the correct pet age:");
		int correctAge = keyboard.nextInt();
		yourPet.setAge(correctAge);
		newPet.setAge(correctAge);
		System.out.println("Please enter the correct pet weight:");
		double correctWeight = keyboard.nextDouble();
		yourPet.setWeight(correctWeight);
		newPet.setWeight(correctWeight);
		yourPet.writeOutput();
		newPet.writeOutput();
		keyboard.close();
	}
}
