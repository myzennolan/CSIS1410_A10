package a10;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PaymentInfoGui extends JFrame {

	/**
	 * Serial 
	 */
	private static final long serialVersionUID = 1617001722376899556L;
	
private JPanel contentPane;
private JTextField textField_cardNo;
private JTextField textField_name;
private JTextField textField_filename;
private JTextField textField_zip;
private JTextField textField_secCode;


	/**
	 * creates the frame
	 * @param rcpt references the user's selections for each individual movie
	 * @param ct references the cart total built by aggregating purchases for separate movies during the
	 * same purchasing session 
	 */
	public PaymentInfoGui(String rcpt, Double ct, ShoppingCart sCart) {
		PaymentInfo pInfo = new PaymentInfo();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/**
		 * builds the main content pane of the gui
		 */
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		/**
		 * receipt area shows shopping cart of each individual movie and the total for all tickets selected
		 * for multiple movies
		 */
		JTextArea txtrReceipt = new JTextArea();
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		txtrReceipt.setText(rcpt + "\n" + "Total: " + formatter.format(ct));
		GridBagConstraints gbc_txtrReceipt = new GridBagConstraints();
		gbc_txtrReceipt.gridheight = 5;
		gbc_txtrReceipt.gridwidth = 7;
		gbc_txtrReceipt.insets = new Insets(0, 0, 5, 5);
		gbc_txtrReceipt.fill = GridBagConstraints.BOTH;
		gbc_txtrReceipt.gridx = 0;
		gbc_txtrReceipt.gridy = 0;
		contentPane.add(txtrReceipt, gbc_txtrReceipt);
		
		/**
		 * label identifying payment information section
		 */
		JLabel lblPaymentInfo = new JLabel("Enter payment information. " + "All fields are required.");
		lblPaymentInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPaymentInfo.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblPaymentInfo = new GridBagConstraints();
		gbc_lblPaymentInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaymentInfo.gridx = 8;
		gbc_lblPaymentInfo.gridy = 0;
		contentPane.add(lblPaymentInfo, gbc_lblPaymentInfo);
		
		/**
		 * labels the section where the credit card information is entered
		 */
		JLabel lblCardNumber = new JLabel("Card number");
		lblCardNumber.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCardNumber.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
		gbc_lblCardNumber.anchor = GridBagConstraints.WEST;
		gbc_lblCardNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCardNumber.gridx = 8;
		gbc_lblCardNumber.gridy = 1;
		contentPane.add(lblCardNumber, gbc_lblCardNumber);
		
		/**
		 * text field where the user enters the credit card number
		 */
		textField_cardNo = new JTextField();
		GridBagConstraints gbc_textField_cardNo = new GridBagConstraints();
		gbc_textField_cardNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_cardNo.insets = new Insets(0, 0, 5, 5);
		gbc_textField_cardNo.gridx = 8;
		gbc_textField_cardNo.gridy = 2;
		contentPane.add(textField_cardNo, gbc_textField_cardNo);
		textField_cardNo.setColumns(10);
		
		/**
		 * labels the field for the credit card expiration month
		 */
		JLabel lblExpMonth = new JLabel("Exp Month");
		lblExpMonth.setFont(new Font("Arial", Font.PLAIN, 11));
		GridBagConstraints gbc_lblExpMonth = new GridBagConstraints();
		gbc_lblExpMonth.anchor = GridBagConstraints.WEST;
		gbc_lblExpMonth.insets = new Insets(0, 0, 5, 10);
		gbc_lblExpMonth.gridx = 8;
		gbc_lblExpMonth.gridy = 3;
		contentPane.add(lblExpMonth, gbc_lblExpMonth);
		
		/**
		 * labels the field for the credit card expiration year
		 */
		JLabel lblExpYear = new JLabel("Exp Year");
		lblExpYear.setFont(new Font("Arial", Font.PLAIN, 11));
		GridBagConstraints gbc_lblExpYear = new GridBagConstraints();
		gbc_lblExpYear.anchor = GridBagConstraints.WEST;
		gbc_lblExpYear.insets = new Insets(0, 0, 5, 10);
		gbc_lblExpYear.gridx = 9;
		gbc_lblExpYear.gridy = 3;
		contentPane.add(lblExpYear, gbc_lblExpYear);
		
		/**
		 * dropdown where the user selects the credit card expiration month
		 */
		String monthname[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		JComboBox<String> month = new JComboBox<String>(monthname);
		@SuppressWarnings("unused")
		GridBagConstraints gbc_month = new GridBagConstraints();
		gbc_month.insets = new Insets(0, 0, 5, 10);
		gbc_month.fill = GridBagConstraints.HORIZONTAL;
		gbc_month.gridx = 8;
		gbc_month.gridy = 4;
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				month.getSelectedItem();
				
			}
		});
		contentPane.add(month, gbc_month);
		
		/**
		 * dropdown where the user selects the credit card expiration year
		 */
		String years[] = {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026"};
		JComboBox<String> year = new JComboBox<String>(years);
		@SuppressWarnings("unused")
		GridBagConstraints gbc_year = new GridBagConstraints();
		gbc_year.insets = new Insets(0, 0, 5, 10);
		gbc_year.fill = GridBagConstraints.HORIZONTAL;
		gbc_year.gridx = 9;
		gbc_year.gridy = 4;
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				year.getSelectedItem();
				
			}
		});
		contentPane.add(year, gbc_year);
		
		/**
		 * label which identifies the field for the name on credit card
		 */
		JLabel lblNameOnCard = new JLabel("Name on Card");
		lblNameOnCard.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNameOnCard.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNameOnCard = new GridBagConstraints();
		gbc_lblNameOnCard.anchor = GridBagConstraints.WEST;
		gbc_lblNameOnCard.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOnCard.gridx = 8;
		gbc_lblNameOnCard.gridy = 5;
		contentPane.add(lblNameOnCard, gbc_lblNameOnCard);
		
		/**
		 * button which when selected will initiate the credit card purchase
		 */
		JButton btnBuyNow = new JButton("Buy Now");
		GridBagConstraints gbc_btnBuyNow = new GridBagConstraints();
		gbc_btnBuyNow.gridwidth = 3;
		gbc_btnBuyNow.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuyNow.gridx = 0;
		gbc_btnBuyNow.gridy = 6;
		contentPane.add(btnBuyNow, gbc_btnBuyNow);
		btnBuyNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ct > 0.0) {
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					txtrReceipt.setText(rcpt + "\n" + "Total: " + formatter.format(ct) + "\n\n" + 
					"Payment processed" + "\n\n" + 
							"Save your receipt for your records" );
				}
				else
					txtrReceipt.setText("Please select a movie");
				
			}
		});
		
		/**
		 * textfield which indicates the name of the saved receipt text file
		 */
		textField_filename = new JTextField();
		GridBagConstraints gbc_textField_filename = new GridBagConstraints();
		gbc_textField_filename.insets = new Insets(0, 0, 5, 5);
		gbc_textField_filename.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_filename.gridx = 0;
		gbc_textField_filename.gridy = 7;
		contentPane.add(textField_filename, gbc_textField_filename);
		textField_filename.setColumns(10);
		
		/**
		 * savereceipt button when selected will initiate the process of saving the receipt  
		 * showing the purchase of the movie tickets
		 */
		JButton btnSaveReceipt = new JButton("Save Receipt");
		GridBagConstraints gbc_btnSaveReceipt = new GridBagConstraints();
		gbc_btnSaveReceipt.gridwidth = 3;
		gbc_btnSaveReceipt.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveReceipt.gridx = 0;
		gbc_btnSaveReceipt.gridy = 8;
		btnSaveReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pInfo.processPayment(sCart,"movieticketreceipt.txt");
				textField_filename.setText("movieticketreceipt.txt");	
				
			}
		});
		contentPane.add(btnSaveReceipt, gbc_btnSaveReceipt);
		
		/**
		 * textfield_name is where the user enters the name shown on the credit card
		 */
		textField_name = new JTextField();
		GridBagConstraints gbc_textField_name = new GridBagConstraints();
		gbc_textField_name.insets = new Insets(0, 0, 5, 5);
		gbc_textField_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_name.gridx = 8;
		gbc_textField_name.gridy = 6;
		contentPane.add(textField_name, gbc_textField_name);
		textField_name.setColumns(10);
		
		/**
		 * labels the zip code text field
		 */
		JLabel lblZipCode = new JLabel("Zip Code");
		lblZipCode.setFont(new Font("Arial", Font.PLAIN, 11));
		GridBagConstraints gbc_lblZipCode = new GridBagConstraints();
		gbc_lblZipCode.anchor = GridBagConstraints.WEST;
		gbc_lblZipCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblZipCode.gridx = 8;
		gbc_lblZipCode.gridy = 7;
		contentPane.add(lblZipCode, gbc_lblZipCode);
		
		/**
		 * labels the security code text field
		 */
		JLabel lblSecCode = new JLabel("Sec Code");
		lblSecCode.setFont(new Font("Arial", Font.PLAIN, 11));
		GridBagConstraints gbc_lblSecCode = new GridBagConstraints();
		gbc_lblSecCode.anchor = GridBagConstraints.WEST;
		gbc_lblSecCode.insets = new Insets(0, 0, 5, 0);
		gbc_lblSecCode.gridx = 9;
		gbc_lblSecCode.gridy = 7;
		contentPane.add(lblSecCode, gbc_lblSecCode);
		
		/**
		 * textfield_zip is where the user enters the zip code associated with the 
		 * credit card used for payment
		 */
		textField_zip = new JTextField();
		GridBagConstraints gbc_textField_zip = new GridBagConstraints();
		gbc_textField_zip.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_zip.insets = new Insets(0, 0, 5, 10);
		gbc_textField_zip.gridx = 8;
		gbc_textField_zip.gridy = 8;
		contentPane.add(textField_zip, gbc_textField_zip);
		textField_zip.setColumns(10);
		
		/**
		 * textfield_seccode is where the user enters the security code from the credit card
		 */
		textField_secCode = new JTextField();
		GridBagConstraints gbc_textField_secCode = new GridBagConstraints();
		gbc_textField_secCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_secCode.insets = new Insets(0, 0, 5, 10);
		gbc_textField_secCode.gridx = 9;
		gbc_textField_secCode.gridy = 8;
		contentPane.add(textField_secCode, gbc_textField_secCode);
		textField_secCode.setColumns(10);
		

		
	}



}