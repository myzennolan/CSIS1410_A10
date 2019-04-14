package a10;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public void processPayment(ShoppingCart sCart) {
		
		try {
			saveRcpt(payDetails.toString()+"\n\n"+sCart.getCartTotal()+"\n\nDetails:\n"+sCart.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void saveRcpt(String content) 
		throws IOException {
		    String str = "Hello";
		    BufferedWriter writer = new BufferedWriter(new FileWriter("testfile.txt"));
		    writer.write(str);
		     
		    writer.close();
		
	}
	
}
