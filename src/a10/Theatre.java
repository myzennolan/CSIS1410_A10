package a10;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class Theatre {
	public String name;
	public Map<LocalDateTime,Movie> showTimes = new TreeMap<>();
	public Map<LocalDateTime,Integer> soldTickets = new TreeMap<>();
	public Integer maxCapacity;
	
	public Theatre(String n,Integer i) {
		name = n;
		maxCapacity = i;
	}
	
	public void addShowTimes(Map<LocalDateTime,Movie> s,Map<LocalDateTime,Integer> t) {
		showTimes.putAll(s);
		soldTickets.putAll(t);
	}
	
	@Override
	public String toString() {
		
		StringBuilder rtn = new StringBuilder(name+" ("+maxCapacity+"):\n");
		
		for(Map.Entry<LocalDateTime, Movie> st:showTimes.entrySet()) {
			rtn.append(st.getKey().format(DateTimeFormatter.ISO_LOCAL_DATE));
			rtn.append(" @ ");
			rtn.append(st.getKey().format(DateTimeFormatter.ISO_LOCAL_TIME));
			rtn.append(" - ");
			rtn.append(" - "+soldTickets.get(st.getKey())+"/"+maxCapacity);
			rtn.append(" - ");
			rtn.append(st.getValue().name.replace("\"", ""));
			rtn.append("\n");
		}
		
		return rtn.toString().replace(":00 ", " ");
	}

	public boolean buyTickets(LocalDateTime key, Integer qty) {
		if(soldTickets.get(key)+qty <= maxCapacity) {
			soldTickets.replace(key, soldTickets.get(key)+qty);
			return true;
		}
		else {
			return false;
		}
		
	}

	public Integer remainingTickets(LocalDateTime key) {
		
		return (maxCapacity-soldTickets.get(key));
	}

}
