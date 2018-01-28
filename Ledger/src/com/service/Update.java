package com.service;

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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Update extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField address;
	private JTextField mobile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Update dialog = new Update();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static int id;
	static String name1;
	static String adds1;
	static void get_id(int temp,String temp_name,String temp_adds)
	{
		id=temp;
		name1=temp_name;
		adds1=temp_adds;
	}
	/**
	 * Create the dialog.
	 */
	public Update() {
		setBounds(500, 200, 398, 191);
		setTitle(Message.ACCOUNT_NAME+name1+Message.ACCOUNT_ADDRESS+adds1);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name      -");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblName.setBounds(10, 11, 101, 27);
			contentPanel.add(lblName);
		}
		{
			name = new JTextField("");
			name.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					{
						dispose();
					}
				}
			});
			name.setFont(new Font("Tahoma", Font.PLAIN, 15));
			name.setColumns(10);
			name.setBounds(121, 11, 161, 27);
			contentPanel.add(name);
		}
		{
			JLabel lblAddress = new JLabel("Address   -");
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAddress.setBounds(10, 49, 101, 27);
			contentPanel.add(lblAddress);
		}
		{
			JLabel lblMobile = new JLabel("Mobile     -");
			lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblMobile.setBounds(10, 85, 101, 27);
			contentPanel.add(lblMobile);
		}
		{
			address = new JTextField();
			address.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					{
						dispose();
					}
				}
			});
			address.setFont(new Font("Tahoma", Font.PLAIN, 15));
			address.setColumns(10);
			address.setBounds(121, 49, 161, 27);
			contentPanel.add(address);
		}
		{
			mobile = new JTextField();
			mobile.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					{
						dispose();
					}
				}
			});
			mobile.setFont(new Font("Tahoma", Font.PLAIN, 15));
			mobile.setColumns(10);
			mobile.setBounds(121, 91, 161, 27);
			contentPanel.add(mobile);
		}
		{
			JButton button1 = new JButton("Update");
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try
				        {
				            Connection connection = com.dao.Connect.DBConnector();
				           String query="update accounts set Name=? where ID=?";
				            PreparedStatement pst=connection.prepareStatement(query);
				            pst.setString(1, name.getText());
				            pst.setString(2, String.valueOf(id));
				            if(name.getText().equals(""))
				            {
				            	JOptionPane.showMessageDialog(null,Message.EMPTY_STRING);
				            }
				            else
				            {
					            int a=pst.executeUpdate();    
					            if(a==1)
					            JOptionPane.showMessageDialog(null,Message.UPDATED_SUCCESSFULLY);
				            }
				        }
				        catch(Exception g)
				        {
				            JOptionPane.showMessageDialog(null, g);
				        }
					 dispose();
				}
			});
			button1.setBounds(292, 11, 80, 27);
			contentPanel.add(button1);
		}
		{
			JButton button2 = new JButton("Update");
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try
				        {
						 	Connection connection = com.dao.Connect.DBConnector();
						 	String query="update accounts set Address=? where ID=?";
				            PreparedStatement pst=connection.prepareStatement(query);
				            pst.setString(1, address.getText());
				            pst.setString(2, String.valueOf(id));
				            if(address.getText().equals(""))
				            {
				            	JOptionPane.showMessageDialog(null,Message.EMPTY_STRING);
				            }
				            else
				            {
					            int a=pst.executeUpdate();    
					            if(a==1)
					            JOptionPane.showMessageDialog(null,Message.UPDATED_SUCCESSFULLY);
				            }
				        }
				        catch(Exception g)
				        {
				            JOptionPane.showMessageDialog(null, g);
				        }
					 dispose();
				}
			});
			button2.setBounds(292, 49, 80, 27);
			contentPanel.add(button2);
		}
		{
			JButton button3 = new JButton("Update");
			button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try
			        {
					 	Connection connection = com.dao.Connect.DBConnector();
					 	String query="update accounts set Mobile=? where ID=?";
			            PreparedStatement pst=connection.prepareStatement(query);
			            pst.setString(1, mobile.getText());
			            pst.setString(2, String.valueOf(id));
			            if(mobile.getText().equals(""))
			            {
			            	JOptionPane.showMessageDialog(null,Message.EMPTY_STRING);
			            }
			            else
			            {
				            int a=pst.executeUpdate();    
				            if(a==1)
				            JOptionPane.showMessageDialog(null,Message.UPDATED_SUCCESSFULLY);
			            }
			        }
			        catch(Exception g)
			        {
			            JOptionPane.showMessageDialog(null, g);
			        }
					 dispose();
				}
			});
			button3.setBounds(292, 90, 80, 27);
			contentPanel.add(button3);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Update.class.getResource("/com/image/background.jpg")));
			lblNewLabel.setBounds(0, 0, 382, 152);
			contentPanel.add(lblNewLabel);
		}
	}

	
}
