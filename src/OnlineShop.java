import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Scanner;

public class OnlineShop {
	
	ProductList list = new ProductList();
	OrderList order = new OrderList();
	DecimalFormat df = new DecimalFormat(".00");
	
	public void welcome() {
		Scanner input = new Scanner(System.in);
		System.out.println("***************************************************************");
		System.out.println("++                                                           ++");
		System.out.println("++-----------------WELCOME TO EVERYDAY SHOP!-----------------++");
		System.out.println("++                                                           ++");
		System.out.println("***************************************************************");

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("|\tAre you a customer or staff? \t\t|");
		System.out.println("|\t--------------------------------\t|");
		System.out.println("|\t1. Customer\t\t\t\t|");
		System.out.println("|\t2. Staff\t\t\t\t|");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter your option here: ");
		int opt = input.nextInt();
		while(opt != 1 && opt != 2) {
			System.out.println("Invalid Option!  Please re-enter your option:");
			opt = input.nextInt();
		}
		if(opt == 1) {
			customerMenu();
		}else if(opt == 2){
			System.out.print("\nPlease enter login password:");
			String password = input.next();
			while(staffLogin(password) == false) {
				System.out.print("Incorrect password! Please re-enter your password: ");
				password = input.next();
				}
			staffMenu();
			}
	}
	
	public boolean staffLogin(String passcode) {
		boolean isStaff = false;
		if(passcode.equals("admin")) {
			return true;
		}
		else
			return false;
	}

	public void customerMenu(){
		Scanner input = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("              * Main Menu *");
		System.out.println("=========================================");
		System.out.println("|\tPlease select one option: \t|");
		System.out.println("|\t-----------------------\t\t|");
		System.out.println("|\t1. Start Ordering! \t\t|");
		System.out.println("|\t2. Shopping Cart \t\t|");
		System.out.println("|\t3. Payment \t\t\t|");
		System.out.println("|\t4. Receipt \t\t\t|");
		System.out.println("|\t5. Rate Us! \t\t\t|");
		System.out.println("|\t6. Exit \t\t\t|");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter your option here: ");
		int opt = input.nextInt();
		System.out.println();
		while(opt != 1 && opt != 2 && opt != 3 && opt != 4 && opt != 5 && opt != 6) {
			System.out.println("Invalid Option!  Please re-enter your option:");
			opt = input.nextInt();
		}
		if(opt == 1) {
			orderItem();
		}
		else if(opt == 2) {
			shoppingCart();
		}
		else if(opt == 3) {
			checkout();
		}
		else if(opt == 4) {
			printReceipt();
			System.out.println("0. Exit");
			int in = input.nextInt();
			if(in == 0) {
				customerMenu();
			}
		}
		else if(opt == 5) {
			review();
		}else if(opt == 6) {
			welcome();
		}
	}
	
