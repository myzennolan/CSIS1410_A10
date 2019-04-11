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

@SuppressWarnings("serial")
public class MovieTixGui extends JFrame {

	private JPanel contentPane;
	private JTextField txttix;
	private int counter = 1;
	public 	JLabel lblMoviePoster = new JLabel("");
	public 	Movie currentMovie;
	public Map<String,Integer> shoppingCart = new TreeMap<String,Integer>();
	public static double cartTotal = 0.0d;
	public static double moviePrice = 5.0d;
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
		
		movieButtons(movies, txtrTextaboutmovies, panel);
		lblReceipt.setBackground(Color.LIGHT_GRAY);
		lblReceipt.setPreferredSize(new Dimension(250,100));
		contentPane.add(lblReceipt, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		BasicArrowButton babUp = new BasicArrowButton(SwingConstants.NORTH);
		babUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				counter++;
				txttix.setText("" + counter);
				
			}
		});
		panel_1.add(babUp);
		
		BasicArrowButton babDown = new BasicArrowButton(SwingConstants.SOUTH);
		babDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (counter > 1)
				counter--;
				txttix.setText("" + counter);
			}
		});
		panel_1.add(babDown);
		
		txttix = new JTextField();
		txttix.setText("1");
		panel_1.add(txttix);
		txttix.setColumns(10);
		
		JButton btnAddtocart = new JButton("Add to Cart");
		btnAddtocart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shoppingCart.put(currentMovie.name, Integer.decode(txttix.getText()));
				cartParser(shoppingCart);
			}

		});
		panel_1.add(btnAddtocart);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
	//		@Override
			public void actionPerformed(ActionEvent e) {
//		            JFrame frame = new JFrame ("MyPanel");
//		            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//		            frame.getContentPane().add (new PaymentInfo());
//		            frame.pack();
//		            frame.setVisible (true);
					displayCheckout();
			}
			
		});
		
		//btnCheckout.setEnabled(false); 
		panel_1.add(btnCheckout);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrTextaboutmovies.setText("Please select a movie");
				lblReceipt.setText("receipt.details");
				shoppingCart.clear();
				cartTotal = 0.0d;
				txttix.setText("1");
			}
		});
		panel_1.add(btnReset);
		
		
	}

	/**
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
	
	public static void cartParser(Map<String,Integer> sCart) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		StringBuilder rcpt = new StringBuilder();
		cartTotal =0.0d;
		for(Entry<String,Integer> m:sCart.entrySet()) {
			rcpt.append(m.getKey()+"("+m.getValue()+") - "+formatter.format(m.getValue()*moviePrice)+"\n");
			cartTotal += m.getValue()*moviePrice;
		}
		lblReceipt.setText(rcpt.toString());
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

//		ImageIcon eyes = new ImageIcon ("filename.jpg");
//		eyes.paintIcon(null, g, circleLeft + circleWidth/2 - circleWidth/4, circleTop);
//		
//		g.drawImage(eyes.getImage(), x, y, width, height, this);
//		g.drawImage(ImageIO.read(newFile("filename.jpg"))
//				, circleLeft + CircleWidth/4
//				, circleTop + 10, 50, 80, this);
	
	//jpanel that extends jpanel and then override to enter graphics g in that panel
	//http://www.java2s.com/Tutorials/Java/Swing_How_to/JPanel/Scale_image_as_with_JPanel.htm
	
}
