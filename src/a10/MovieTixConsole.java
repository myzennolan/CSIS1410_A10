package a10;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;


/**
 * Console application for automated testing of Class FUnctions related to Movie, ShoppingCart, and PaymentInfo
 * 
 * @author Jennifer Snider and Nolan Harris
 *
 *@param movies ArrayList<Movie>
 *@param shoppingCart ShoppingCart
 *@param paymentInfo PaymentInfo
 */
public class MovieTixConsole {
	
	public static ArrayList<Movie> movies = movieLoader();
	public static ShoppingCart shoppingCart = new ShoppingCart();
	public static PaymentInfo pInfo = new PaymentInfo();
	
	public static void main(String[] args) {

		//ArrayList<Movie> movies = movieLoader();
		
		for(Movie m : movies) {
				Integer ticketstobuy = new Random().nextInt(20);
				shoppingCart.addMovieTickets(m.name, ticketstobuy);
		}

		
		
		//Buy Tickets
		System.out.println("Receipt:");
		System.out.println(shoppingCart.toString());
		System.out.println();

		//Calculate Total
		System.out.println("Cart Total:");
		System.out.println(shoppingCart.getCartTotal());
		System.out.println();
		
		//SaveReceipt
		pInfo.processPayment(shoppingCart, "ConsoleRcpt.txt");
		
	}
	
	public static ArrayList<Movie> movieLoader(){

		URL tsvFile = MovieTixConsole.class.getResource("/resources/movies.tsv");
        BufferedReader br = null;
        String line = ""; // line storage
        String cvsSplitBy = "\t";   // use tab as separator
        ArrayList<Movie> movies = new ArrayList<Movie>();

        try {

            br = new BufferedReader(new FileReader(tsvFile.getFile()));
            while ((line = br.readLine()) != null) {

              
                String[] movie = line.split(cvsSplitBy);
                movies.add(new Movie(movie[0],movie[1],new ImageIcon(MovieTixConsole.class.getResource(movie[4])),movie[2],movie[3]));
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
