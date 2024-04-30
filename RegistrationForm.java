
// Java program to implement
// a Simple Registration Form
// using Java Swing
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

import java.sql.ResultSet;
import java.awt.Color;

import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.Toolkit;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.BorderFactory;

import javax.swing.ButtonGroup;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JRadioButton;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")

class RegistrationFormGUI extends JFrame {

	Connection con;
	Statement stmt;
	PreparedStatement preStatement, updatePreStmt;

	JLabel title, idLabel, nameLabel, genderLabel, addressLabel, contactLabel;

	JTextField idField, nameField, genderField, addressField, contactField;

	JButton registerButton, exitButton, updateButton, deleteButton, resetButton,refresh;

	JRadioButton male, female;
	ButtonGroup bg;

	JPanel panel;
	JTable table;

	DefaultTableModel model;

	JScrollPane scrollpane;

	public RegistrationFormGUI() {
		// TODO Auto-generated constructor stub

		super("REGISTRATION FORM");
		setSize(770, 420);
		setLayout(null);

		// Calling connect method, this will connect us to database
		connect();

		// Defining Labels

		title = new JLabel("Registration Form");

		title.setBounds(60, 7, 200, 30);

		idLabel = new JLabel("ID");

		idLabel.setBounds(30, 50, 60, 30);

		nameLabel = new JLabel("Name");

		nameLabel.setBounds(30, 85, 60, 30);

		genderLabel = new JLabel("Gender");

		genderLabel.setBounds(30, 120, 60, 30);

		addressLabel = new JLabel("Address");

		addressLabel.setBounds(30, 155, 60, 30);

		contactLabel = new JLabel("Contact");

		contactLabel.setBounds(30, 190, 60, 30);

		// Defining ID field
		idField = new JTextField();

		idField.setBounds(95, 50, 130, 30);
		idField.setEnabled(false);

		// Defining Name field
		nameField = new JTextField();

		nameField.setBounds(95, 85, 130, 30);

		// Defining Gender Buttons
		male = new JRadioButton("Male");

		male.setBounds(95, 120, 60, 30);

		female = new JRadioButton("Female");
		female.setBounds(155, 120, 70, 30);

		bg = new ButtonGroup();

		bg.add(male);

		bg.add(female);

		addressField = new JTextField();

		addressField.setBounds(95, 155, 130, 30);

		contactField = new JTextField();

		contactField.setBounds(95, 190, 130, 30);

		// fixing all Label,TextField,RadioButton

		add(title);

		add(idLabel);

		add(nameLabel);

		add(genderLabel);

		add(addressLabel);

		add(contactLabel);

		add(idField);

		add(nameField);

		add(male);

		add(female);

		add(addressField);

		add(contactField);

		// Defining Exit Button

		exitButton = new JButton("Exit");

		exitButton.setBounds(25, 250, 80, 25);

		// Defining Register Button

		registerButton = new JButton("Register");

		registerButton.setBounds(110, 250, 100, 25);

		// Defining Update Button

		updateButton = new JButton("Update");

		updateButton.setBounds(110, 285, 100, 25);

		updateButton.setEnabled(false);

		// Defining Delete Button

		deleteButton = new JButton("Delete");

		deleteButton.setBounds(25, 285, 80, 25);

		deleteButton.setEnabled(false);

		// Defining Reset Button

		resetButton = new JButton("Reset");

		resetButton.setBounds(60, 320, 100, 25);

		resetButton.setEnabled(false);

		// fixing all Buttons

		add(exitButton);

		add(registerButton);

		add(updateButton);

		add(deleteButton);

		add(resetButton);

		// Defining Panel

		panel = new JPanel();

		panel.setLayout(new GridLayout());

		panel.setBounds(250, 20, 480, 330);

		panel.setBorder(BorderFactory.createDashedBorder(Color.blue));

		add(panel);

		// Defining Refresh Button

		refresh = new JButton("Refresh Table");

		refresh.setBounds(350, 350, 270, 15);

		add(refresh);

		// Defining Model for table

		model = new DefaultTableModel();

		// Adding object of DefaultTableModel into JTable

		table = new JTable(model);

		// Fixing Columns move

		table.getTableHeader().setReorderingAllowed(false);

		// Defining Column Names on model

		model.addColumn("S.No");

		model.addColumn("ID");

		model.addColumn("Name");

		model.addColumn("Gender");

		model.addColumn("Address");
		model.addColumn("Contact");

		// Enable Scrolling on table

		scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,

				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		panel.add(scrollpane);

		// Displaying Frame in Center of the Screen

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation(dim.width / 2 - this.getSize().width / 2,

				dim.height / 2 - this.getSize().height / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);

		setVisible(true);

	}

