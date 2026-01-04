package project03;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class FetchAllEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private DefaultTableModel model;
    private JTable table;
    private JTextField textId;
    private JTextField txtName;
    private JTextField txtgender;
    private JTextField txtsalary;
	protected JLabel txtId;
	private JFrame frame;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FetchAllEmployee frame = new FetchAllEmployee();
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
	public FetchAllEmployee() {
		

        setTitle("Fetch All Employees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel title = new JLabel("ALL EMPLOYEES", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(128, 64, 64));
        title.setBounds(180, 10, 240, 30);
        contentPane.add(title);
        
        model = new DefaultTableModel();
        model.addColumn("EMP ID");
        model.addColumn("EMP NAME");
        model.addColumn("EMP GENDER");
        model.addColumn("EMP SALARY");

        table = new JTable(model);

        table.getTableHeader().setBackground(new Color(64, 64, 64));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        // ===== ROW STYLE (ZEBRA + SELECTION) =====
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(new Color(0, 120, 215));
                    c.setForeground(Color.WHITE);
                } else {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(230, 230, 230)); // light grey
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        table.setRowHeight(25);
        table.setGridColor(Color.GRAY);
        table.setShowVerticalLines(false);

        // ===== SCROLL PANE =====
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 550, 260);
        contentPane.add(scrollPane);

        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(480, 330, 90, 25);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            dispose();
            new Index();
        });

        fetchAllEmployees();

        setVisible(true);
    }

    // ===== FETCH DATA FROM DATABASE =====
    private void fetchAllEmployees() {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM employee";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0); // clear table

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("emp_gender"),
                        rs.getInt("emp_salary")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
		
	}

	}

