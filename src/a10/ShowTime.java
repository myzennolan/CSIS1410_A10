package a10;

import java.time.LocalTime;

public class ShowTime {
	public Movie movie;
	public LocalTime	startTime;
	public Auditorium aud;
	public Integer ticketsSold =0;
	
	public ShowTime(Movie m, LocalTime t, Auditorium a) {
		movie = m;
		startTime = t;
		aud = a;
		
	}
	
	public boolean auditTickets(Integer s) {
		if(aud.maxCapacity-ticketsSold-s >= 0) {
			ticketsSold += s;
			return true;
		}
		else {

			return false;
		}
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowTime other = (ShowTime) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}



	@Override 
	public String toString() {
		return startTime.toString() + " - " +movie.name;
	}
}
