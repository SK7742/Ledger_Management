package com.service;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.constants.Message;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	int count=0,flag=0;
	String acc = null,name = null,adds = null,mob = null,luo = null,bal = null, temp_id;
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/image/logo.png")));
		frame.setBounds(-5, 2, 1375, 728);
		frame.setTitle("Ledger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu options = new JMenu("Options");
		options.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(options);
		
		JMenuItem mntmCreateNewAccount = new JMenuItem("Create New Account");
		mntmCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.service.Create.main(null);
			}
		});
		mntmCreateNewAccount.setHorizontalAlignment(SwingConstants.LEFT);
		options.add(mntmCreateNewAccount);
		
		JMenuItem mntmSearchByName = new JMenuItem("Search By Name");
		mntmSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				com.utils.Search_Data.get_search_data("Enter Name :");
				com.utils.Search_Data.main(null);
			}
		});
		options.add(mntmSearchByName);
		
		JMenuItem mntmSearchByMobile = new JMenuItem("Search By Mobile");
		mntmSearchByMobile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				com.utils.Search_Data.get_search_data("Enter  Mobile No:");
				com.utils.Search_Data.main(null);
			}
		});
		options.add(mntmSearchByMobile);
		
		JMenuItem mntmSearchAccount = new JMenuItem("Search By Address");
		mntmSearchAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				com.utils.Search_Data.get_search_data("Enter  Address :");
				com.utils.Search_Data.main(null);
			}
		});
		options.add(mntmSearchAccount);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuBar.add(mnHelp);
		
		JMenuItem mntmShortcuts = new JMenuItem("Shortcuts");
		mntmShortcuts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				com.utils.Help.main(null);
			}
		});
		mntmShortcuts.setHorizontalAlignment(SwingConstants.CENTER);
		mnHelp.add(mntmShortcuts);
		

        // ScrollPane for Table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 1342, 612);
        frame.getContentPane().add(scrollPane);

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
        		else if(e.getKeyCode()==KeyEvent.VK_R)
    			{
    				frame.dispose();
    				main(null);
    			}
        		else if(e.getKeyCode()==KeyEvent.VK_L)
    			{

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
                        			JOptionPane.showMessageDialog(null, f);
                        		}
                        		int id=Integer.parseInt(temp_id);
                        		com.service.Ledger.get_id(id,name,adds);
                        		com.service.Ledger.main(null);
                        	
                        } 
                    }
    			}
        		else if(e.getKeyCode()==KeyEvent.VK_D)
    			{

					
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
    	            			JOptionPane.showMessageDialog(null, f);
    	            		}
    	                }	
    				
    			}
    			
    			}
        		else if(e.getKeyCode()==KeyEvent.VK_C)
    			{

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
    	            			JOptionPane.showMessageDialog(null, f);
    	            		}
    	                }	
    				
    			}
    			
    			}
        		else if(e.getKeyCode()==KeyEvent.VK_U)
    			{
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
        	                		JOptionPane.showMessageDialog(null, f);

        	                	}
        	                }
        				}
        			}
        	}
        });
        scrollPane.setViewportView(table);
        
        	try
        	{
        		Connection connection=com.dao.Connect.DBConnector();
			
        		String query="select *from accounts ";
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
		frame.getContentPane().setLayout(null);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
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
		delete.setBounds(1263, 634, 89, 23);
		frame.getContentPane().add(delete);
		
		
		
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
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
		update.setBounds(1164, 634, 89, 23);
		frame.getContentPane().add(update);
		
		JButton ledger = new JButton("Ledger");
		ledger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
                		
                		catch(Exception e)
                		{
                			
                		}
                		int id=Integer.parseInt(temp_id);
                		com.service.Ledger.get_id(id,name,adds);
                		com.service.Ledger.main(null);
                	
                } 
            }
			
        }
		});
		
		JButton debit = new JButton("Debit");
		debit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
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
	            		
	            		catch(Exception e)
	            		{
	            			
	            		}
	                }	
				
			}
			}
		});
		debit.setBounds(966, 634, 89, 23);
		frame.getContentPane().add(debit);
		ledger.setBounds(867, 634, 89, 23);
		frame.getContentPane().add(ledger);
		
		JButton credit = new JButton("Credit");
		credit.addActionListener(new ActionListener() {
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
		credit.setBounds(1065, 634, 89, 23);
		frame.getContentPane().add(credit);
		
		JButton btnRefresh = new JButton("Refresh");
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				main(null);
			}
		});
		btnRefresh.setBounds(768, 634, 89, 23);
		frame.getContentPane().add(btnRefresh);
	}
}
