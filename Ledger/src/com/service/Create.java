package com.service;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Create extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField address;
	private JTextField mobile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Create dialog = new Create();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public Create() {
		setBounds(450, 250, 399, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEnterName = new JLabel("Enter Name :");
			lblEnterName.setBounds(10, 11, 115, 34);
			lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(lblEnterName);
		}
		{
			JLabel lblAddress = new JLabel("Address      :");
			lblAddress.setBounds(10, 51, 115, 34);
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(lblAddress);
		}
		{
			JLabel lblMobile = new JLabel("Mobile        :");
			lblMobile.setBounds(10, 86, 115, 34);
			lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(lblMobile);
		}
		
		name = new JTextField();
		name.setBounds(105, 14, 265, 31);
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		name.setFont(new Font("Tahoma", Font.PLAIN, 22));
		name.setColumns(10);
		contentPanel.add(name);
		
		address = new JTextField();
		address.setBounds(105, 50, 265, 31);
		address.setFont(new Font("Tahoma", Font.PLAIN, 22));
		address.setColumns(10);
		contentPanel.add(address);
		
		mobile = new JTextField();
		mobile.setBounds(105, 86, 265, 31);
		mobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
				else if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					com.utils.Check.get_data("create");
					com.utils.Check.create(name.getText(),address.getText(),mobile.getText());
					com.utils.Check.main(null);
					dispose();
				}
			}
		});
		mobile.setFont(new Font("Tahoma", Font.PLAIN, 22));
		mobile.setColumns(10);
		contentPanel.add(mobile);
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(294, 173, 78, 23);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(219, 173, 65, 23);
			contentPanel.add(okButton);
			okButton.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					com.utils.Check.get_data("create");
					com.utils.Check.create(name.getText(),address.getText(),mobile.getText());
					com.utils.Check.main(null);
					dispose();
				}
			});
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					com.utils.Check.get_data("create");
					com.utils.Check.create(name.getText(),address.getText(),mobile.getText());
					com.utils.Check.main(null);
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(Create.class.getResource("/com/image/background.jpg")));
			lblNewLabel.setBounds(0, 0, 383, 207);
			contentPanel.add(lblNewLabel);
		}
	}
}
