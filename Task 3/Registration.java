package OOAD_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Registration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Registration frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private Color color;

			public void run() {
				try {
					frame = new Registration(color);
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
	public Registration(Color color) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		contentPane.setBackground(color);
		setContentPane(contentPane);

		JButton usid = new JButton("User");
		usid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrationUser us = new RegistrationUser(color);
				us.setVisible(true);
				us.toFront();
			}

		});
		usid.setBounds(96, 183, 115, 31);
		contentPane.add(usid);

		JButton drivid = new JButton("Driver");
		drivid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrationDriver driv = new RegistrationDriver(color);
				driv.setVisible(true);
				driv.toFront();

			}

		});
		drivid.setBounds(96, 250, 115, 31);
		contentPane.add(drivid);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Ismayilova Fidan\\workspace\\OOAD\\src\\OOAD_HW2\\login.png"));
		btnNewButton.setBounds(26, 19, 16, 16);
		btnNewButton.setVisible(true);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AppFirstPage app = new AppFirstPage();
				app.setVisible(true);
				app.toFront();
			}
		});
		btnNewButton.setBounds(10, 15, 110, 20);
		contentPane.add(btnNewButton);
	}

}
