package a10;

import java.text.NumberFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

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
			rcpt.append(m.getKey()+"("+m.getValue()+") - "+formatter.format(m.getValue()*moviePrice)+"\n");
		}
		return rcpt.toString();
		
	}
	
}
