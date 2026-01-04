package project03;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class AddEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
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
	public AddEmployee() {
		getContentPane().setBackground(new Color(0, 0, 0));
		getContentPane().setLayout(null);
		
		JLabel lblAddEmployees = new JLabel("ADD  EMPLOYEES");
		lblAddEmployees.setForeground(new Color(128, 64, 64));
		lblAddEmployees.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddEmployees.setBackground(Color.YELLOW);
		lblAddEmployees.setBounds(120, 10, 200, 27);
		getContentPane().add(lblAddEmployees);
		
		JLabel lblNewLabel = new JLabel("ENTER EMPLOYEE");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(49, 78, 174, 12);
		getContentPane().add(lblNewLabel);
		
		JLabel lblEnterName = new JLabel("ENTER NAME");
		lblEnterName.setForeground(new Color(255, 255, 255));
		lblEnterName.setBounds(49, 112, 165, 12);
		getContentPane().add(lblEnterName);
		
		JLabel lblEnter = new JLabel("ENTER GENDER");
		lblEnter.setForeground(new Color(255, 255, 255));
		lblEnter.setBounds(49, 144, 165, 12);
		getContentPane().add(lblEnter);
		
		JLabel lblEnterSalary = new JLabel("ENTER SALARY");
		lblEnterSalary.setForeground(new Color(255, 255, 255));
		lblEnterSalary.setBounds(49, 175, 165, 12);
		getContentPane().add(lblEnterSalary);
		
		textField = new JTextField();
		textField.setBounds(180, 75, 124, 18);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 109, 124, 18);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(180, 141, 124, 18);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(180, 172, 124, 18);
		getContentPane().add(textField_3);
		
		JButton btnNewButton = new JButton("SUMBIT");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(textField.getText());
                String name = textField_1.getText();
                String gender = textField_2.getText();
                int salary = Integer.parseInt(textField_3.getText());
				
				try (Connection con = DBConnection.getConnection()) {
					String sql = "INSERT INTO employee (emp_id, emp_name, emp_gender, emp_salary) VALUES(?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					
					 ps.setInt(1, id);
	                 ps.setString(2, name);
	                 ps.setString(3, gender);
	                 ps.setInt(4, salary);

					  ps.executeUpdate();
					  
	               JOptionPane.showMessageDialog(null, "Employee Added Successfully");
	               
	               textField.setText("");
	               textField_1.setText("");
	               textField_2.setText("");
	               textField_3.setText("");


				} catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Error: " + e2.getMessage());

				}
				
			}
		});
		btnNewButton.setBackground(new Color(255, 128, 0));
		btnNewButton.setBounds(126, 208, 84, 20);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Index();
			}
		});
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setBounds(342, 233, 84, 20);
		getContentPane().add(btnNewButton_1);
		
		

		        setTitle("Add Employee");
		        setSize(459, 300);
		        getContentPane().setLayout(null);
		        setVisible(true);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
	}

}
