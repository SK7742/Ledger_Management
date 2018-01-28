package com.service;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.constants.Message;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Search extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Search dialog = new Search();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	int count=0,flag=0;
	String acc = null,name = null,adds = null,mob = null,luo = null,bal = null, temp_id;
	static String search_query;
	public static void get_search_deatails(String temp_data)
	{
		search_query=temp_data;
	}
	public Search() {
		setBounds(-5, 2, 1375, 728);
		setTitle(Message.SEARCH_TITLE);
		getContentPane().setLayout(null);
		
		 // ScrollPane for Table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 1339, 633);
        getContentPane().add(scrollPane);

        // Table
        final JTable table = new JTable();
        table.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_F5)
				{
					try {
						table.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
        		else if (e.getKeyCode()==KeyEvent.VK_N)
        		{
        			com.service.Create.main(null);
        		}
        	}
        });
        scrollPane.setViewportView(table);
        
        	try
        	{
        		Connection connection=com.dao.Connect.DBConnector();
			
        		String query=search_query;
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
				
					 // Model for Table
		            DefaultTableModel model = new DefaultTableModel() {

		                public Class<?> getColumnClass(int column) {
		                    switch (column) {
		                    case 0:
		                        return Boolean.class;
		                    case 1:
		                        return String.class;
		                    case 2:
		                        return String.class;
		                    case 3:
		                        return String.class;
		                    case 4:
		                        return String.class;
		                    case 5:
		                        return String.class;
		                    case 6:
		                        return String.class;
		                    default:
		                        return String.class;
		                    }
		                }
		            };
		            table.setModel(model);

		            model.addColumn("Select");
		            model.addColumn("Account ID");
		            model.addColumn("Name");
		            model.addColumn("Address");
		            model.addColumn("Mobile");
		            model.addColumn("Last Updated On");
		            model.addColumn("Balance");
		           
						// Data Row
		            	while(rs.next())
		            	{
		            		acc=rs.getString("ID");
							name=rs.getString("Name");
							adds=rs.getString("Address");
							mob=rs.getString("Mobile");
							luo=rs.getString("Last_Updated_On");
							bal=rs.getString("Balance");
							
			            	for (int i = 0; i <1; i++) {
			                model.addRow(new Object[]{false,acc,name,adds,mob,luo,bal});
		            	}
		            }
				}
        	
			catch(Exception e)
	        {
	        		
	        } 
		getContentPane().setLayout(null);
		
		JButton button_1 = new JButton("Update");
		button_1.setBounds(1260, 655, 89, 23);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Debit");
		button_2.setBounds(1062, 655, 89, 23);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Ledger");
		button_3.setBounds(864, 655, 89, 23);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Credit");
		button_4.setBounds(963, 655, 89, 23);
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("Refresh");
		button_5.setBounds(765, 655, 89, 23);
		getContentPane().add(button_5);
		
		JButton button = new JButton("Delete");
		button.setBounds(1161, 655, 89, 23);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				for (int i = 0; i < table.getRowCount(); i++) {
	                Boolean chked = Boolean.valueOf(table.getValueAt(i, 0)
	                        .toString());
	                temp_id = table.getValueAt(i, 1).toString();
	                if (chked) 
	                {
	                	try
	            		{
	            			Connection connection=com.dao.Connect.DBConnector();
	            			String query3="select *from accounts where ID="+temp_id;
	        				PreparedStatement pst=connection.prepareStatement(query3);
	        				ResultSet rs=pst.executeQuery();
	        				while(rs.next())
	        				{
	        					name=rs.getString("Name");
	        					adds=rs.getString("Address");
	        				}
	        				int id=Integer.parseInt(temp_id);
	        				com.utils.Check.get_data("delete");
	        				com.utils.Check.delete(id);
	        				com.utils.Check.main(null);
	            		}
	                	catch(Exception f)
	                	{
	                		
	                	}
	                }
				}
			
			
			}
		});
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				main(null);
			}
		});
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
	                Boolean chked = Boolean.valueOf(table.getValueAt(i, 0)
	                        .toString());
	                temp_id = table.getValueAt(i, 1).toString();
	                if (chked) 
	                {
	                	try
	            		{
	            			Connection connection=com.dao.Connect.DBConnector();
	            			String query3="select *from accounts where ID="+temp_id;
	        				PreparedStatement pst=connection.prepareStatement(query3);
	        				ResultSet rs=pst.executeQuery();
	        				while(rs.next())
	        				{
	        					name=rs.getString("Name");
	        					adds=rs.getString("Address");
	        				}
	        				int id=Integer.parseInt(temp_id);
	        				com.service.Credit.get_id(id, name, adds);
	        				com.service.Credit.main(null);
	            		}
	            		
	            		catch(Exception f)
	            		{
	            			
	            		}
	                }	
				
			}
			
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
	                Boolean chked = Boolean.valueOf(table.getValueAt(i, 0)
	                        .toString());
	                temp_id = table.getValueAt(i, 1).toString();
	                if (chked) 
	                {
	                	
	                		try
	                		{
	                			Connection connection=com.dao.Connect.DBConnector();
	                			String query2="select *from accounts where ID="+temp_id;
	            				PreparedStatement pst=connection.prepareStatement(query2);
	            				ResultSet rs=pst.executeQuery();
	            				while(rs.next())
	            				{
	            					name=rs.getString("Name");
	            					adds=rs.getString("Address");
	            				}
	                		}
	                		
	                		catch(Exception f)
	                		{
	                			
	                		}
	                		int id=Integer.parseInt(temp_id);
	                		com.service.Ledger.get_id(id,name,adds);
	                		com.service.Ledger.main(null);
	                	
	                } 
	            }
				
	        
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			for (int i = 0; i < table.getRowCount(); i++) {
                Boolean chked = Boolean.valueOf(table.getValueAt(i, 0)
                        .toString());
                temp_id = table.getValueAt(i, 1).toString();
                if (chked) 
                {
                	try
            		{
            			Connection connection=com.dao.Connect.DBConnector();
            			String query3="select *from accounts where ID="+temp_id;
        				PreparedStatement pst=connection.prepareStatement(query3);
        				ResultSet rs=pst.executeQuery();
        				while(rs.next())
        				{
        					name=rs.getString("Name");
        					adds=rs.getString("Address");
        				}
        				int id=Integer.parseInt(temp_id);
        				com.service.Debit.get_id(id, name, adds);
        				com.service.Debit.main(null);
            		}
            		
            		catch(Exception f)
            		{
            			
            		}
                }	
			
		}
		
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < table.getRowCount(); i++) {
	                Boolean chked = Boolean.valueOf(table.getValueAt(i, 0)
	                        .toString());
	                temp_id = table.getValueAt(i, 1).toString();
	                if (chked) 
	                {
	                	try
	            		{
	            			Connection connection=com.dao.Connect.DBConnector();
	            			String query3="select *from accounts where ID="+temp_id;
	        				PreparedStatement pst=connection.prepareStatement(query3);
	        				ResultSet rs=pst.executeQuery();
	        				while(rs.next())
	        				{
	        					name=rs.getString("Name");
	        					adds=rs.getString("Address");
	        				}
	        				int id=Integer.parseInt(temp_id);
	        				com.service.Update.get_id(id, name, adds);
	        				com.service.Update.main(null);
	            		}
	                	catch(Exception f)
	                	{
	                		
	                	}
	                }
				}
			
			}
		});
	}
}
