package lightsOut;

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
import java.awt.Color;

public class LightsOutGui extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LightsOutGui frame = new LightsOutGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LightsOutGui() {
		setTitle("Lights Out");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton button = new JButton("");
		panel.add(button);
		button.setOpaque(true);
		
		JButton button_1 = new JButton("");
		panel.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBackground(Color.PINK);
		panel.add(button_2);
		
		JButton button_3 = new JButton("");
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		panel.add(button_4);
		
		JButton button_5 = new JButton("");
		panel.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.setBackground(Color.PINK);
		panel.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.setBackground(Color.PINK);
		panel.add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.setBackground(Color.PINK);
		panel.add(button_8);
		
		JButton button_9 = new JButton("");
		panel.add(button_9);
		
		JButton button_10 = new JButton("");
		panel.add(button_10);
		
		JButton button_11 = new JButton("");
		panel.add(button_11);
		
		JButton button_12 = new JButton("");
		button_12.setBackground(Color.PINK);
		panel.add(button_12);
		
		JButton button_13 = new JButton("");
		panel.add(button_13);
		
		JButton button_14 = new JButton("");
		panel.add(button_14);
		
		JButton button_15 = new JButton("");
		panel.add(button_15);
		
		JButton button_16 = new JButton("");
		panel.add(button_16);
		
		JButton button_17 = new JButton("");
		panel.add(button_17);
		
		JButton button_18 = new JButton("");
		panel.add(button_18);
		
		JButton button_19 = new JButton("");
		panel.add(button_19);
		
		JButton button_20 = new JButton("");
		panel.add(button_20);
		
		JButton button_21 = new JButton("");
		panel.add(button_21);
		
		JButton button_22 = new JButton("");
		panel.add(button_22);
		
		JButton button_23 = new JButton("");
		panel.add(button_23);
		
		JButton button_24 = new JButton("");
		panel.add(button_24);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New Game");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset Game");
		panel_1.add(btnNewButton_1);
	}
}
