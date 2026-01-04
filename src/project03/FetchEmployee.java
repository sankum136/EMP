package project03;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class FetchEmployee extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField textField;   // ID input
    private JTextArea textArea;     // result display

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new FetchEmployee();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FetchEmployee() {

        setTitle("Fetch Employee");
        setSize(459, 300);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(0, 0, 64));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FETCH EMPLOYEE", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 128));
        title.setBounds(100, 5, 250, 30);
        getContentPane().add(title);

        JLabel lblEmployeeId = new JLabel("Employee ID");
        lblEmployeeId.setBackground(new Color(0, 0, 0));
        lblEmployeeId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEmployeeId.setForeground(new Color(128, 255, 255));
        lblEmployeeId.setBounds(40, 150, 120, 25);
        getContentPane().add(lblEmployeeId);

        textField = new JTextField();
        textField.setBounds(160, 150, 140, 25);
        getContentPane().add(textField);

        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.setBackground(new Color(255, 128, 64));
        btnSubmit.setBounds(170, 190, 100, 30);
        getContentPane().add(btnSubmit);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(255, 255, 255));
        textArea.setBounds(70, 40, 300, 100);
        getContentPane().add(textArea);

        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(340, 230, 80, 25);
        getContentPane().add(btnBack);

        // BUTTON ACTIONS
        btnSubmit.addActionListener(e -> fetchEmployee());

        btnBack.addActionListener(e -> {
            dispose();
            new Index();
        });

        setVisible(true);
    }

    // ================= FETCH METHOD =================
    
    private void fetchEmployee() {
    	
    	int id;
    	
    	try {
			id = Integer.parseInt(textField.getText());
			
		} catch (NumberFormatException e) {
			textArea.setText("please enter a vaild Employee ID");
			return;
		}
    	
    	try (Connection con = DBConnection.getConnection()){
			
    		String sql = "SELECT * FROM employee where emp_id = ?";
    		PreparedStatement ps = con.prepareStatement(sql);
    		
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		
    		if (rs.next()) {
				textArea.setText(
						"ID  :  " + rs.getInt("emp_id") + "\n" +
						"Name         :  " + rs.getString("emp_name") + "\n" +
						"Gender       :  " + rs.getString("emp_gender") + "\n" +
						"Salary       :  " + rs.getInt("emp_salary") 
						);
			} else {
				textArea.setText("Employee not found");
			}
		} catch (Exception e) {
			textArea.setText("Error: " + e.getMessage());
		}
    
    }
}

