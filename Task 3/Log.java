package OOAD_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Log extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;
	private JPanel contentPane;
	private JTextField usn;
	private JTextField ps;
	static Log frame;
	static Color color;
	String mess = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Log(color);
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
	public Log(Color color) {
		con = AppDatabase.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(color);
		setContentPane(contentPane);

		usn = new JTextField();
		usn.setBounds(72, 175, 173, 28);
		contentPane.add(usn);
		usn.setColumns(10);

		ps = new JPasswordField();
		ps.setColumns(10);
		ps.setBounds(72, 257, 173, 28);
		contentPane.add(ps);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(72, 132, 73, 21);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(72, 225, 73, 21);
		contentPane.add(lblNewLabel_1);

		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Statement s = con.createStatement();
					String sql = "Select * from new_login where user_name ='" + usn.getText() + "' and password='"
							+ ps.getText() + "'";
					ResultSet rs = s.executeQuery(sql);
					if (rs.next()) { 
						int Name = rs.getInt(2);
						JOptionPane.showMessageDialog(null, "Login done");
						Order order = new Order(Name,color);
						order.setVisible(true);
						order.toFront();

					} else {
						JOptionPane.showMessageDialog(null, "Failed");
					}

				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		login.setBounds(95, 321, 110, 23);
		contentPane.add(login);

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
//				frame.dispose();
			}
		});
		btnNewButton.setBounds(10, 15, 110, 20);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Registration");
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				frame.dispose();
				RegistrationUser re = new RegistrationUser(color);
				re.setVisible(true);
				re.toFront();
			}
		});
		btnNewButton.setBounds(10, 15, 110, 20);
		contentPane.add(btnNewButton);
		btnNewButton_1.setBounds(95, 382, 110, 20);
		contentPane.add(btnNewButton_1);
	}
}
