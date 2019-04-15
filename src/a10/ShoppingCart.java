package a10;

import java.text.NumberFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Shopping Cart abstract class. Stores selected movies and their number of tickets as a treemap for easy sorting and duplicate management.
 * 
 * 
 * 
 * @author Jennifer Snider and Nolan Harris
 *
 *@param sCart TreeMap<String,String>
 *@param cartTotal double
 *@param moviePrice double
 */
public class ShoppingCart {
	private Map<String,Integer> sCart = new TreeMap<String,Integer>();
	private static double cartTotal = 0.0d;
	private static double moviePrice = 5.0d;
	
	public ShoppingCart() {
		
	}
	
	public void addMovieTickets(String movie,Integer tickets) {
		if(sCart.containsKey(movie)) {
			sCart.replace(movie, tickets);
		}
		else {
			sCart.put(movie, tickets);
		}
	}
	
	public void resetCart() {
		sCart = new TreeMap<String,Integer>();
	}
	
	public double getCartTotal() {
		cartTotal =0.0d;
		for(Entry<String,Integer> m:sCart.entrySet()) {
			cartTotal += m.getValue()*moviePrice;
		}
		return cartTotal;
		
	}
	
	@Override
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		StringBuilder rcpt = new StringBuilder();
		for(Entry<String,Integer> m:sCart.entrySet()) {
			rcpt.append(m.getKey()+": " +m.getValue() + " x " + formatter.format(moviePrice) + " = " + formatter.format(m.getValue()*moviePrice)+"\n\n");
		}
		return rcpt.toString();
		
	}
	
}
