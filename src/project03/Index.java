package project03;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;


public class Index extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
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
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("SANJAI");
		contentPane.setForeground(new Color(128, 64, 64));
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE MANAGEMENT SYSTEMS");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(128, 64, 64));
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setBounds(45, 10, 360, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          
			      dispose();       // Step 3.1: close 
			      new AddEmployee(); 	//Index window
			}
		});
		btnNewButton.setBounds(30, 58, 132, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Fetch Employee");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FetchEmployee();
			}
		});
		btnNewButton_1.setBounds(30, 99, 132, 31);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Fetch All");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FetchAllEmployee();
			}
		});
		btnNewButton_2.setBounds(30, 140, 132, 31);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Update Employee");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UpdateEmployee();
			}
		});
		btnNewButton_3.setBounds(211, 99, 132, 31);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete Employee");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DeleteEmployee();
			}
		});
		btnNewButton_4.setBounds(211, 58, 132, 31);
		contentPane.add(btnNewButton_4);
		
	   setVisible(true);


	}
}
