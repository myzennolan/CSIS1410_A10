package a10;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class PaymentInfo {
	public Map<String,String> payDetails = new HashMap<String,String>();
	
	public PaymentInfo() {
		
	}
	
	public void setDetails(String key, String value) {

		if(payDetails.containsKey(key)) {
			payDetails.replace(key, value);
		}
		else {
			payDetails.put(key, value);
		}
	}
	
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
	
	public void saveRcpt(String content) 
		throws IOException {
		    String str = "Hello";
		    BufferedWriter rcptWriter = new BufferedWriter(new FileWriter("testfile.txt"));
		    rcptWriter.write(str);
		     
		    rcptWriter.close();
		
	}
	
}
