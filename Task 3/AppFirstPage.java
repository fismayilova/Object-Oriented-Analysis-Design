package OOAD_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class AppFirstPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static AppFirstPage frame;
	private JPanel contentPane;
	Random rn = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AppFirstPage();
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
	public AppFirstPage() {
		Color color = new Color(244, 208, 63);
		color.brighter();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(color);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setToolTipText("Hi");
		panel.setForeground(Color.PINK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[1px][][][][][][][][][][]", "[1px][][][][][][][][][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Taxi Dispatch Application");
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 27));
		lblNewLabel.setForeground(SystemColor.textHighlight);
		panel.add(lblNewLabel, "cell 4 7");

		JButton btnNewButton_1 = new JButton("REGISTER");
		btnNewButton_1.setBackground(Color.WHITE);
		panel.add(btnNewButton_1, "cell 4 12");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Registration rg = new Registration(color);
				rg.setVisible(true);
				rg.toFront();
			}

		});

		JLabel lblNewLabel_1 = new JLabel("        OR");
		panel.add(lblNewLabel_1, "cell 4 13");

		JButton btnNewButton = new JButton("LOG IN");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(btnNewButton, "cell 4 14");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
				Log us = new Log(color);
				us.setVisible(true);
				us.toFront();
			}

		});

		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon("src/OOAD_HW2/Taxi (2).png"));
		panel.add(image, "cell 50 50");

	}

}
