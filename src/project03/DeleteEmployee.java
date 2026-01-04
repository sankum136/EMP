package project03;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class DeleteEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmployee frame = new DeleteEmployee();
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
	public DeleteEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteEmployees = new JLabel("DELETE  EMPLOYEES");
		lblDeleteEmployees.setForeground(new Color(128, 64, 64));
		lblDeleteEmployees.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDeleteEmployees.setBackground(Color.YELLOW);
		lblDeleteEmployees.setBounds(109, 10, 219, 27);
		contentPane.add(lblDeleteEmployees);
		
		JLabel lblNewLabel = new JLabel("ENTER EMPLOYEE ID");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(57, 64, 138, 46);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 78, 127, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Index();
			}
		});
		btnNewButton.setForeground(new Color(128, 128, 128));
		btnNewButton.setBounds(342, 233, 84, 20);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SUMBIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String idText = textField.getText().trim();
				
				if (!idText.matches("\\d+")) {
					JOptionPane.showInternalMessageDialog(DeleteEmployee.this, "Employee ID must be numberic");
					return;
				}
				
				int id = Integer.parseInt(idText);
				
				int confirm = JOptionPane.showConfirmDialog(DeleteEmployee.this, "Are you sure you want to delete employee ID " + id + "?", "Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (confirm != JOptionPane.YES_OPTION) {
					return;
				}
				
				try (Connection con = DBConnection.getConnection()){
					
					String sql = "DELETE FROM EMPLOYEE WHERE emp_id = ?";
					
					PreparedStatement ps = con.prepareStatement(sql);
					
					ps.setInt(1, id);
					
					int rows = ps.executeUpdate();
					
					if (rows > 0) {
						JOptionPane.showMessageDialog(DeleteEmployee.this,  "Employee Deleted Successfully");
						textField.setText("");
					} else {
						JOptionPane.showInternalMessageDialog(DeleteEmployee.this, "Employee ID not found");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(DeleteEmployee.this, "Error" + e2.getMessage());
				}
			}
		});
		btnNewButton_1.setBackground(new Color(255, 128, 0));
		btnNewButton_1.setBounds(156, 133, 84, 20);
		contentPane.add(btnNewButton_1);
		
	
		setTitle("Delete Employee");
		setSize(455, 300);
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}
