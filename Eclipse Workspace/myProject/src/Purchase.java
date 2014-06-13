
import java.util.Scanner;

public class Purchase {

	private String name;
	private int quantity;
	private double costOfItem;
	private double rateOfItem;
	private int itemCountPurchased;
	private double purchaseAmount;
	
	public void SetName(String newItemName){
		name = newItemName;
		
	}
	public double SetPrice(int quant, double rate){
		quantity = quant;
		costOfItem = rate;
		return rateOfItem = costOfItem/quantity;
			
	}
	public int SetNumberBought(int number){
		return itemCountPurchased = number;
		
	}
	public void readInput(){
		
		System.out.println("Welcome to Purchase.");
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the name of the item");
		String itemName = keyboard.next();
		SetName(itemName);
		
		System.out.println("Enter the rate of the item in Quantity and Cost format");
		System.out.println("For e.g., if 3 apples cost Rs 12, then enter"+"'3 12.00'"+".");
		int itemQuant = keyboard.nextInt();
		double itemCost = keyboard.nextDouble();
		while(itemQuant<=0){
			System.out.println("Kindly enter a correct value for Quantity of "+name);
			System.out.println("New correct Quantity");
			itemQuant = keyboard.nextInt();
		}
		while(itemCost<=0){
			System.out.println("Kindly enter a correct value for Rate of "+name);
			System.out.println("New correct Rate");
			itemCost = keyboard.nextInt();
		}
		SetPrice(itemQuant, itemCost);
		
		System.out.println("Enter the number of "+name+" that you want to purchase:");
		int count = keyboard.nextInt();
		while(count<=0){
			System.out.println("Kindly enter a correct count of "+name);
			System.out.println("New correct COUNT");
			count = keyboard.nextInt();
		}
		SetNumberBought(count);
		
		keyboard.close();
		
	}
	public void writeOutput(){
		
		System.out.println("Thank You for purchasing from us");
		getTotalCost(itemCountPurchased, rateOfItem);
		System.out.println("Your TOTAL purchase amount for item "+name+" is :"+purchaseAmount);
	}
	public double getTotalCost(int itemCount, double itemRate){
		return purchaseAmount = itemCountPurchased * rateOfItem;
//		System.out.println("Your TOTAL purchase amount for item "+name+" is :"+purchaseAmount);
	}
}
