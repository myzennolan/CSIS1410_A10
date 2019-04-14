package a10;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

/**
 * Allows user to select and purchase tickets from a predefined list of movies
 * 
 * @author Jennifer Snider and Nolan Harris
 *
 * @param counter int
 * @param cartTotal double
 * @param moviePrice double
 * @param currentMovie Movie
 * @param contentPane JPanel
 * @param txttix JTextField
 * @param lblMoviePoster JLabel
 * @param shoppingCart TreeMap<String,Integer>
 * @param lblReceipt JTextArea
 * @param txtrTextaboutmovies JTextArea
 * @param movieSelectionPanel JPanel
 * @param cartControlPanel JPanel;
 */
@SuppressWarnings("serial")
public class MovieTixGui extends JFrame {

	private int counter = 1;
	private JPanel contentPane = new JPanel();
	private JTextField txttix = new JTextField();
	public 	JLabel lblMoviePoster = new JLabel("");
	public 	Movie currentMovie;
	//public Map<String,Integer> shoppingCart = new TreeMap<String,Integer>();
	public static ShoppingCart shoppingCart = new ShoppingCart();
	public static JTextArea lblReceipt = new JTextArea("Receipt");
	public JTextArea txtrTextaboutmovies = new JTextArea();
	public JPanel movieSelectionPanel = new JPanel();
	public JPanel cartControlPanel = new JPanel();
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieTixGui frame = new MovieTixGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Builds the main GUI.
	 */
	public MovieTixGui() {
		ArrayList<Movie> movies = movieLoader();
		
		buildWindow();
		
		setupCenter();
		
		setupWest();
		
		setupNorth(movies);
		
		setupEast();
		
		setupSouth();
		
		controlButtons();
		
		
	}

	/**
	 * Sets up the JFrame and configures its properties.
	 */
	private void buildWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	/**
	 * Places our cart control panel in the south.
	 */
	private void setupSouth() {
		contentPane.add(cartControlPanel, BorderLayout.SOUTH);
	}

	/**
	 * Places our receipt representation in the east.
	 */
	private void setupEast() {
		lblReceipt.setBackground(Color.LIGHT_GRAY);
		lblReceipt.setPreferredSize(new Dimension(250,100));
		contentPane.add(lblReceipt, BorderLayout.EAST);
	}

	/**
	 * Accepts a list of movies and adds them as buttons to the movieSelectionPanel, in the North
	 * 
	 * @param movies ArrayList<Movie>
	 */
	private void setupNorth(ArrayList<Movie> movies) {
		contentPane.add(movieSelectionPanel, BorderLayout.NORTH);		
		movieButtons(movies, txtrTextaboutmovies, movieSelectionPanel);
	}

	/**
	 * Places a Giant Movie Poster Target in the west, this poster is updated when users click on the movie buttons.
	 */
	private void setupWest() {
		lblMoviePoster.setPreferredSize(new Dimension(200, 300));
		contentPane.add(lblMoviePoster, BorderLayout.WEST);
	}

	/**
	 * Places our movie details box in the center.
	 */
	private void setupCenter() {
		txtrTextaboutmovies.setColumns(30);
		txtrTextaboutmovies.setEditable(false);
		txtrTextaboutmovies.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrTextaboutmovies.setWrapStyleWord(true);
		txtrTextaboutmovies.setLineWrap(true);
		txtrTextaboutmovies.setText("Please select a movie");		
		contentPane.add(txtrTextaboutmovies, BorderLayout.CENTER);
	}

	/**
	 * Creates, configures, and places, our control buttons where they belong.
	 */
	private void controlButtons() {
		
		/**
		 * Increments the number of tickets desired
		 */
		BasicArrowButton babUp = new BasicArrowButton(SwingConstants.NORTH);
		babUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				counter++;
				txttix.setText("" + counter);
				
			}
		});
		cartControlPanel.add(babUp);
		
		/**
		 * Decrements the number of tickets desired
		 */
		BasicArrowButton babDown = new BasicArrowButton(SwingConstants.SOUTH);
		babDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (counter > 1)
				counter--;
				txttix.setText("" + counter);
			}
		});
		cartControlPanel.add(babDown);
		
		txttix.setText("1");
		cartControlPanel.add(txttix);
		txttix.setColumns(10);

		
		/**
		 * Adds the chosen number of tickets for the selected movie to the shoppingCart.
		 */
		JButton btnAddtocart = new JButton("Add to Cart");
		btnAddtocart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentMovie == null) {
				}
				else {
					shoppingCart.addMovieTickets(currentMovie.name, Integer.decode(txttix.getText()));

					lblReceipt.setText(shoppingCart.toString());				
				}
			}

		});
		cartControlPanel.add(btnAddtocart);

		
		/**
		 * Displays the checkout window
		 */
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					displayCheckout();
			}
			
		}); 
		cartControlPanel.add(btnCheckout);
		
		/**
		 * Resets the shopping cart and screen elements to their original state.
		 */
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrTextaboutmovies.setText("Please select a movie");
				lblReceipt.setText("receipt.details");
				shoppingCart.resetCart();
				txttix.setText("1");
				lblMoviePoster.setIcon(null);
				currentMovie = null;
				
			}
		});
		cartControlPanel.add(btnReset);
	}

	/**
	 * Builds and configures the movie buttons to display their specific poster, and when clicked update the big poster and the movie details.
	 * 
	 * @param movies
	 * @param txtrTextaboutmovies
	 * @param panel
	 */
	private void movieButtons(ArrayList<Movie> movies, JTextArea txtrTextaboutmovies, JPanel panel) {
		for(Movie m : movies){
			JButton btnMovie = new JButton("");
			btnMovie.setBorderPainted( false );
			btnMovie.setSize(new Dimension(100,150));
			btnMovie.setIcon(new ImageIcon(m.poster.getImage().getScaledInstance(100, 150, 0)));
			btnMovie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtrTextaboutmovies.setText(m.name+" ("+ m.rating+") "+ m.runTime+"\n"
							+ m.description+"\n");
					lblMoviePoster.setIcon(new ImageIcon(m.poster.getImage().getScaledInstance(200, 300, 0)));
					currentMovie = m;
				}
			});
			panel.add(btnMovie);
		}
	}
	
	/**
	 * Updates receipt with formatted view of shoppingCart
	 * 
	 * @param sCart
	 */
	public static void cartParser(Map<String,Integer> sCart) {
		/*
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		StringBuilder rcpt = new StringBuilder();
		cartTotal =0.0d;
		for(Entry<String,Integer> m:sCart.entrySet()) {
			rcpt.append(m.getKey()+"("+m.getValue()+") - "+formatter.format(m.getValue()*moviePrice)+"\n");
			cartTotal += m.getValue()*moviePrice;
		}
		*/
		lblReceipt.setText(shoppingCart.toString());
	}
	
	/**
	 * Parses movies.tsv file and returns an ArrayList<Movie> built for the items in the file.
	 * @return ArrayList<Movie>
	 */
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
	
	/**
	 * Initializes and displays the PaymentInfo frame, passing through the text representation of the receipt and the total dollar value of the cart.
	 */
	public static void displayCheckout() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentInfo frame = new PaymentInfo(shoppingCart.toString(),shoppingCart.getCartTotal());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
