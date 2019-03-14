package slotMachine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SlotGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlotGui frame = new SlotGui();
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
	public SlotGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Spinner1");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Spinner2");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Spinner3");
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblSlots = new JLabel("Slots");
		panel_1.add(lblSlots);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("$1");
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("$5");
		panel_2.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("$20");
		panel_2.add(rdbtnNewRadioButton_2);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.WEST);
		
		JLabel lblNewLabel_3 = new JLabel("Funds");
		panel_3.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Spin");
		panel_4.add(btnNewButton);
	}
}
