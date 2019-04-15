package Jennifer;
import a10.MovieTixConsole;
import a10.Movie;
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
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class MovieTixGui extends JFrame {

	private JPanel contentPane;
	private JTextField txttix;
	private int counter = 1;
	public 	JLabel lblMoviePoster = new JLabel("");
	public 	Movie currentMovie;
	public Map<String,Integer> shoppingCart = new TreeMap<String,Integer>();
	public static Double cartTotal = 0.0d;
	public static Double moviePrice = 5.0d;
	public static JTextArea lblReceipt = new JTextArea("Receipt");
	

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
	 * Create the frame.
	 */
	public MovieTixGui() {
		ArrayList<Movie> movies = movieLoader();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/**
		 * jtextarea that shows title, rating, runtime and description of each movie
		*/
		JTextArea txtrTextaboutmovies = new JTextArea();
		txtrTextaboutmovies.setColumns(30);
		txtrTextaboutmovies.setEditable(false);
		txtrTextaboutmovies.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrTextaboutmovies.setWrapStyleWord(true);
		txtrTextaboutmovies.setLineWrap(true);
		txtrTextaboutmovies.setText("Please select a movie");
		contentPane.add(txtrTextaboutmovies, BorderLayout.CENTER);
		lblMoviePoster.setPreferredSize(new Dimension(200, 300));
		contentPane.add(lblMoviePoster, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		/**
		 * sizes the movie pictures
		 */
		movieButtons(movies, txtrTextaboutmovies, panel);
		lblReceipt.setBackground(Color.LIGHT_GRAY);
		lblReceipt.setPreferredSize(new Dimension(250,100));
		contentPane.add(lblReceipt, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		/**
		 * arrow to increment the number of tickets to buy
		 */
		BasicArrowButton babUp = new BasicArrowButton(SwingConstants.NORTH);
		babUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				counter++;
				txttix.setText("" + counter);
				
			}
		});
		panel_1.add(babUp);
		
		/**
		 * arrow to decrement the number of tickets to buy
		 */
		BasicArrowButton babDown = new BasicArrowButton(SwingConstants.SOUTH);
		babDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (counter > 1)
				counter--;
				txttix.setText("" + counter);
			}
		});
		panel_1.add(babDown);
		
		/**
		 * textfield showing the number of tickets selected to buy, this field increments
		 * and decrements in value when the user pushes the up and down arrow buttons
		 */
		txttix = new JTextField();
		txttix.setText("1");
		panel_1.add(txttix);
		txttix.setColumns(10);
		txttix.setEditable(false); 
		
		/**
		 * add to cart button moves the user's selection to the shopping cart
		 */
		JButton btnAddtocart = new JButton("Add to Cart");
		btnAddtocart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shoppingCart.put(currentMovie.name, Integer.decode(txttix.getText()));
				cartParser(shoppingCart);
			}

			/**
			 * @return
			 */
			
		});
		panel_1.add(btnAddtocart);
		
		/**
		 * checkout button which when selected takes the user to the payment screen
		 */
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
				public void actionPerformed(ActionEvent e) {  
				if (cartTotal > 0) {
					displayCheckout();
				}
				else
					txtrTextaboutmovies.setText("Please make your selection");
			 
			 
				}
				
			});
	
		
		panel_1.add(btnCheckout);
		
		/**
		 * reset button clears the user's selection from the shopping cart
		 */
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrTextaboutmovies.setText("Please select a movie");
				lblReceipt.setText("No movie selected");
				shoppingCart.clear();
				txttix.setText("1");
				cartTotal = 0.0d;
				
			}
		});
		panel_1.add(btnReset);
		
		
	}

	/**
	 * @param movies this is the array list of information from the input file
	 * @param txtrTextaboutmovies is the area in the gui that holds the title, rating, runtime, and description 
	 * from the input file 
	 * @param panel holds the buttons which show the images of the movies
	 */
	private void movieButtons(ArrayList<Movie> movies, JTextArea txtrTextaboutmovies, JPanel panel) {
		for(Movie m : movies){
			JButton btnMovie = new JButton("");
			btnMovie.setBorderPainted( false );
			btnMovie.setSize(new Dimension(100,150));
			btnMovie.setIcon(new ImageIcon(m.poster.getImage().getScaledInstance(100, 150, 0)));
			btnMovie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtrTextaboutmovies.setText(m.name+"\n" +"("+ m.rating+") "+ "\n"+ m.runTime+"\n\n"
							+ m.description+"\n");
					lblMoviePoster.setIcon(new ImageIcon(m.poster.getImage().getScaledInstance(200, 300, 0)));
					currentMovie = m; 
				}
			});
			panel.add(btnMovie);
		}
	}
	
	/**
	 * @returns the parsed data from the input file
	 */
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
	 * 
	 * @param sCart builds the shopping cart of the user's purchases (#tix x price = total)
	 */
	public static void cartParser(Map<String,Integer> sCart) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		StringBuilder rcpt = new StringBuilder();
		cartTotal = 0.0d;
		for(Entry<String,Integer> m:sCart.entrySet()) {
		rcpt.append(m.getKey()+": " +m.getValue() + " x " + formatter.format(moviePrice) + " = " + formatter.format(m.getValue()*moviePrice)+"\n\n");
		cartTotal += m.getValue()*moviePrice;
		}
		lblReceipt.setText(rcpt.toString());
		}

	/**
	 * creates the second gui which is the payment screen to take the credit card information
	 */
	public static void displayCheckout() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentInfo frame = new PaymentInfo(lblReceipt.getText(),cartTotal);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