	public void connect() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/windowapp", "root", "");

			stmt = con.createStatement();

			preStatement = con.prepareStatement("insert into employee(name,gender,address,contact) values(?,?,?,?)");

		} catch (Exception e) {

			System.out.print(e.getMessage());

		}

	}

}

@SuppressWarnings("serial")
public class RegistrationForm extends RegistrationFormGUI {

	String gender = "";

	ResultSet rst, rstLast;

	Object[][] data;

	int serialNo;

	String SHOW = "Show";

	RegistrationFormGUI formGUIObject;

	// Defining Constructor

	RegistrationForm() {

		nameField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (nameField.getText().length() >= 15)

					e.consume();

			}

		});

		male.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				gender = "Male";

			}
		});

		female.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				gender = "Female";

			}

		});

		addressField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (addressField.getText().length() > 50)

					e.consume();

			}

		});

		contactField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') ||

						(c == KeyEvent.VK_BACK_SPACE) ||

						(c == KeyEvent.VK_DELETE))) {

					// getToolkit().beep();

					e.consume();

				}

				if (contactField.getText().length() > 9)

					e.consume();

			}

		});

		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {

					con.close();

					System.exit(0);

				} catch (Exception ex) {

					System.out.println(ex.getMessage());

				}

			}

		});

		registerButton.addActionListener(new AbstractAction(SHOW) {

			public void actionPerformed(ActionEvent ae) {

				try {

					if (ae.getSource() == registerButton) {

						if (nameField.getText().equals(""))

							JOptionPane.showMessageDialog(idField,"Please provide Name_Field");

						else if (addressField.getText().equals(""))

							JOptionPane.showMessageDialog(idField,"Please provide Address_Field");

						else if (contactField.getText().equals(""))
							JOptionPane.showMessageDialog(idField, "Please provide Contact_Field");

						else if (gender.equals(""))

							JOptionPane.showMessageDialog(idField, "Please select Gender");

						else {

							// Fetching column values from Database

							preStatement.setString(1, nameField.getText());

							preStatement.setString(2, gender);

							preStatement.setString(3, addressField.getText());

							preStatement.setString(4, contactField.getText());

							// Executing MySQL Update Query

							int i = preStatement.executeUpdate();

							if (i == 1) {

								JOptionPane.showMessageDialog(panel,"Successfully Registered");

							}

							// showing last row

							rstLast = stmt.executeQuery("select *from employee");

							rstLast.last();

							String string = serialNo++ + "," + String.valueOf(rstLast.getLong(1)) + ","
									+ rstLast.getString(2) + "," + rstLast.getString(3) + "," + rstLast.getString(4)
									+ "," + rstLast.getString(5);

							Object[] row = null;

							row = string.split(",");

							model.addRow(row);

							panel.revalidate();

							// fields are blank

							blankFields();

						}

					}

				} catch (Exception ex) {

					System.out.println(ex.getMessage());

				}

			}

		});

		updateButton.addActionListener(new AbstractAction(SHOW) {

			public void actionPerformed(ActionEvent e) {

				if (nameField.getText().equals(""))

					JOptionPane.showMessageDialog(idField,	"Please provide Name_Field");

				else if (addressField.getText().equals(""))

					JOptionPane.showMessageDialog(idField,"Please provide Address_Field");

				else if (contactField.getText().equals(""))

					JOptionPane.showMessageDialog(idField,"Please provide Contact_Field");

				else if (gender.equals(""))

					JOptionPane.showMessageDialog(idField,"Please select Gender");

				else {

					int r = table.getSelectedRow();

					try {

						if (r >= 0) {

							if (male.isSelected())

								gender = male.getText();
							else
								gender = female.getText();

							String id = (String) table.getModel().getValueAt(r, 1);

							updatePreStmt = con.prepareStatement("update employee set name=?,gender=?,address=?,contact=?where id=" + id);

							updatePreStmt.setString(1, nameField.getText());

							updatePreStmt.setString(2, gender);

							updatePreStmt.setString(3, addressField.getText());

							updatePreStmt.setString(4, contactField.getText());

							int i = updatePreStmt.executeUpdate();

							if (i == 1) {

								table.setValueAt(nameField.getText(), r, 2);

								table.setValueAt(gender, r, 3);

								table.setValueAt(addressField.getText(), r, 4);

								table.setValueAt(contactField.getText(), r, 5);

							}

							else
								JOptionPane.showMessageDialog(panel,"ID does't Exists in Database");

						}

					} catch (Exception ex) {

						System.out.println("Update section: " +	ex.getMessage());

					}

				}

			}

		});

		// Registering Anonymous Listener Class

		deleteButton.addActionListener(new AbstractAction(SHOW) {

			public void actionPerformed(ActionEvent e) {

				try {

					// Getting Selected Row No

					int r = table.getSelectedRow();

					if (r >= 0) {

						if (JOptionPane.showConfirmDialog(panel, "Are you sure want to Delete this 'RECORD' ?",
								"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

							String id = (String) table.getModel().getValueAt(r, 1);

							// Executing MySQL Update Command

							int i = stmt.executeUpdate("delete from employee where id=" + id);

							if (i == 1) {

								model.removeRow(r);

								// fields are blank

								blankFields();

								registerButton.setEnabled(true);

								deleteButton.setEnabled(false);

								updateButton.setEnabled(false);

							}

						}

					}

				} catch (Exception ex) {

					System.out.println(ex.getMessage());

				}

			}

		});

		// Registering Anonymous Listener Class

		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// calling method resetFields()

				resetFields();
				registerButton.setEnabled(true);
				updateButton.setEnabled(false);
				deleteButton.setEnabled(false);
				resetButton.setEnabled(false);

			}

		});
		// Registering Anonymous Listener Class
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calling refresh() method
				refreshTable();
			}
		});

		// Registering Anonymous Listener Class

		table.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {

				// Getting Selected Row No

				int r = table.getSelectedRow();

				if (r >= 0) {

					deleteButton.setEnabled(true);

					updateButton.setEnabled(true);

					resetButton.setEnabled(true);

					registerButton.setEnabled(false);

					// Fetching records from Table on Fields

					idField.setText("" + table.getModel().getValueAt(r, 1));

					nameField.setText("" + table.getModel().getValueAt(r, 2));

					if (table.getModel().getValueAt(r, 3).equals("Male"))

						male.setSelected(true);

					else

						female.setSelected(true);

					addressField.setText("" + table.getModel().getValueAt(r, 4));

					contactField.setText("" + table.getModel().getValueAt(r, 5));

				}

			}

			// @Override
			public void mouseReleased(MouseEvent arg0) {
			}

			// @Override
			public void mousePressed(MouseEvent arg0) {
			}

			// @Override
			public void mouseExited(MouseEvent arg0) {
			}

			// @Override
			public void mouseEntered(MouseEvent arg0) {
			}

		});

		// Displaying rows into the Frame table

		addRows();
	}

	// addRows method

	private void addRows() {

		try {

			Object[] row = null;

			// Generating Serial No

			serialNo = 1;

			rst = stmt.executeQuery("select *from employee");

			while (rst.next()) {

				String string = serialNo++ + "," + String.valueOf(rst.getLong(1)) + "," + rst.getString(2) + ","
						+ rst.getString(3) + "," + rst.getString(4) + "," + rst.getString(5);

				row = string.split(",");

				// Adding records in table model

				model.addRow(row);

			}

			panel.revalidate();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void resetFields() {

		// calling method blankfields()

		blankFields();

	}

	// refresh method

	private void refreshTable() {

		// removing all the rows of the table

		DefaultTableModel dm = (DefaultTableModel) table.getModel();

		dm.getDataVector().removeAllElements();

		System.out.println("Refresh Table");

		// calling method addRows

		addRows();

	}

	private void blankFields() {

		// fields will be blank

		idField.setText("");

		nameField.setText("");

		gender = "";

		bg.clearSelection();

		addressField.setText("");

		contactField.setText("");

	}

// Driver Code

	public static void main(String[] args) {

		new RegistrationForm();

	}
}
