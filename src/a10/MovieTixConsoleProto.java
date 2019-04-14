package a10;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import a10.unused.Theatre;

public class MovieTixConsoleProto {
	
	public static Theatre the1 = new Theatre("Dimension 35C", 12);
	public static Theatre the2 = new Theatre("Earth C-137", 20);
	public static Map<LocalDateTime,Movie> showTimes = new TreeMap<>();
	public static Map<LocalDateTime,Integer> showTimesTickets = new TreeMap<>();
	
	//public static Sale sale = new Sale();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Movie> movies = movieLoader();
		
		for(Movie m : movies) {
			for(int i=0;i<=24/movies.size();i++) {
				Integer hour = new Random().nextInt(23)/2*2;
				showTimes.put(LocalDateTime.of(2019, 04,27,hour,15),m);
				showTimesTickets.put(LocalDateTime.of(2019, 04,27,hour,15),0);
				
			}
		}
		
		the1.addShowTimes(showTimes,showTimesTickets);

		System.out.println(the1.toString());
		
		
		//Buy Tickets
		
		
		for(Map.Entry<LocalDateTime, Movie> e : the1.showTimes.entrySet()) {
			Integer qty = new Random().nextInt(13);

			System.out.println("Attempting to buy "+qty+" of "+the1.remainingTickets(e.getKey())+" "+(the1.buyTickets(e.getKey(),qty) ? "Success":"Failure"));
			the1.buyTickets(e.getKey(),qty);
		}

		System.out.println(the1.toString());
		
		
		for(Map.Entry<LocalDateTime, Movie> e : the1.showTimes.entrySet()) {
			Integer qty = new Random().nextInt(13);

			System.out.println("Attempting to buy "+qty+" of "+the1.remainingTickets(e.getKey())+" "+(the1.buyTickets(e.getKey(),qty) ? "Success":"Failure"));
			
			the1.buyTickets(e.getKey(),qty);
		}
		
		System.out.println(the1.toString());
		
	}
	
	public static ArrayList<Movie> movieLoader(){

		URL tsvFile = MovieTixConsoleProto.class.getResource("/resources/movies.tsv");
        BufferedReader br = null;
        String line = ""; // line storage
        String cvsSplitBy = "\t";   // use tab as separator
        ArrayList<Movie> movies = new ArrayList<Movie>();

        try {

            br = new BufferedReader(new FileReader(tsvFile.getFile()));
            while ((line = br.readLine()) != null) {

              
                String[] movie = line.split(cvsSplitBy);
                movies.add(new Movie(movie[0],movie[1],new ImageIcon(MovieTixConsoleProto.class.getResource(movie[4])),movie[2],movie[3]));
                //System.out.println(movie[0] + "\n" + movie[1] + "\n" + movie[2] + "\n" + movie[3] + "\n" + movie[4] + "\n\n");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Useless Gesture
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return movies;
		
	}

}
