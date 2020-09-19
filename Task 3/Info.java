package OOAD_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class Info extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection con;
	Random rn = new Random();
	static Color color;
	static int num, ran;
	private static DecimalFormat df = new DecimalFormat("0.00");
	static String or, ch = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info(num, ran, or, ch, color);
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
	 * @param order_id
	 * @param ch
	 * @param color
	 */
	public Info(int name, int ran, String order_id, String ch, Color color) {
		con = AppDatabase.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(color);
		setContentPane(contentPane);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(349, 28, 293, 356);
		textArea.append("Hello World.");
		contentPane.add(textArea);
		JButton btnNewButton_1 = new JButton("User Info");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.setText(null);
					Statement s = con.createStatement();
					String sql = "Select * from user where user_id ='" + name + "'";
					ResultSet rs = s.executeQuery(sql);
					if (rs.next()) {
						String userName = rs.getString(2);
						String phone = rs.getString(3);
						textArea.append(
								"Customer: " + userName + "\nMobile Number: " + phone + "\nPayment Method: " + ch);
						textArea.setFont(textArea.getFont().deriveFont(15f));
						rs.close();
					} else {
						JOptionPane.showMessageDialog(null, "Failed");
					}

				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btnNewButton_1.setBounds(69, 257, 171, 41);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Driver Info");
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.setText(null);
					Statement s = con.createStatement();
					String sql = "Select * from driver where driver_id ='" + ran + "'";
					ResultSet rs = s.executeQuery(sql);
					if (rs.next()) {
						String driverName = rs.getString(2);
						String phone = rs.getString(3);
						String carModel = rs.getString(5);
						String carNum = rs.getString(6);
						String rating = rs.getString(7);
						textArea.append("Driver: " + driverName + "\nMobile Number: " + phone + "\n" + "Car: "
								+ carModel + ", " + carNum + "\n" + "Rating: " + rating);
						textArea.setFont(textArea.getFont().deriveFont(15f));
						rs.close();
					} else {
						JOptionPane.showMessageDialog(null, "Failed");
					}

				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btnNewButton_2.setBounds(69, 51, 171, 41);
		contentPane.add(btnNewButton_2);
		JButton btnNewButton = new JButton("Order Info");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.setText(null);
					Statement s = con.createStatement();
					String sql = "Select user_name from user where user_id ='" + name + "'";
					ResultSet rs = s.executeQuery(sql);
					if (rs.next()) {
						String userName = rs.getString(1);
						textArea.append("Customer: " + userName);
						rs.close();
					} else {
						JOptionPane.showMessageDialog(null, "Failed");
					}
					String sql1 = "Select * from driver where driver_id ='" + ran + "'";
					ResultSet rs1 = s.executeQuery(sql1);
					if (rs1.next()) {
						String driverName = rs1.getString(2);
						String carModel = rs1.getString(5);
						String carNum = rs1.getString(6);
						textArea.append(
								"\n" + "Driver: " + driverName + "\n" + "Car: " + carModel + ", " + carNum + "\n");
						textArea.setFont(textArea.getFont().deriveFont(15f));
					}
					String sql2 = "Select * from current_order where order_id ='" + order_id + "'";
					ResultSet rs2 = s.executeQuery(sql2);
					if (rs2.next()) {
						String curAdd = rs2.getString(4);
						String desAdd = rs2.getString(5);
						textArea.append("From: " + curAdd + " \nTo: " + desAdd);
					}
					String sql3 = "SELECT * FROM taxi JOIN driver ON taxi.car_num = driver.car_num WHERE driver_id ='"
							+ ran + "'";
					ResultSet rs3 = s.executeQuery(sql3);
					if (rs3.next()) {
						String type = rs3.getString(3);
						if (type.equalsIgnoreCase("vip")) {
							double price = 1 + 9.0 * rn.nextDouble();
							price += 12;

							textArea.append("\n" + "Price of the ride: " + df.format(price) + " azn");
						} else if (type.equalsIgnoreCase("comfort")) {
							double price = 1 + 9.0 * rn.nextDouble();
							price += 7;
							textArea.append("\n" + "Price of the ride: " + df.format(price) + " azn");
						} else if (type.equalsIgnoreCase("econom")) {
							double price = 1 + 9.0 * rn.nextDouble();
							price += 3;
							textArea.append("\n" + "Price of the ride: " + df.format(price) + " azn");
						}
						textArea.setFont(textArea.getFont().deriveFont(15f));
						rs3.close();
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btnNewButton.setBounds(69, 119, 171, 41);
		contentPane.add(btnNewButton);

		JButton btnNewButton_3 = new JButton("Vehicle Info");
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.setText(null);
					Statement s = con.createStatement();
					String sql = "SELECT * FROM taxi JOIN driver ON taxi.car_num = driver.car_num WHERE driver_id ='"
							+ ran + "'";
					ResultSet rs = s.executeQuery(sql);
					if (rs.next()) {
						String carModel = rs.getString(1);
						String carNum = rs.getString(2);
						String type = rs.getString(3);
						textArea.append("Car: " + carModel + ", " + carNum + "\n" + "Type: " + type);
						textArea.setFont(textArea.getFont().deriveFont(15f));
						rs.close();
					} else {
						JOptionPane.showMessageDialog(null, "Failed");
					}

				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btnNewButton_3.setBounds(69, 188, 171, 41);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Enter Chat");
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textArea.append("Please enter your Message \nand driver will receive it: ");
				textArea.setFont(textArea.getFont().deriveFont(15f));
			}
		});
		btnNewButton_4.setBounds(69, 326, 171, 41);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Exit");
		btnNewButton_5.setBounds(471, 420, 110, 20);
		contentPane.add(btnNewButton_5);
	}
}
