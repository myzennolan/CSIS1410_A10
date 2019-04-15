package a10;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * PaymentInfo class provides file saving functionality
 * 
 * @author Jennifer Snider and Nolan Harris
 *
 */
public class PaymentInfo {
	
	public PaymentInfo() {
		
	}
	
	/*
	 * Exports shoppingCart to text file
	 */
	@SuppressWarnings("hiding")
	public void processPayment(ShoppingCart sCart,String fileName) {
		
		
		boolean done = false;
		do 
		{
			try {
				PrintWriter out = new PrintWriter(fileName);
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				out.println("MovieTix" + "\r\n\r\n" + sCart.toString() + "\r\n\r\n" + "Total: " + formatter.format(sCart.getCartTotal()) + "\r\n\r\n");
				done = true;
				out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("A problem occurred: " + e.getMessage());
		}
		
		}  while(!done);	
		
		
	}
	
}
