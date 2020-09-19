package OOAD_Project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.Color;

public class Order extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField, textField_1;
	private JButton btnNewButton;
	Random rn = new Random();
	Connection con;
	static Color color;
	static Order frame;
	static int num;
	String order_id, ch = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					frame = new Order(num, color);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param name
	 * @param color2
	 */
	public Order(int name, Color color) {

		con = AppDatabase.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(color);
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setBounds(72, 137, 173, 28);
		contentPane.add(textField);

		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 212, 173, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		JLabel lblNewLabel = new JLabel("Current Location");
		lblNewLabel.setBounds(72, 101, 150, 21);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Destination Location");
		lblNewLabel_1.setBounds(72, 180, 150, 21);
		contentPane.add(lblNewLabel_1);
		Choice choice = new Choice();
		choice.add("Cash");
		choice.add("Card");
		choice.setBounds(72, 285, 173, 39);
		contentPane.add(choice);

		btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ran = 1 + rn.nextInt(5);
				try {
					PreparedStatement ps = con.prepareStatement(
							"insert into current_order(driver_id,user_id, user_curr_address,user_dest_address,payment) values(?,?,?,?,?);");
					ps.setString(1, ran + "");
					ps.setString(2, name + "");
					ps.setString(3, textField.getText());
					ps.setString(4, textField_1.getText());
					ps.setString(5, choice.getItem(choice.getSelectedIndex()));
					int x = ps.executeUpdate();
					Statement s = con.createStatement();
					String sql = "SELECT order_id FROM current_order where user_id ='" + name + "'"
							+ " ORDER BY order_id Desc";
					ResultSet rs = s.executeQuery(sql);
					if (rs.next()) {
						order_id = rs.getString(1);
					}
					if (x > 0) {
						ch = choice.getItem(choice.getSelectedIndex());
						JOptionPane.showMessageDialog(null, "Address confirmed");
						Info info = new Info(name, ran, order_id, ch, color);
						info.setVisible(true);
						info.toFront();
					} else {
						System.out.println("Failed");
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btnNewButton.setBounds(72, 347, 171, 41);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1_1 = new JLabel("Payment Method");
		lblNewLabel_1_1.setBounds(72, 257, 150, 21);
		contentPane.add(lblNewLabel_1_1);

	}
}
