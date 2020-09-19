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

public class RegistrationUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JTextField phone;
	private JTextField pass;
	Connection con;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	static RegistrationUser frame;
	private JButton btnLogIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private Color color;

			public void run() {
				try {
					frame = new RegistrationUser(color);
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
	public RegistrationUser(Color color) {
		con = AppDatabase.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(color);
		setContentPane(contentPane);

		user = new JTextField();
		user.setBounds(10, 150, 174, 27);
		contentPane.add(user);
		user.setColumns(10);

		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(10, 228, 174, 27);
		contentPane.add(phone);

		pass = new JPasswordField();
		pass.setColumns(10);
		pass.setBounds(10, 308, 174, 27);
		contentPane.add(pass);

		lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(20, 120, 88, 22);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Phone");
		lblNewLabel_1.setBounds(20, 195, 88, 22);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(20, 269, 88, 22);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement ps = con
							.prepareStatement("insert into user(user_name, phone,password) values(?,?,?);");
					ps.setString(1, user.getText());
					ps.setString(2, phone.getText());
					ps.setString(3, pass.getText());
					PreparedStatement ps2 = con.prepareStatement("Delete from new_login;");
					PreparedStatement ps1 = con.prepareStatement(
							"INSERT INTO new_login(user_name, user_id, password) SELECT user_name, user_id,password FROM user;");
					int x = ps.executeUpdate();
					int z = ps2.executeUpdate();
					int y = ps1.executeUpdate();
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
		btnNewButton.setBounds(10, 363, 110, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton1 = new JButton("Back");
		btnNewButton1.setIcon(new ImageIcon("C:\\Users\\Ismayilova Fidan\\workspace\\OOAD\\src\\OOAD_HW2\\login.png"));
		btnNewButton1.setBounds(26, 19, 16, 16);
		btnNewButton1.setVisible(true);
		contentPane.add(btnNewButton1);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration rg = new Registration(color);
				rg.setVisible(true);
				rg.toFront();
			}

		});
		btnNewButton1.setBounds(10, 0, 110, 20);
		contentPane.add(btnNewButton1);

		btnLogIn = new JButton("Log in");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Log l = new Log(null);
				l.setVisible(true);
				l.toFront();
			}
		});
		JLabel image = new JLabel("");
		image.setBackground(Color.LIGHT_GRAY);
		image.setIcon(new ImageIcon("C:\\Users\\Ismayilova Fidan\\Desktop\\user.png"));
		image.setBounds(192, 133, 205, 216);
		image.setVisible(true);
		contentPane.add(image);
		btnNewButton1.setBounds(10, 15, 110, 20);
		contentPane.add(btnNewButton1);
		btnLogIn.setBounds(10, 414, 110, 20);
		contentPane.add(btnLogIn);

	}
}