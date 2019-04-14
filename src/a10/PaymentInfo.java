package a10;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaymentInfo extends JFrame {

	private JPanel contentPane;
	private JTextField txtPleaseEnterCredit;
	private JTextField txtCard;
	private JTextField textField;
	private JTextField txtExpDate;
	private JTextField txtFirstName;
	private JTextField textField_1;
	private String r;
	private double c;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public PaymentInfo(String rcpt,Double ct) {
		//setDefaultCloseOperation(this.setVisible(true));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		txtPleaseEnterCredit = new JTextField();
		txtPleaseEnterCredit.setHorizontalAlignment(SwingConstants.CENTER);
		txtPleaseEnterCredit.setText("Please enter credit card information");
		panel.add(txtPleaseEnterCredit);
		txtPleaseEnterCredit.setColumns(50);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		txtCard = new JTextField();
		txtCard.setText("Card #");
		panel_1.add(txtCard);
		txtCard.setColumns(10);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		
		txtExpDate = new JTextField();
		txtExpDate.setText("Exp. Date");
		panel_2.add(txtExpDate);
		txtExpDate.setColumns(10);
	}

	public PaymentInfo(ShoppingCart shoppingCart) {
		// TODO Auto-generated constructor stub
	}

}