	public void staffMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("              * Staff Menu *");
		System.out.println("=========================================");
		System.out.println("|\tPlease select one option: \t|");
		System.out.println("|\t-----------------------\t\t|");
		System.out.println("|\t1. Manage Inventory \t\t|");
		System.out.println("|\t2. Staff Details \t\t|");
		System.out.println("|\t3. Customer Details \t\t|");
		System.out.println("|\t4. Customer Review \t\t|");
		System.out.println("|\t5. Exit \t\t\t|");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter your option here: ");
		int opt = input.nextInt();
		System.out.println();
		while(opt != 1 && opt != 2 && opt != 3 && opt != 4 && opt != 5) {
			System.out.println("Invalid Option!  Please re-enter your option:");
			opt = input.nextInt();
		}
		if(opt == 1) {
			manageInventory();
		}
		else if(opt == 2) {
			staffDetails();	
		}
		else if(opt == 3) {
			printDetails();
		}
		else if(opt == 4) {
			customerReview();
		}
		else if(opt == 5){
			welcome();
		}
	}

	public void orderItem() {
		Scanner input = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("           * Start Ordering! *");
		System.out.println("=========================================");
		System.out.println("|\tWhat do you want to order? \t|");
		System.out.println("|\t------------------------\t|");
		System.out.println("|\t1. Food \t\t\t|");
		System.out.println("|\t2. Drink \t\t\t|");
		System.out.println("|\t3. Exit \t\t\t|");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter your option here: ");
		int opt = input.nextInt();
		while(opt != 1 && opt != 2 && opt != 3) {
			System.out.println("Invalid Option!  Please re-enter your option:");
			opt = input.nextInt();
		}
		System.out.println();
		if(opt == 1) {
			System.out.println("_________________________________________________________\n");
			System.out.println("                       FOOD MENU");
			System.out.println("_________________________________________________________");
			System.out.println("|-------------------------------------------------------|");
			System.out.println("| No." + "\t| Product" + "\t| Price" + "\t\t| Quantity\t|");
			System.out.println("|-------------------------------------------------------|");
			for(int i = 0; i <list.fd.size(); i++) {
				System.out.println("| " + (i+1) + "\t| " + list.fd.get(i).name + "\t\t| " + "RM" + df.format(list.fd.get(i).price) + "\t|  " + list.fd.get(i).quantity + "\t\t|");
			}
			System.out.println("---------------------------------------------------------\n");
			
			System.out.println("Please enter the index of food item that you would like to order: ");
			int index = input.nextInt();
			while(index > list.fd.size() || index < 1) {
				System.out.println("Invalid input! Please re-enter the index: ");
				index = input.nextInt();
			}
			
			System.out.println("Please enter the quantity that you would like to order:");
			int foodQuantity = input.nextInt();
			while(foodQuantity > list.fd.get(index-1).quantity || foodQuantity<=0) {
				System.out.println("Invalid quanitity! Please enter the quantity again:");
				foodQuantity = input.nextInt();
			}
			
			order.foodList.add(new Food((list.fd.get(index-1).name), (list.fd.get(index-1).price), foodQuantity));
			list.fd.get(index-1).quantity=list.fd.get(index-1).quantity-foodQuantity;
			System.out.println("\n----------Item successfully added to the cart!----------\n");
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|\tDo you want to order more items? \t|");
			System.out.println("|\t--------------------------------\t|");
			System.out.println("|\t1. Yes\t\t\t\t\t|");
			System.out.println("|\t2. No\t\t\t\t\t|");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter your option here: ");
			int num = input.nextInt();
			while(num != 1 && num != 2 ) {
				System.out.println("Invalid Option!  Please re-enter your option:");
				num = input.nextInt();
			}
			System.out.println();
			if(num == 1) {
				orderItem(); 
			}else if(num == 2) {
				shoppingCart();    
			}
		}
		else if(opt == 2) {
			System.out.println("_________________________________________________________\n");
			System.out.println("                       DRINK MENU");
			System.out.println("_________________________________________________________");
			System.out.println("--------------------------------------------------------|");
			System.out.println("| No." + "\t| Product" + "\t| Price" + "\t\t| Quantity\t|");
			System.out.println("|-------------------------------------------------------|");
			for(int i = 0; i <list.dr.size(); i++) {
				System.out.println("| " + (i+1) + "\t| " + list.dr.get(i).name + "\t\t| " + "RM" + df.format(list.dr.get(i).price) + "\t|  " + list.dr.get(i).quantity + "\t\t|");
			}
			System.out.println("---------------------------------------------------------\n");
			
			System.out.println("Please enter the index of drink item that you would like to order: ");
			int index = input.nextInt();
			System.out.println();
			while(index > list.dr.size() || index < 1) {
				System.out.println("Invalid input! Please re-enter the index: ");
				index = input.nextInt();
			}
			
			System.out.println("Please enter the quantity that you would like to order:");
			int drinkQuantity = input.nextInt();
			while(drinkQuantity > list.dr.get(index-1).quantity || drinkQuantity<=0) {
				System.out.print("Invalid quanitity! Please enter the quantity again:");
				drinkQuantity = input.nextInt();
			}
			
			order.drinkList.add(new Drink((list.dr.get(index-1).name), (list.dr.get(index-1).price), drinkQuantity));
			list.dr.get(index-1).quantity=list.dr.get(index-1).quantity-drinkQuantity;
			System.out.println("\n----------Item successfully added to the cart!----------\n");
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|\tDo you want to order more items? \t|");
			System.out.println("|\t--------------------------------\t|");
			System.out.println("|\t1. Yes\t\t\t\t\t|");
			System.out.println("|\t2. No\t\t\t\t\t|");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter your option here: ");
			int num = input.nextInt();
			while(num != 1 && num != 2 ) {
				System.out.println("Invalid Option!  Please re-enter your option:");
				num = input.nextInt();
			}
			System.out.println();
			if(num == 1) {
				orderItem();
			}else if(num == 2) {
				shoppingCart(); 
			}
		}else if(opt == 3) {
			customerMenu();
		}

	}
	
	public void shoppingCart(){
		Scanner input = new Scanner(System.in);
        double totalPrice = 0;
		System.out.println("=================================================");
		System.out.println("                * Shopping Cart *");
		System.out.println("=================================================");
        System.out.println("| Product\t|" + " Price\t" + "\t| Quantity\t|");
        System.out.println("|------\t\t|-----\t\t|--------\t|");
        for(int i = 0; i < order.foodList.size(); i++) {
			System.out.println("| " + order.foodList.get(i).name + "\t\t| " + "RM" + df.format(order.foodList.get(i).price) + "\t|   "+ order.foodList.get(i).quantity + "\t\t|");
			totalPrice+= order.foodList.get(i).price*order.foodList.get(i).quantity;
        }
		for(int i = 0; i < order.drinkList.size(); i++) {
			System.out.println("| " + order.drinkList.get(i).name + "\t\t| " + "RM" + df.format(order.drinkList.get(i).price) + "\t|   "+ order.drinkList.get(i).quantity + "\t\t|");
			totalPrice+= order.drinkList.get(i).price*order.drinkList.get(i).quantity;
		}
		System.out.println("-------------------------------------------------");
        System.out.println("              Total Price: RM" + df.format(totalPrice));
        System.out.println("-------------------------------------------------");
		System.out.println();
	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("|\tPlease enter your choice: \t\t|");
		System.out.println("|\t--------------------------------\t|");
		System.out.println("|\t1. Make your paymentt\t\t\t|");
		System.out.println("|\t2. Edit Shopping Cart\t\t\t|");
		System.out.println("|\t3. Exit\t\t\t\t\t|");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter your option here: ");
		int choice = input.nextInt();
		while(choice != 1 && choice != 2 && choice!= 3) {
			System.out.println("Invalid Option!  Please re-enter your option:");
			choice = input.nextInt();
		}
		System.out.println();
		if(choice == 1) {
			checkout();
		}	
		else if(choice == 2){
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|\tChoose your action: \t\t\t|");
			System.out.println("|\t--------------------------------\t|");
			System.out.println("|\t1. Add items to shopping cart\t\t|");
			System.out.println("|\t2. Remove items from shopping cart\t|");
			System.out.println("|\t3. Exit\t\t\t\t\t|");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter your option here: ");
			int choice2 = input.nextInt();
			while(choice2 != 1 && choice2 != 2 && choice2 != 3) {
				System.out.println("Invalid Option!  Please re-enter your option:");
				choice = input.nextInt();
			}
			System.out.println();
			if(choice2 == 1) {
				orderItem();
			}
			else if(choice2 == 2) {
				totalPrice = 0;
				System.out.println("=================================================");
				System.out.println("                * Shopping Cart *");
				System.out.println("=================================================");
		        System.out.println("| Product\t|" + " Price\t" + "\t| Quantity\t|");
		        System.out.println("|------\t\t|-----\t\t|--------\t|");
		        for(int i = 0; i < order.foodList.size(); i++) {
					System.out.println("| " + order.foodList.get(i).name + "\t\t| " + "RM" + df.format(order.foodList.get(i).price) + "\t|   "+ order.foodList.get(i).quantity + "\t\t|");
					totalPrice+= order.foodList.get(i).price*order.foodList.get(i).quantity;
		        }
				for(int i = 0; i < order.drinkList.size(); i++) {
					System.out.println("| " + order.drinkList.get(i).name + "\t\t| " + "RM" + df.format(order.drinkList.get(i).price) + "\t|   "+ order.drinkList.get(i).quantity + "\t\t|");
					totalPrice+= order.drinkList.get(i).price*order.drinkList.get(i).quantity;
				}
				System.out.println("-------------------------------------------------");
		        System.out.println("              Total Price: RM" + df.format(totalPrice));
		        System.out.println("-------------------------------------------------");
				System.out.println();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Please enter the name of the item you would like to remove: ");
				String itemName = input.next();
				for(int i = 0; i < order.foodList.size(); i++) {
					if(itemName.equalsIgnoreCase(order.foodList.get(i).name)) {
						order.foodList.remove(i);
						System.out.println("\n-----------Item removed successfully!-----------\n");
					}
				}
				for(int i = 0; i < order.drinkList.size(); i++) {
					if(itemName.equalsIgnoreCase(order.drinkList.get(i).name)) {
						order.drinkList.remove(i);
						System.out.println("\n-----------Item removed successfully!-----------\n");
					}
				}
			System.out.println();
			shoppingCart();	
			}
			else if(choice2 == 3) {
				customerMenu();
			}
		}	
		else if(choice == 3) {
			customerMenu();
		}	
	}
	
	public void checkout() { 
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		System.out.println("=================================================");
		System.out.println("           * Make you payment here! *");
		System.out.println("=================================================");
        System.out.println("\tEnter your personal detail");
        System.out.println("\t--------------------------");
        System.out.print("Enter your name: ");
        String customername = in.nextLine();
        
        System.out.print("Delivery Address:");
        String customeraddress = in.nextLine();
        
        System.out.print("Contact number: ");
        String customercontact = in.next();
        boolean isDigit1 = true;
        char[] array1 = customercontact.toCharArray();
        for(char b : array1) {
        	if(!Character.isDigit(b)) {
				isDigit1 = false ;
			}
        }
        while(isDigit1==false) {
        	isDigit1 = true;
            System.out.print("Your contact number is invalid, please re-enter your contact number: ");
            customercontact = in.next();
            array1 = customercontact.toCharArray();
            for(char b : array1) {
            	if(!Character.isDigit(b)) {
    				isDigit1 = false ;
    			}
            }
        }

        System.out.print("Email: ");
        String customeremail = in.next();
		while (!(customeremail.charAt(0)!= '@' && customeremail.contains("@") && customeremail.endsWith(".com"))) {
			System.out.print("Please enter a valid email address: ");
			customeremail = in.next();
		}
		
        System.out.println("Enter the choice of your payment method: ");
        System.out.println("---------------------------------------");
        System.out.println("\t1. Credit or Debit card");
        System.out.println("\t2. Cash");
        System.out.print("Enter your option here: ");
        int payment = in.nextInt();
        while(payment != 1 && payment != 2) {
        	System.out.print("Invalid option ! Please re-enter your option again: ");
        	payment = in.nextInt();
        }
        if(payment == 1) {
        	boolean isDigit = true;
			System.out.print("Enter the name on your card: ");
            String cardname = in1.nextLine();
            System.out.print("Enter your card number: ");
            String cardnumber = in.next();
            char[] array = cardnumber.toCharArray();
            for(char c: array) {
    			if(!Character.isDigit(c)) {
    				isDigit = false ;
    			}
    		}
            while(cardnumber.length()!=16 || isDigit==false) {
            	isDigit = true;
                System.out.print("Your card number is invalid, please re-enter your card number: ");
                cardnumber = in.next();
            }
            System.out.println("Enter the expiry date of your card (mm/yy): ");
            System.out.print("Year: ");
            int expiryyear = in.nextInt();
            System.out.print("Month: ");
            int expirymonth = in.nextInt();
            System.out.print("Security code: ");
            int cvv = in.nextInt();
            while(String.valueOf(cvv).length() != 3) {
                System.out.println("Your security code is invalid, please re-enter your security code: ");
                 cvv = in.nextInt();
                
            	}
            System.out.println();
            System.out.println("*************************************************");
            System.out.println("             Please check your detail");
            System.out.println("*************************************************");
            System.out.println("\tName: " + customername);
            System.out.println("\tDelivery Address: " + customeraddress);
            System.out.println("\tContact number: " + customercontact);
            System.out.println("\tEmail: " + customeremail);
            System.out.println("\tName of cardholder: " + cardname);
            System.out.println("\tCard number: " + cardnumber);
            System.out.println("**************************************************");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|\tDo you want to re-enter your details? \t|");
			System.out.println("|\t--------------------------------\t|");
			System.out.println("|\t1. Yes\t\t\t\t\t|");
			System.out.println("|\t2. No\t\t\t\t\t|");
			System.out.println("|\t3. Exit\t\t\t\t\t|");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter your option here: ");
			int reenter = in.nextInt();
			System.out.println();
			while(reenter != 1 && reenter != 2 &&reenter != 3) {
				System.out.println("Invalid Option!  Please re-enter your option:");
				reenter = in.nextInt();
			}
            if(reenter == 1) {
            	checkout();
            }
            else if (reenter ==2) { 
                double totalPrice = 0;
                System.out.println("==========================================");
                System.out.println("                My Orders");
                System.out.println("==========================================");
                System.out.println("Product\t" + "\tPrice\t" + "\tQuantity");
                System.out.println("------\t\t-----\t\t--------");
                for(int i = 0; i < order.foodList.size(); i++) {
        			System.out.println(order.foodList.get(i).name + "\t\t" + "RM" + df.format(order.foodList.get(i).price) + "\t\t   "+ order.foodList.get(i).quantity);
        			totalPrice+= order.foodList.get(i).price*order.foodList.get(i).quantity;
                }
        		for(int i = 0; i < order.drinkList.size(); i++) {
        				System.out.println(order.drinkList.get(i).name + "\t\t" + "RM" + df.format(order.drinkList.get(i).price) + "\t\t   " + order.drinkList.get(i).quantity);
        				totalPrice+= order.drinkList.get(i).price*order.drinkList.get(i).quantity;
        		}
        		System.out.println();
        		System.out.println("------------------------------------------");
                System.out.println("            Total Price: RM" + df.format(totalPrice));
                System.out.println("------------------------------------------");
        		System.out.println();
            	System.out.println("-----Your order is successfully placed-----");
            }
            else if(reenter == 3) {
            	customerMenu();
            }
           }
        else if(payment == 2) {
        	 System.out.println("*************************************************");
             System.out.println("             Please check your detail");
             System.out.println("*************************************************");
             System.out.println("\tName: " + customername);
             System.out.println("\tDelivery Address: " + customeraddress);
             System.out.println("\tContact number: " + customercontact);
             System.out.println("\tEmail: " + customeremail);
             System.out.println("**************************************************");
 			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
 			System.out.println("|\tDo you want to re-enter your details? \t|");
 			System.out.println("|\t--------------------------------\t|");
 			System.out.println("|\t1. Yes\t\t\t\t\t|");
 			System.out.println("|\t2. No\t\t\t\t\t|");
 			System.out.println("|\t3. Exit\t\t\t\t\t|");
 			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
 			System.out.print("Enter your option here: ");
 			int reenter2 = in.nextInt();
 			System.out.println();
			while(reenter2 != 1 && reenter2 != 2 &&reenter2 != 3) {
				System.out.println("Invalid Option!  Please re-enter your option:");
				reenter2 = in.nextInt();
			}
            if(reenter2 == 1) {
            	checkout();
            }
            else if (reenter2 ==2) { 
                double totalPrice = 0;
				System.out.println("=================================================");
				System.out.println("                * My Orders *");
				System.out.println("=================================================");
		        System.out.println("| Product\t|" + " Price\t" + "\t| Quantity\t|");
		        System.out.println("|------\t\t|-----\t\t|--------\t|");
		        for(int i = 0; i < order.foodList.size(); i++) {
					System.out.println("| " + order.foodList.get(i).name + "\t\t| " + "RM" + df.format(order.foodList.get(i).price) + "\t|   "+ order.foodList.get(i).quantity + "\t\t|");
					totalPrice+= order.foodList.get(i).price*order.foodList.get(i).quantity;
		        }
				for(int i = 0; i < order.drinkList.size(); i++) {
					System.out.println("| " + order.drinkList.get(i).name + "\t\t| " + "RM" + df.format(order.drinkList.get(i).price) + "\t|   "+ order.drinkList.get(i).quantity + "\t\t|");
					totalPrice+= order.drinkList.get(i).price*order.drinkList.get(i).quantity;
				}
				System.out.println("-------------------------------------------------");
		        System.out.println("              Total Price: RM" + df.format(totalPrice));
		        System.out.println("-------------------------------------------------");
				System.out.println();
            	System.out.println("-----Your order is successfully placed-----");
            }
            else if (reenter2 == 3) {
            	customerMenu();
            }
           }
       
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("|\tDo you need a receipt? \t\t\t|");
		System.out.println("|\t--------------------------------\t|");
		System.out.println("|\t1. Yes\t\t\t\t\t|");
		System.out.println("|\t2. No\t\t\t\t\t|");
		System.out.println("|\t3. Exit\t\t\t\t\t|");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter your option here: ");
		int receipt1 = in.nextInt();
		while(receipt1 != 1 && receipt1 != 2 && receipt1!= 3) {
			System.out.println("Invalid Option!  Please re-enter your option:");
			receipt1 = in.nextInt();
		}
		System.out.println();
        if(receipt1 == 1) {
        	customerDetails(customername, customeraddress, customercontact, customeremail);
        	printReceipt();
        	review();
        }
        else if(receipt1 == 2) {
        	review();
        }
        else if(receipt1 == 3 ) {
        	customerMenu();
        }
        
    
	}
	
	public void customerDetails(String customername, String customeraddress, String customercontact, String customeremail) {
		 double totalPrice = 0;
	        File receiptFile = new File("Receipt.txt");
			try {
				FileWriter receipt = new FileWriter(receiptFile);
				receipt.write("Name: ");
				receipt.write(customername);
				receipt.write("\nDelivery Address: ");
				receipt.write(customeraddress);
				receipt.write("\nContact Number: ");
				receipt.write(customercontact);
				receipt.write("\nEmail: ");
				receipt.write(customeremail);
				receipt.write("\n\n=================================================\n");
				receipt.write("                * Orders *\n");
				receipt.write("=================================================\n");
				receipt.write("| Product\t|" + " Price\t" + "\t| Quantity\t|\n");
			    receipt.write("|------\t\t|-----\t\t|--------\t|\n");
			        for(int i = 0; i < order.foodList.size(); i++) {
						receipt.write("| " + order.foodList.get(i).name + "\t\t| " + "RM" + df.format(order.foodList.get(i).price) + "\t|   "+ order.foodList.get(i).quantity + "\t\t|\n");
						totalPrice+= order.foodList.get(i).price*order.foodList.get(i).quantity;
			        }
					for(int i = 0; i < order.drinkList.size(); i++) {
						receipt.write("| " + order.drinkList.get(i).name + "\t\t| " + "RM" + df.format(order.drinkList.get(i).price) + "\t|   "+ order.drinkList.get(i).quantity + "\t\t|\n");
						totalPrice+= order.drinkList.get(i).price*order.drinkList.get(i).quantity;
					}
					receipt.write("-------------------------------------------------\n");
					receipt.write("              Total Price: RM" + df.format(totalPrice));
					receipt.write("\n-------------------------------------------------\n");
					receipt.write("             Hope to see you again!");
					receipt.write("\n\n\n");
				receipt.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void printReceipt() {
		System.out.println("***************************************************\n");
		System.out.println("\t\t    RECEIPT\n");
		System.out.println("***************************************************\n");
		File inputFile = new File("Receipt.txt");
		try {
        	FileReader readFile = new FileReader(inputFile);
    		int c = 0;
    		while((c=readFile.read())!=-1) {
    			System.out.print((char) c);
    		}
    		readFile.close();
    		
    	}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void review() {
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);

		System.out.println("===========================================");
		System.out.println("                 * Rate Us! *");
		System.out.println("===========================================");
		System.out.println("\n\tThanks for shopping with us! ");
		System.out.println("\tPlease leave us a review ...\n");
		System.out.print("Your name: ");
		String fdbackname = in.nextLine();
		System.out.print("Your email address: ");
		String fdbackemail = in.next();
		while (!(fdbackemail.charAt(0) != '@' && fdbackemail.contains("@") && fdbackemail.endsWith(".com"))) {

			System.out.print("Please enter a valid email address: ");
			fdbackemail = in.next();
		}
		System.out.println("\nOut of five stars, how much would you rate us? ");
		System.out.println("You may also share your thoughts with us! ");

		String reviews;
		int ratings;
		System.out.println("\t+=========================+");
		System.out.println("\t    1   2   3   4   5    ");
		System.out.println("\t+=========================+");
		System.out.print("\nRating: ");
		ratings = in.nextInt();
		System.out.print("Feedback: ");
		reviews = in2.nextLine();

		boolean flag = false;
		while (flag == false) {
			if (ratings < 1 || ratings > 5) {

				System.out.print("Please select only one to five : ");
				ratings = in.nextInt();
			}

			else {
				System.out.println("\n-----Thank you for rating us, we hope to see you again !-----\n");
				flag = true;
			}

		}
		
		File file = new File("Review.txt");
		try {
			FileWriter fileWrite = new FileWriter(file);
			fileWrite.write("Name: ");
			fileWrite.write(fdbackname);
			fileWrite.write("\nEmail Address: ");
			fileWrite.write(fdbackemail);
			fileWrite.write("\nRating: ");
			fileWrite.write(String.valueOf(ratings));
			fileWrite.write("\nFeedback: ");
			fileWrite.write(reviews + "\n");
			fileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("0. Exit");
		int exit = in.nextInt();
		if(exit == 0) {
			customerMenu();
		}


	}
	
	///////////////////////////////////////////////////////////////////
	//                         Staff Menu                            //
	///////////////////////////////////////////////////////////////////
	
	public void manageInventory() {
		Scanner in = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("           * Manage Inventory *");
		System.out.println("=========================================");
		System.out.println("|\tPlease select one inventory:  \t|");
		System.out.println("|\t------------------------\t|");
		System.out.println("|\t1. Food \t\t\t|");
		System.out.println("|\t2. Drink \t\t\t|");
		System.out.println("|\t3. Exit \t\t\t|");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter your option here: ");
		int inv = in.nextInt();
		while(inv != 1 && inv != 2 && inv != 3) {
			System.out.println("Invalid Option!  Please re-enter your option:");
			inv = in.nextInt();
		}
		if(inv == 1) {
			foodInventory();
		}else if(inv == 2) {
			drinkInventory();
		}else if(inv == 3) {
			staffMenu();
		}

	}

	public void foodInventory() {
		Scanner s = new Scanner(System.in);
		int m;
		do {
			System.out.println("========================================================================");
			System.out.println("                    * Food Inventory Management System *");
			System.out.println("========================================================================");
			System.out.println("|\t\t\tPlease select one option:  \t\t\t|");
			System.out.println("|\t\t\t------------------------\t\t\t|");
			System.out.println("|\t\t\t1. Display Inventory \t\t\t\t|");
			System.out.println("|\t\t\t2. Insert Inventory \t\t\t\t|");
			System.out.println("|\t\t\t3. Remove Inventory \t\t\t\t|");
			System.out.println("|\t\t\t4. Update Inventory \t\t\t\t|");
			System.out.println("|\t\t\t5. Exit \t\t\t\t\t|");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter your Option: ");
			m = s.nextInt();
			System.out.println();
			
			switch (m) {
			case 1:
				System.out.println("=================================================");
				System.out.println("                   Stock Details");
				System.out.println("=================================================");
				System.out.println("-------------------------------------------------");
				System.out.println( "| Product" + "\t| Price" + "\t\t| Quantity\t|");
				System.out.println("|-----------------------------------------------|");
				Iterator<Food> li = list.fd.iterator();
				while (li.hasNext()) {
					Food f = (Food) li.next();
					System.out.println("| " +f.getName() + "\t\t| RM" + df.format(f.getPrice()) + "\t|   " + f.getQuantity() + "\t\t|");
				}
				System.out.println("-------------------------------------------------\n");
				break;

			case 2:
				System.out.println("=================================================");
				System.out.println("                  Insert Inventory");
				System.out.println("=================================================");
				System.out.print("Enter Product Name: ");
				String name = s.next();
				System.out.print("Enter Price: ");
				double price = s.nextDouble();
				System.out.print("Enter Quantity: ");
				int quantity = s.nextInt();
				list.fd.add(new Food(name, price, quantity));
				System.out.println("\n----------Item inserted successfully!----------\n");
				break;

			case 3:
				System.out.println("_________________________________________________________\n");
				System.out.println("                       FOOD MENU");
				System.out.println("_________________________________________________________");
				System.out.println("|-------------------------------------------------------|");
				System.out.println("| No." + "\t| Product" + "\t| Price" + "\t\t| Quantity\t|");
				System.out.println("|-------------------------------------------------------|");
				for(int i = 0; i <list.fd.size(); i++) {
					System.out.println("| " + (i+1) + "\t| " + list.fd.get(i).name + "\t\t| " + "RM" + df.format(list.fd.get(i).price) + "\t|  " + list.fd.get(i).quantity + "\t\t|");
				}
				System.out.println("---------------------------------------------------------\n");
				
				System.out.println("Please enter the index of food that you want to remove:");
				int remove = s.nextInt();
				int index = remove - 1;
				list.fd.remove(index);
				System.out.println("\n----------Item removed successfully!----------\n");
				break;

			case 4:
				System.out.println("_________________________________________________________\n");
				System.out.println("                       FOOD MENU");
				System.out.println("_________________________________________________________");
				System.out.println("|-------------------------------------------------------|");
				System.out.println("| No." + "\t| Product" + "\t| Price" + "\t\t| Quantity\t|");
				System.out.println("|-------------------------------------------------------|");
				for (int i = 0; i < list.fd.size(); i++) {
					System.out.println("| " + (i + 1) + "\t| " + list.fd.get(i).name + "\t\t| " + "RM"
							+ df.format(list.fd.get(i).price) + "\t|  " + list.fd.get(i).quantity + "\t\t|");
				}
				System.out.println("---------------------------------------------------------\n");
				System.out.println("\nPlease enter index of food item that you would like to update: ");
				int index2 = s.nextInt();
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("|\tDo you want to update the PRICE or the QUANTITY of the item?    |");
				System.out.println("|\t------------------------------------------------------------\t|");
				System.out.println("|\t\t\t1. Price\t\t\t\t\t|");
				System.out.println("|\t\t\t2. Quantity\t\t\t\t\t|");
				System.out.println("|\t\t\t3. Both\t\t\t\t\t\t|");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("Enter your option here: ");
				int update = s.nextInt();
				System.out.println();
				while (update != 1 && update != 2 && update != 3) {
					System.out.println("Invalid Option! Please re-enter your option again:");
					update = s.nextInt();
				}
				if (update == 1) {
					System.out.print("Please enter the new price:");
					double newprice = s.nextDouble();
					System.out.println("\n----------Price updated successfully!----------\n");
					list.fd.get(index2 - 1).setPrice(newprice);
				} else if (update == 2) {
					System.out.print("Please enter the new quantity:");
					int newquantity = s.nextInt();
					System.out.println("\n----------Quantity updated successfully!----------\n");
					list.fd.get(index2 - 1).setQuantity(newquantity);
				} else if (update == 3) {
					System.out.print("Please enter the new price:");
					double newprice = s.nextDouble();
					list.fd.get(index2 - 1).setPrice(newprice);
					System.out.print("Please enter the new quantity:");
					int newquantity = s.nextInt();
					list.fd.get(index2 - 1).setQuantity(newquantity);
					System.out.println("\n----------Price and Quantity updated successfully!----------\n");
				}
				break;
				
			}

		} while (m != 5);
		{
			manageInventory();
		}
		;
	}
	
	public void drinkInventory() {
		Scanner s = new Scanner(System.in);
		int m;
		do {
			System.out.println("========================================================================");
			System.out.println("                    * Drink Inventory Management System *");
			System.out.println("========================================================================");
			System.out.println("|\t\t\tPlease select one option:  \t\t\t|");
			System.out.println("|\t\t\t------------------------\t\t\t|");
			System.out.println("|\t\t\t1. Display Inventory \t\t\t\t|");
			System.out.println("|\t\t\t2. Insert Inventory \t\t\t\t|");
			System.out.println("|\t\t\t3. Remove Inventory \t\t\t\t|");
			System.out.println("|\t\t\t4. Update Inventory \t\t\t\t|");
			System.out.println("|\t\t\t5. Exit \t\t\t\t\t|");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter your Option: ");
			m = s.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				System.out.println("=================================================");
				System.out.println("                   Stock Details");
				System.out.println("=================================================");
				System.out.println("-------------------------------------------------");
				System.out.println( "| Product" + "\t| Price" + "\t\t| Quantity\t|");
				System.out.println("|-----------------------------------------------|");
				Iterator<Drink> itr = list.dr.iterator();
				while (itr.hasNext()) {
					Drink d = (Drink) itr.next();
					System.out.println("| " +d.getName() + "\t\t| RM" + df.format(d.getPrice()) + "\t|   " + d.getQuantity() + "\t\t|");
				}
				System.out.println("-------------------------------------------------\n");
				break;
			case 2:
				System.out.println("=================================================");
				System.out.println("                  Insert Inventory");
				System.out.println("=================================================");
				System.out.print("Enter Product Name: ");
	    		String name = s.next();
	    		System.out.print("Enter Price: ");
	    		double price = s.nextDouble();
	    		System.out.print("Enter Quantity: ");
	    		int quantity = s.nextInt();
	    		list.dr.add(new Drink(name, price, quantity));
				System.out.println("\n----------Item inserted successfully!----------\n");
	    		break;
			case 3:
				System.out.println("=========================================================");
				System.out.println("                      Remove Inventory");
				System.out.println("=========================================================");
				System.out.println("_________________________________________________________\n");
				System.out.println("                       DRINK MENU");
				System.out.println("_________________________________________________________");
				System.out.println("---------------------------------------------------------");
				System.out.println("| No." + "\t| Product" + "\t| Price" + "\t\t| Quantity\t|");
				System.out.println("|-------------------------------------------------------|");
				for(int i = 0; i <list.dr.size(); i++) {
					System.out.println("| " + (i+1) + "\t| " + list.dr.get(i).name + "\t\t| " + "RM" + df.format(list.dr.get(i).price) + "\t|  " + list.dr.get(i).quantity + "\t\t|");
				}
				System.out.println("---------------------------------------------------------\n");
				System.out.println("Please enter the index of drink that you want to remove:");
				int remove = s.nextInt();
				int index = remove - 1;
				list.dr.remove(index);
				System.out.println("\n----------Item removed successfully!----------\n");
				break;
			case 4:
				System.out.println("_________________________________________________________\n");
				System.out.println("                       DRINK MENU");
				System.out.println("_________________________________________________________");
				System.out.println("--------------------------------------------------------|");
				System.out.println("| No." + "\t| Product" + "\t| Price" + "\t\t| Quantity\t|");
				System.out.println("|-------------------------------------------------------|");
				for(int i = 0; i <list.dr.size(); i++) {
					System.out.println("| " + (i+1) + "\t| " + list.dr.get(i).name + "\t\t| " + "RM" + df.format(list.dr.get(i).price) + "\t|  " + list.dr.get(i).quantity + "\t\t|");
				}
				System.out.println("---------------------------------------------------------\n");
				System.out.println("\nPlease enter index of drink item that you would like to update: ");
				int index2 = s.nextInt();
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("|\tDo you want to update the PRICE or the QUANTITY of the item?    |");
				System.out.println("|\t------------------------------------------------------------\t|");
				System.out.println("|\t\t\t1. Price\t\t\t\t\t|");
				System.out.println("|\t\t\t2. Quantity\t\t\t\t\t|");
				System.out.println("|\t\t\t3. Both\t\t\t\t\t\t|");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("Enter your option here: ");
				int update = s.nextInt();
				System.out.println();
				while(update != 1 && update != 2 && update != 3) {
					System.out.println("Invalid Option! Please re-enter your option again:");
					update = s.nextInt();
				}
				if (update == 1) {
					System.out.print("Please enter the new price:");
					double newprice = s.nextDouble();
					System.out.println("\n----------Price updated successfully!----------\n");
					list.dr.get(index2 - 1).setPrice(newprice);
				} else if (update == 2) {
					System.out.println("Please enter the new quantity:");
					int newquantity = s.nextInt();
					System.out.println("\n----------Quantity updated successfully!----------\n");
					list.dr.get(index2 - 1).setQuantity(newquantity);
				} else if (update == 3) {
					System.out.print("Please enter the new price:");
					double newprice = s.nextDouble();
					list.dr.get(index2 - 1).setPrice(newprice);
					System.out.print("Please enter the new quantity:");
					int newquantity = s.nextInt();
					list.dr.get(index2 - 1).setQuantity(newquantity);
					System.out.println("\n----------Price and Quantity updated successfully!----------\n");

				}
				break;
				
			}
		}while (m != 5);
			{
				manageInventory();
			}
   		
	}

	public void staffDetails() {
		Scanner in = new Scanner(System.in);
		String[][] StaffList = {

				{ "| Tan Jun Yuan    \t ", "| 0342353\t", "| junyuan.tan05@sd.taylors.edu.my\t  |" },
				{ "| Ethan Lee       \t ", "| 0345841\t", "| khoochuen.ethanlee@sd.taylors.edu.my    |" },
				{ "| Tan Yi Yuan     \t ", "| 0344346\t", "| yiyuan.tan@sd.taylors.edu.my\t\t  |" },
				{ "| Choo Yong Hann  \t ", "| 0352381\t", "| yonghann.choo@sd.taylors.edu.my\t  |" },
				{ "| Chia Zheng Chean\t ", "| 0343579\t", "| zhengchean.chia@sd.taylors.edu.my\t  |" },
				{ "| Pong Jing Xuan  \t ", "| 0344435\t", "| jingxuan.pong@sd.taylors.edu.my\t  |" },

		};
		System.out.println("===================================================================================");
		System.out.println("                                  Staff Details");
		System.out.println("===================================================================================");
		System.out.println("| Name\t\t\t | Staff ID\t| Email Address\t\t\t\t  |");
		System.out.println("|---------------------------------------------------------------------------------|");
		for (int i = 0; i < StaffList.length; i++) {
			for (int j = 0; j < StaffList[i].length; j++) {
				System.out.print(StaffList[i][j]);

			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------------\n");
		System.out.println("0. Exit");
		int exit = in.nextInt();
		while (exit != 0) {
			System.out.println("Invalid input! Please enter your input again:");
			exit = in.nextInt();
		}
		staffMenu();
	}
	
	public void printDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("***************************************************\n");
		System.out.println("\t\tCustomer's Details\n");
		System.out.println("***************************************************\n");
		File inputFile = new File("Receipt.txt");
		try {
			FileReader readFile = new FileReader(inputFile);
			int c = 0;
			while ((c = readFile.read()) != -1) {
				System.out.print((char) c);
			}
			readFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("0. Exit");
		int opt = input.nextInt();
		if (opt == 0) {
			staffMenu();
		}

	}
	
	public void customerReview() {
		Scanner input = new Scanner(System.in);
		System.out.println("===========================================");
		System.out.println("            * Customer's Review *");
		System.out.println("===========================================");
    	File inputFile = new File("Review.txt");
    	try {
        	FileReader readFile = new FileReader(inputFile);
    		int c = 0;
    		while((c=readFile.read())!=-1) {
    			System.out.print((char) c);
    		}
    		readFile.close();
    		
    	}catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println();
    	System.out.println("0. Exit");
		int opt = input.nextInt();
		if (opt == 0) {
			staffMenu();
		}
	}
	

}
