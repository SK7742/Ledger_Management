package com.utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.constants.Message;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;

public class Check extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Check dialog = new Check();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int check=0;
	static int id;
	static String check_string="",damount="";
	public static String particulars;
	public static String price;
	public static String quantity;
	public static String name="",address="",mobile="";
	public static void get_data(String temp)
	{
		check_string=temp;
		
	}
	public static void debit(String temp_data,int temp_id) 
	{
		damount=temp_data;
		id=temp_id;
	}
	public static void credit(String temp_particulars,String temp_price,String temp_quantity,int temp_id)
	{
		particulars=temp_particulars;
		price=temp_price;
		quantity=temp_quantity;
		id=temp_id;	
	}
	public static void delete(int temp_id)
	{
		id=temp_id;
	}
	public static void create(String temp_name,String temp_adds,String temp_mob)
	{
		name=temp_name;
		address=temp_adds;
		mobile=temp_mob;
	}
	/**
	 * Create the dialog.
	 */
	public Check() {
		setTitle("Confirm Dialog");
		setBounds(500, 250, 248, 192);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblConfirm = new JLabel("Confirm");
			lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
			lblConfirm.setBounds(57, 11, 104, 49);
			lblConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
			contentPanel.add(lblConfirm);
		}
		{
			JButton btnYes = new JButton("Yes");
			btnYes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(check_string.equals("debit"))
					{
							try
							{
						            float balance,debit;
						            Connection connection =com.dao.Connect.DBConnector();
						            String value="",query="select Balance from accounts where ID="+id;
						            PreparedStatement pst=connection.prepareStatement(query);
						            ResultSet rs=pst.executeQuery(query);
						            while(rs.next())
						            {
						                value=rs.getString("Balance");
						            }
						            balance=Float.parseFloat(value);
						            debit=Float.parseFloat(damount);
						            balance=balance-debit;
						            String temp_bal=String.valueOf(balance);
						            String query2="update accounts set Balance=? where ID="+id;
						            PreparedStatement pst2=connection.prepareStatement(query2);
						            pst2.setString(1, temp_bal);
						            int update=pst2.executeUpdate();
						            String query3="select CURDATE()";
						            PreparedStatement pst3=connection.prepareStatement(query3);
						            ResultSet rs3=pst3.executeQuery(query3);
						            String date="";
						            while(rs3.next())
						            {
						                date=rs3.getString("CURDATE()");
						            }
						            String query4="update accounts set Last_Updated_On='"+date+"' where ID="+id;
						            PreparedStatement pst4=connection.prepareStatement(query4);
						            int y=pst4.executeUpdate();
						            String query5="insert into ledger_for_id_"+id+" (Date,Debit,Balance) "
						                    + "values ('"+date+"','"+damount+"','"+temp_bal+"')";
						            PreparedStatement pst5=connection.prepareStatement(query5);
						            int a=pst5.executeUpdate();
						            if(update==1)
						            JOptionPane.showMessageDialog(null,Message.UPDATED_SUCCESSFULLY);
						             dispose();
							}
						        catch(Exception f)
						        {
						            JOptionPane.showMessageDialog(null, f);
						            dispose();
						            com.service.Debit.main(null);
						           
						        }
						    
						
						}
					else if(check_string=="credit")
					{
						try
						{
						        float balance=0,credit;
						        float camount,temp_price,temp_quan;
						        temp_price=Float.parseFloat(price);
						        temp_quan=Float.parseFloat(quantity);
						        camount=temp_price*temp_quan;
						        Connection connection = com.dao.Connect.DBConnector();
						        String value="",query="select Balance from accounts where ID="+id;
						        PreparedStatement pst=connection.prepareStatement(query);
						        ResultSet rs=pst.executeQuery(query);
						        while(rs.next())
						        {
						            value=rs.getString("Balance");
						        }
						        balance=Float.parseFloat(value);						
						        credit=camount;
						        balance=balance+credit;
						        String temp_bal=String.valueOf(balance);
						        String query2="update accounts set Balance=? where ID="+id;
								PreparedStatement pst2=connection.prepareStatement(query2);
								pst2.setString(1, temp_bal);
								int update=pst2.executeUpdate();
								String query3="select CURDATE()";
								PreparedStatement pst3=connection.prepareStatement(query3);
								ResultSet rs3=pst3.executeQuery(query3);
								String date="";
						       
								while(rs3.next())
								{
								    date=rs3.getString("CURDATE()");
								}
								String query4="update accounts set Last_Updated_On='"+date+"' where ID="+id;
								PreparedStatement pst4=connection.prepareStatement(query4);
								int y=pst4.executeUpdate();
								String query5="insert into ledger_for_id_"+id+"(Particulars,Price,Quantity,Date,Credit,Balance) "
							                    + "values ('"+particulars+"','"+price+"','"+quantity+"','"+date+"',"+camount+","+temp_bal+")";
								PreparedStatement pst5=connection.prepareStatement(query5);
								int a=pst5.executeUpdate();
								if(update==1)
								JOptionPane.showMessageDialog(null,Message.UPDATED_SUCCESSFULLY);
							    }
							    catch(Exception f)
							    {
							    	JOptionPane.showMessageDialog(null, Message.EMPTY_STRING);
						            dispose();
						            com.service.Credit.main(null);
							    }
							    dispose();
						}
					else if(check_string=="delete")
					{
						try
						{
								Connection connection =com.dao.Connect.DBConnector();
					            String query="drop table ledger_for_id_"+id;
					            PreparedStatement pst=connection.prepareStatement(query);
					            pst.executeUpdate();  
					            
					            String query2="delete from accounts where ID="+id;
					            PreparedStatement pst2=connection.prepareStatement(query2);
					            int a=pst2.executeUpdate();
					            if(a==1)
					            JOptionPane.showMessageDialog(null, Message.DELETE_ACCOUNT);
						}
						catch(Exception g)
						{
								JOptionPane.showMessageDialog(null, g);
						}
						com.service.Main.main(null);		
					}
					
					else if(check_string=="create")
					{
						if(name.equals("") ||address.equals("") || mobile.equals(""))
							{
								JOptionPane.showMessageDialog(null, Message.EMPTY_STRING);
								dispose();
								com.service.Create.main(null);
							}
						else 
						{
								try
						        {
									String Balance="0";
						            Connection connection = com.dao.Connect.DBConnector();
						            String query="insert into accounts (Name,Address,Mobile,Balance) values (?,?,?,?)";
						            PreparedStatement pst=connection.prepareStatement(query);
						            pst.setString(1, name);
						            pst.setString(2, address);
						            pst.setString(3, mobile);
						            pst.setString(4, Balance);
						            pst.executeUpdate();
						            String query2="select ID from accounts where Mobile="+mobile;
						            java.sql.PreparedStatement pst2=connection.prepareStatement(query2);								
						            java.sql.ResultSet rs=pst2.executeQuery();
						            String temp="";
						            while(rs.next())
						            {
						                temp=rs.getString("ID");
						            }
						            String query3="CREATE TABLE ledger_for_id_"+temp+" ("
						                    +"S_NO int NOT NULL AUTO_INCREMENT,"
						                    +"Particulars varchar(50) ,"
						                    +"Price varchar(10) ,"
						                    +"Quantity varchar(10),"
						                    +"Date varchar(10),"
						                    +"Debit varchar(10) ,"
						                    +"Credit varchar(10) ,"
						                    +"Balance varchar(15),"
						                    +"PRIMARY KEY (S_NO)"
						                    +")";
						            java.sql.PreparedStatement pst3=connection.prepareStatement(query3);
						            int a=pst3.executeUpdate();
						            JOptionPane.showMessageDialog(null, Message.NEW_ACCOUNT);
						       }
						        catch(Exception f)
						        {
						            JOptionPane.showMessageDialog(null,f);
						        }
						        dispose();
							}
					    }
					}
				});
			
			btnYes.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(check_string.equals("debit"))
					{
						if (e.getKeyCode()==KeyEvent.VK_ENTER)
						{
							 try
						        {
						            float balance,debit;
						            Connection connection =com.dao.Connect.DBConnector();
						            String value="",query="select Balance from accounts where ID="+id;
						            PreparedStatement pst=connection.prepareStatement(query);
						            ResultSet rs=pst.executeQuery(query);
						            while(rs.next())
						            {
						                value=rs.getString("Balance");
						            }
						            balance=Float.parseFloat(value);
						            debit=Float.parseFloat(damount);
						            balance=balance-debit;
						            String temp_bal=String.valueOf(balance);
						            String query2="update accounts set Balance=? where ID="+id;
						            PreparedStatement pst2=connection.prepareStatement(query2);
						            pst2.setString(1, temp_bal);
						            int update=pst2.executeUpdate();
						            String query3="select CURDATE()";
						            PreparedStatement pst3=connection.prepareStatement(query3);
						            ResultSet rs3=pst3.executeQuery(query3);
						            String date="";
						            while(rs3.next())
						            {
						                date=rs3.getString("CURDATE()");
						            }
						            String query4="update accounts set Last_Updated_On='"+date+"' where ID="+id;
						            PreparedStatement pst4=connection.prepareStatement(query4);
						            int y=pst4.executeUpdate();
						            String query5="insert into ledger_for_id_"+id+" (Date,Debit,Balance) "
						                    + "values ('"+date+"','"+damount+"','"+temp_bal+"')";
						            PreparedStatement pst5=connection.prepareStatement(query5);
						            int a=pst5.executeUpdate();
						            if(update==1)
						            JOptionPane.showMessageDialog(null,Message.UPDATED_SUCCESSFULLY);
						             dispose();
						        }
						        catch(Exception f)
						        {
						            JOptionPane.showMessageDialog(null, f);
						            dispose();
						            com.service.Debit.main(null);
						           
						        }
						    }
						else if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
						{
							dispose();
						}
						}
					else if(check_string=="credit")
					{
						if (e.getKeyCode()==KeyEvent.VK_ENTER)
						{
							try
						    {
						        float balance=0,credit;
						        float camount,temp_price,temp_quan;
						        temp_price=Float.parseFloat(price);
						        temp_quan=Float.parseFloat(quantity);
						        camount=temp_price*temp_quan;
						        Connection connection = com.dao.Connect.DBConnector();
						        String value="",query="select Balance from accounts where ID="+id;
						        PreparedStatement pst=connection.prepareStatement(query);
						        ResultSet rs=pst.executeQuery(query);
						        while(rs.next())
						        {
						            value=rs.getString("Balance");
						        }
						        balance=Float.parseFloat(value);						
						        credit=camount;
						        balance=balance+credit;
						        String temp_bal=String.valueOf(balance);
						        String query2="update accounts set Balance=? where ID="+id;
							PreparedStatement pst2=connection.prepareStatement(query2);
							pst2.setString(1, temp_bal);
							int update=pst2.executeUpdate();
							String query3="select CURDATE()";
						        PreparedStatement pst3=connection.prepareStatement(query3);
							ResultSet rs3=pst3.executeQuery(query3);
							String date="";
						       
							while(rs3.next())
							{
							    date=rs3.getString("CURDATE()");
							}
							String query4="update accounts set Last_Updated_On='"+date+"' where ID="+id;
							PreparedStatement pst4=connection.prepareStatement(query4);
							int y=pst4.executeUpdate();
							String query5="insert into ledger_for_id_"+id+"(Particulars,Price,Quantity,Date,Credit,Balance) "
						                    + "values ('"+particulars+"','"+price+"','"+quantity+"','"+date+"',"+camount+","+temp_bal+")";
							PreparedStatement pst5=connection.prepareStatement(query5);
							int a=pst5.executeUpdate();
							if(update==1)
							JOptionPane.showMessageDialog(null,Message.UPDATED_SUCCESSFULLY);
						    }
						    catch(Exception f)
						    {
						    	JOptionPane.showMessageDialog(null, Message.EMPTY_STRING);
					            dispose();
					            com.service.Credit.main(null);
						    }
						    dispose();
						    }
						else if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
						{
							dispose();
						}
					}
					
					else if(check_string=="delete")
					{
						if (e.getKeyCode()==KeyEvent.VK_ENTER)
						{
							try
						    {
								Connection connection =com.dao.Connect.DBConnector();
					            String query="drop table ledger_for_id_"+id;
					            PreparedStatement pst=connection.prepareStatement(query);
					            pst.executeUpdate();  
					            
					            String query2="delete from accounts where ID="+id;
					            PreparedStatement pst2=connection.prepareStatement(query2);
					            int a=pst2.executeUpdate();
					            if(a==1)
					            JOptionPane.showMessageDialog(null, Message.DELETE_ACCOUNT);
						    }
							catch(Exception g)
							{
								JOptionPane.showMessageDialog(null, g);
							}
							com.service.Main.main(null);
							
						}
						else if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
						{
							dispose();
						}
					}
					else if(check_string=="create")
					{
						if(e.getKeyCode()==KeyEvent.VK_ENTER)
						{
							if(name.equals("") ||address.equals("") || mobile.equals(""))
							{
								JOptionPane.showMessageDialog(null, Message.EMPTY_STRING);
								dispose();
								com.service.Create.main(null);
							}
							else 
							{
								try
						        {
									String Balance="0";
						            Connection connection = com.dao.Connect.DBConnector();
						            String query="insert into accounts (Name,Address,Mobile,Balance) values (?,?,?,?)";
						            PreparedStatement pst=connection.prepareStatement(query);
						            pst.setString(1, name);
						            pst.setString(2, address);
						            pst.setString(3, mobile);
						            pst.setString(4, Balance);
						            pst.executeUpdate();
						            String query2="select ID from accounts where Mobile="+mobile;
						            java.sql.PreparedStatement pst2=connection.prepareStatement(query2);								
						            java.sql.ResultSet rs=pst2.executeQuery();
						            String temp="";
						            while(rs.next())
						            {
						                temp=rs.getString("ID");
						            }
						            String query3="CREATE TABLE ledger_for_id_"+temp+" ("
						                    +"S_NO int NOT NULL AUTO_INCREMENT,"
						                    +"Particulars varchar(50) ,"
						                    +"Price varchar(10) ,"
						                    +"Quantity varchar(10),"
						                    +"Date varchar(10),"
						                    +"Debit varchar(10) ,"
						                    +"Credit varchar(10) ,"
						                    +"Balance varchar(15),"
						                    +"PRIMARY KEY (S_NO)"
						                    +")";
						            java.sql.PreparedStatement pst3=connection.prepareStatement(query3);
						            int a=pst3.executeUpdate();
						            JOptionPane.showMessageDialog(null, Message.NEW_ACCOUNT);
						        }
						        catch(Exception f)
						        {
						            JOptionPane.showMessageDialog(null,f);
						        }
						        dispose();
							}
					    }
						
						else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
						{
							dispose();
						}
					}
				}
				
			});
			btnYes.setBounds(23, 91, 89, 23);
			contentPanel.add(btnYes);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					}
			});
			btnCancel.setBounds(122, 91, 89, 23);
			contentPanel.add(btnCancel);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Check.class.getResource("/com/image/background.jpg")));
			lblNewLabel.setBounds(0, 0, 232, 153);
			contentPanel.add(lblNewLabel);
		}
	}
	

}
