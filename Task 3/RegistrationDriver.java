package OOAD_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegistrationDriver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static RegistrationDriver frame;
	private JTextField user;
	Connection con;
	private JTextField email;
	private JTextField pass;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textField;
	private JLabel lblNewLabel_2_2;
	private JTextField textField_1;
	private JButton register;
	private JButton btnLogIn;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private Color color;

			public void run() {
				try {
					frame = new RegistrationDriver(color);
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

	public RegistrationDriver(Color color) {
		con = AppDatabase.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		contentPane.setBackground(color);
		setContentPane(contentPane);
		user = new JTextField();
		user.setBounds(20, 104, 174, 27);
		contentPane.add(user);
		user.setColumns(10);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(20, 171, 174, 27);
		contentPane.add(email);

		pass = new JPasswordField();
		pass.setColumns(10);
		pass.setBounds(20, 237, 174, 27);
		contentPane.add(pass);

		lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(30, 73, 88, 22);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Phone");
		lblNewLabel_1.setBounds(30, 141, 88, 22);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(30, 210, 88, 22);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(20, 310, 174, 27);
		contentPane.add(textField);

		JLabel lblNewLabel_2_1 = new JLabel("Car Model");
		lblNewLabel_2_1.setBounds(30, 281, 88, 22);
		contentPane.add(lblNewLabel_2_1);

		lblNewLabel_2_2 = new JLabel("Car Number");
		lblNewLabel_2_2.setBounds(30, 347, 88, 22);
		contentPane.add(lblNewLabel_2_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(20, 377, 174, 27);
		contentPane.add(textField_1);

		register = new JButton("Register");
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement ps = con.prepareStatement(
							"insert into driver(driver_name, phone,password, car_model,car_num) values(?,?,?,?,?);");
					ps.setString(1, user.getText());
					ps.setString(2, email.getText());
					ps.setString(3, pass.getText());
					ps.setString(4, textField.getText());
					ps.setString(5, textField_1.getText());
					int x = ps.executeUpdate();
					if (x > 0) {
						System.out.println("Registered Successfully");
					} else {
						System.out.println("Failed");
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		register.setBounds(10, 433, 89, 23);
		contentPane.add(register);

		btnLogIn = new JButton("Log in");
		btnLogIn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Log l = new Log(null);
				l.setVisible(true);
				l.toFront();
			}
		});

		btnLogIn.setBounds(122, 435, 110, 20);
		contentPane.add(btnLogIn);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Ismayilova Fidan\\workspace\\OOAD\\src\\OOAD_HW2\\login.png"));
		btnNewButton.setBounds(26, 19, 16, 16);
		btnNewButton.setVisible(true);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration rg = new Registration(color);
				rg.setVisible(true);
				rg.toFront();
			}

		});
		JLabel image = new JLabel("");
		image.setBackground(Color.LIGHT_GRAY);
		image.setIcon(new ImageIcon("C:\\Users\\Ismayilova Fidan\\workspace\\OOAD\\src\\OOAD_HW2\\Dr.png"));
		image.setBounds(208, 129, 232, 240);
		image.setVisible(true);
		contentPane.add(image);
		btnNewButton.setBounds(10, 15, 110, 20);
		contentPane.add(btnNewButton);

	}
}
