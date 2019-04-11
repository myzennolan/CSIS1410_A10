package a10;

import java.util.HashMap;
import java.util.Map;

public class Sale {
	Map<ShowTime,Integer> showMap = new HashMap<ShowTime,Integer>();
	
	public Sale() {
		
	}
	
	public boolean addTickets(ShowTime s,Integer qty) {
		if(s.auditTickets(qty)) {
			if(showMap.containsKey(s)) {
				showMap.replace(s, showMap.get(s)+qty);
			}
			else {
				showMap.put(s,qty);
			}
		}else {
			System.out.println(s.ticketsSold);
			
		}
		return s.auditTickets(qty);		
	}
}
