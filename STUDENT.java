package newone;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;




public class STUDENT {

	private JFrame frame;
	private JTextField sname;
	private JTextField sdate;
	private JTextField edate;
	private JTextField sgrade;
	private JTable table;
	private JTextField sid;
	private JTextField sreg;

	public static void main(String[] args)throws Exception
	{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					STUDENT window = new STUDENT();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public STUDENT() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void table_load()
	{
		try {
			pst=con.prepareStatement("select * from Stdu");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	

	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student", "root","Vasim@2003");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	               ex.printStackTrace();
	        }

	    }
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 937, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Library");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(323, -11, 198, 88);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 87, 395, 226);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("student name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(29, 34, 100, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_9 = new JLabel("start date");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setBounds(29, 156, 100, 27);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_11 = new JLabel("end date");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_11.setBounds(29, 196, 100, 27);
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_1_2 = new JLabel("book no.");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(29, 116, 100, 27);
		panel.add(lblNewLabel_1_2);
		
		sname = new JTextField();
		sname.setBounds(164, 40, 160, 19);
		panel.add(sname);
		sname.setColumns(10);
		
		sgrade = new JTextField();
		sgrade.setColumns(10);
		sgrade.setBounds(164, 116, 160, 19);
		panel.add(sgrade);
		
		sdate = new JTextField();
		sdate.setColumns(10);
		sdate.setBounds(164, 156, 160, 19);
		panel.add(sdate);
		
		edate = new JTextField();
		edate.setColumns(10);
		edate.setBounds(164, 196, 160, 19);
		panel.add(edate);
		
		JLabel lblNewLabel_1_1 = new JLabel("Reg num");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(29, 77, 65, 17);
		panel.add(lblNewLabel_1_1);
		
		sid = new JTextField();
		sid.setColumns(10);
		sid.setBounds(164, 78, 160, 19);
		panel.add(sid);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				         
				    String name,id,grade,dates,datee;
				    name = sname.getText();
				    id = sid.getText();
				    grade = sgrade.getText();
				    dates = sdate.getText();
				    datee = edate.getText();
				                
				     try {
				        pst = con.prepareStatement("insert into Stdu(sname,sid,book_no,sdate,edate)values(?,?,?,?,?)");
				        pst.setString(1, name);
				        pst.setString(2, id);
				        pst.setString(3, grade);
				        pst.setString(4, dates);
				        pst.setString(5, datee);
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				        
				        table_load();
				                       
				        sname.setText("");
				        sid.setText("");
				        sgrade.setText("");
				        sdate.setText("");
				        sreg.setText("");
				        edate.setText("");
				        sname.requestFocus();
				       }

				    catch (SQLException e1) 
				        {            
				       e1.printStackTrace();
				    }
				}
			
		});
		btnNewButton.setBounds(37, 323, 85, 48);
		frame.getContentPane().add(btnNewButton);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(427, 87, 468, 284);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 396, 406, 69);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Reg num");
		lblNewLabel_1_1_1.setBounds(43, 20, 80, 28);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_1);
		
		sreg = new JTextField();
		sreg.setBounds(120, 29, 187, 19);
		sreg.setColumns(10);
		panel_1.add(sreg);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id = sreg.getText();

		        try {
		            // Retrieve existing values
		            pst = con.prepareStatement("SELECT sname, book_no, sdate, edate FROM Stdu WHERE sid = ?");
		            pst.setString(1, id);
		            ResultSet existingValues = pst.executeQuery();
		            existingValues.next();  // Move to the first (and only) result

		            String existingName = existingValues.getString("sname");
		            String existingGrade = existingValues.getString("book_no");
		            String existingStartDate = existingValues.getString("sdate");
		            String existingEndDate = existingValues.getString("edate");

		            // Get new values
		            String newName = sname.getText();
		            String newGrade = sgrade.getText();
		            String newStartDate = sdate.getText();
		            String newEndDate = edate.getText();

		            // Replace null or empty values with existing values
		            if (newName == null || newName.isEmpty()) {
		                newName = existingName;
		            }
		            if (newGrade == null || newGrade.isEmpty()) {
		                newGrade = existingGrade;
		            }
		            if (newStartDate == null || newStartDate.isEmpty()) {
		                newStartDate = existingStartDate;
		            }
		            if (newEndDate == null || newEndDate.isEmpty()) {
		                newEndDate = existingEndDate;
		            }

		            // Update the record
		            pst = con.prepareStatement("update Stdu set sname=?, book_no=?, sdate=?, edate=? where sid = ?");
		            pst.setString(1, newName);
		            pst.setString(2, newGrade);
		            pst.setString(3, newStartDate);
		            pst.setString(4, newEndDate);
		            pst.setString(5, id);

		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Updated!");

		            table_load();

		            sname.setText("");
		            sid.setText("");
		            sgrade.setText("");
		            sdate.setText("");
		            edate.setText("");
		            sreg.setText("");
		            sname.requestFocus();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnUpdate.setBounds(556, 392, 85, 48);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		           String s_id;
		           s_id  = sreg.getText();
		           
		            try {
		                   pst = con.prepareStatement("delete from Stdu where sid =?");
		           
		                   pst.setString(1, s_id);
		                   pst.executeUpdate();
		                   JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		                   table_load();
		                  
	                
		                   sname.setText("");
		                   sid.setText("");
		                   sgrade.setText("");
		                   sdate.setText("");
					        edate.setText("");
					        sreg.setText("");
		                   sname.requestFocus();
		               }

		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		               }
			}
		});
		btnDelete.setBounds(697, 392, 85, 48);
		frame.getContentPane().add(btnDelete);
       
}
}
