package a10;

import javax.swing.ImageIcon;

public class Movie {
	public String name;
	public String rating;
	public String runTime;
	public String description;
	public ImageIcon poster;
	//public double ticketPrice;
	
	public Movie(String n, String d, ImageIcon p,String r,String rt) {
		name = n;
		rating = r;
		runTime = rt;
		description = d;
		poster = p;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Movie other = (Movie) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
