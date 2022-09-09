import java.util.ArrayList;

public class ProductList {
	
	ArrayList<Food> fd = new ArrayList<Food>();
	{
		fd.add(new Food("Fries", 3.50, 20));
		fd.add(new Food("Rice", 2.00, 20));
		fd.add(new Food("Wraps", 5.00, 20));
		fd.add(new Food("Pasta", 7.50, 20));
		fd.add(new Food("Cake", 1.50, 20));
		fd.add(new Food("Taco", 6.00, 20));
		fd.add(new Food("Pizza", 6.00, 20));
		fd.add(new Food("Sushi", 6.00, 20));
		fd.add(new Food("Pie", 3.50, 20));
		fd.add(new Food("Salad", 2.50, 20));
	}
	
	ArrayList<Drink> dr = new ArrayList<Drink>();
	{	
		dr.add(new Drink("Milo", 1.50, 30));
		dr.add(new Drink("Juice", 4.50, 30));
		dr.add(new Drink("Soda", 2.50, 30));
		dr.add(new Drink("Latte", 3.50, 30));
		dr.add(new Drink("7-Up", 4.50, 30));
		dr.add(new Drink("Fanta", 1.50, 30));
		dr.add(new Drink("Tea", 1.50, 30));
		dr.add(new Drink("Milk", 1.50, 30));
		dr.add(new Drink("Latte", 2.00, 30));
		dr.add(new Drink("Cola", 2.00, 30));
		
	}
	
}
