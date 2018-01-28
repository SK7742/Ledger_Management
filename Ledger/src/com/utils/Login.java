package com.utils;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.constants.Message;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ItemEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Login {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/image/logo.png")));
		frame.setBounds(400, 150, 483, 326);
		frame.setTitle(Message.MAIN_FRAME_TITLE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("UserName :");
		label_1.setBounds(188, 62, 95, 29);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(label_1);
		
		JLabel lblPassword = new JLabel("PassWord :");
		lblPassword.setBounds(188, 103, 95, 29);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(281, 62, 162, 29);
		username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JCheckBox show = new JCheckBox("Show Password");
		show.setForeground(new Color(0, 0, 0));
		show.setBackground(new Color(102, 204, 204));
		show.setBounds(281, 145, 124, 23);
		show.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					password.setEchoChar((char)0);
				}
				else
					password.setEchoChar('•');
			}
		});
		frame.getContentPane().add(show);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(229, 189, 102, 41);
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
						if (e.getKeyCode()==KeyEvent.VK_ENTER){
							try
							{
								int count=0;
								String query="select *from userinfo where username=? and password=?";
								Connection connection=com.dao.Connect.DBConnector();
								PreparedStatement pst=connection.prepareStatement(query);
					            pst.setString(1, username.getText());
					            pst.setString(2, password.getText());
					            ResultSet rs=pst.executeQuery();
					            while(rs.next())
					            {
					                count=count+1;
					            }
					            if(count==1)
					            {
					            	JOptionPane.showMessageDialog(null, "Welcome");
					            }
					           
					            else
					            {
					                JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
					            }
					            pst.close();
							}
							catch(Exception x)
							{
								JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
							}
				        }
				        
					}
				});
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int count=0;
					String query="select *from userinfo where username=? and password=?";
					Connection connection=com.dao.Connect.DBConnector();
					PreparedStatement pst=connection.prepareStatement(query);
		            pst.setString(1, username.getText());
		            pst.setString(2, password.getText());
		            ResultSet rs=pst.executeQuery();
		            while(rs.next())
		            {
		                count=count+1;
		            }
		            if(count==1)
		            {
		            	com.service.Main.main(null);
		            }
		           
		            else
		            {
		                JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
		            }
		            pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/com/image/ok.png")));
		frame.getContentPane().add(btnNewButton);
		
		
		
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				try
				{
					int count=0;
					String query="select *from userinfo where username=? and password=?";
					Connection connection=com.dao.Connect.DBConnector();
					PreparedStatement pst=connection.prepareStatement(query);
		            pst.setString(1, username.getText());
		            pst.setString(2, password.getText());
		            ResultSet rs=pst.executeQuery();
		            while(rs.next())
		            {
		                count=count+1;
		            }
		            if(count==1)
		            {
		            	com.service.Main.main(null);
		            	frame.dispose();
		            }
		           
		            else
		            {
		                JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
		            }
		            pst.close();
				}
				catch(Exception f)
				{
					JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
				}
			}
			}
		});
		password.setBounds(281, 102, 162, 26);
		password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(password);
		
		JButton clr = new JButton("Clear");
		clr.setBounds(341, 189, 102, 41);
		clr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			username.setText("");
			password.setText("");
			}
		});
		clr.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(clr);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 49, 168, 179);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/com/image/login.png")));
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Reset PassWord");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.utils.Update_Password.main(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(229, 241, 214, 45);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/com/image/background.jpg")));
		lblNewLabel_1.setBounds(0, 0, 477, 297);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
