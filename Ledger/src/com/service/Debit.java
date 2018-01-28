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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Debit extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Debit dialog = new Debit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static int id;
	static String name1;
	static String adds1;
	private JTextField damount;
		static void get_id(int temp,String temp_name,String temp_adds)
		{
			id=temp;
			name1=temp_name;
			adds1=temp_adds;
		}
	/**
	 * Create the dialog.
	 */
	public Debit() {
		setTitle(Message.ACCOUNT_NAME+name1+Message.ACCOUNT_ADDRESS+adds1);
		setBounds(500, 200, 301, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Debited Amount :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(10, 11, 214, 49);
		contentPanel.add(label);
		
		damount = new JTextField();
		damount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
						com.utils.Check.get_data("debit");
						com.utils.Check.debit(damount.getText(),id);
						com.utils.Check.main(null);
						dispose();
				}
				else if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
	
		
		damount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		damount.setBounds(10, 71, 265, 49);
		contentPanel.add(damount);
		damount.setColumns(10);
		{
			JButton okButton = new JButton("Debit");
			okButton.setBounds(117, 148, 71, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					com.utils.Check.get_data("debit");
					com.utils.Check.debit(damount.getText(),id);
					com.utils.Check.main(null);
					dispose();
				}

				private void debit() {
					// TODO Auto-generated method stub
					
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(198, 148, 77, 23);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Debit.class.getResource("/com/image/background.jpg")));
			lblNewLabel.setBounds(0, 0, 285, 182);
			contentPanel.add(lblNewLabel);
		}
	}
}
