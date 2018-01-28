package com.service;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.constants.Message;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JLabel;

public class Ledger extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Ledger dialog = new Ledger();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
ResultSet rs=null;
static int id;
static String name1;
static String adds1;
final JTable table = new JTable();
	static void get_id(int temp,String temp_name,String temp_adds)
	{
		id=temp;
		name1=temp_name;
		adds1=temp_adds;
		
	}
	/**
	 * Create the dialog.
	 */
	public Ledger() {
		setTitle(Message.ACCOUNT_NAME+name1+Message.ACCOUNT_ADDRESS+adds1);
		setBounds(-5, 2, 1375, 728);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 656, 1359, 33);
			buttonPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) 
				{
					if(e.getKeyCode()==KeyEvent.VK_F5)
					{
					String headerFormat="Hello";
        			String footerFormat="Footer";
        			try {
						table.print(PrintMode.FIT_WIDTH);
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
			});
			getContentPane().setLayout(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton print = new JButton("Print");
			
				print.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							table.print();
						} catch (PrinterException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				print.setActionCommand("Cancel");
				buttonPane.add(print);
			}
			 // ScrollPane for Table
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(0, 0, 1359, 656);
	        getContentPane().add(scrollPane);
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
	        		else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
	        		{
	        			dispose();
	        		}
	        	}
	        });
	   

	        // Table
	       // final JTable table = new JTable();
	        scrollPane.setViewportView(table);
	        
	        String serial,particulars,price,quantity,date,debit,credit,balance;
	        try
        	{
				Connection connection=com.dao.Connect.DBConnector();
			
        		String query="select *from ledger_for_id_"+id;
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
				
					 // Model for Table
		            DefaultTableModel model = new DefaultTableModel() {

		                public Class<?> getColumnClass(int column) {
		                    switch (column) {
		                    case 0:
		                        return String.class;
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
		                    case 7:
		                        return String.class;
		                    default:
		                        return String.class;
		                    }
		                }
		            };
		            table.setModel(model);

		            model.addColumn("Serial No.");
		            model.addColumn("Particulars ");
		            model.addColumn("Price");
		            model.addColumn("Quantity");
		            model.addColumn("Date");
		            model.addColumn("Debit");
		            model.addColumn("Credit");
		            model.addColumn("Balance");
		            
		            
		           
						// Data Row
		            	while(rs.next())
		            	{
		            		serial=rs.getString("S_NO");
		            		particulars=rs.getString("Particulars");
		            		price=rs.getString("Price");
		            		quantity=rs.getString("Quantity");
							date=rs.getString("Date");
							debit=rs.getString("Debit");
							credit=rs.getString("Credit");
							balance	=rs.getString("Balance");
							
			            	for (int i = 0; i <1; i++) {
			                model.addRow(new Object[]{serial,particulars,price,quantity,date,debit,credit,balance});
		            	}
		            }
				}
        	
			catch(Exception e)
	        {
	        		
	        }
		}
	}

}
