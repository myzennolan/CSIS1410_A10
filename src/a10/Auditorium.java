package a10;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Auditorium {
	public String name;
	public Set<ShowTime> showTimes = new HashSet<ShowTime>();;
	public Integer maxCapacity;
	
	public Auditorium(String n,Integer i) {
		name = n;
		maxCapacity = i;
	}
	
	public void addShowTimes(List<ShowTime> s) {
		showTimes.addAll(s);
	}
	
	@Override
	public String toString() {
		
		StringBuilder rtn = new StringBuilder(name+" ("+maxCapacity+"):\n");
		
		for(ShowTime s: showTimes) {
			rtn.append(s.toString()+"\n");
		}
		
		return rtn.toString();
	}
	
}
