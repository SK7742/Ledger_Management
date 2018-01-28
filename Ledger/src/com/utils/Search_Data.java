package com.utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Search_Data extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField search_element;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Search_Data dialog = new Search_Data();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static String search_type;
	public static void get_search_data(String temp_search_type)
	{
		search_type=temp_search_type;
	}

	/**
	 * Create the dialog.
	 */
	public Search_Data() {
		setTitle("Search");
		setBounds(500, 250, 302, 244);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			search_element = new JTextField();
			search_element.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						if(search_type.equals("Enter Name :"))
						{
							com.service.Search.get_search_deatails("select *from accounts where Name like '%"+search_element.getText()+"%'");
							com.service.Search.main(null);
							dispose();
						}
						else if(search_type.equals("Enter  Mobile No:"))
						{
							com.service.Search.get_search_deatails("select *from accounts where Mobile like '%"+search_element.getText()+"%'");
							com.service.Search.main(null);
							dispose();
						}
						else if(search_type.equals("Enter  Address :"))
						{
							com.service.Search.get_search_deatails("select *from accounts where Address like '%"+search_element.getText()+"%'");
							com.service.Search.main(null);
							dispose();
						}
					}
				}
			});
			search_element.setFont(new Font("Tahoma", Font.PLAIN, 22));
			search_element.setColumns(10);
			search_element.setBounds(10, 77, 265, 49);
			contentPanel.add(search_element);
		}
		{
			JLabel label = new JLabel(search_type);
			label.setFont(new Font("Tahoma", Font.PLAIN, 18));
			label.setBounds(10, 11, 214, 49);
			contentPanel.add(label);
		}
		{
			JButton btnNewButton = new JButton("Search");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(search_type.equals("Enter Name :"))
					{
						com.service.Search.get_search_deatails("select *from accounts where Name like '%"+search_element.getText()+"%'");
						com.service.Search.main(null);
						dispose();
					}
					else if(search_type.equals("Enter  Mobile No:"))
					{
						com.service.Search.get_search_deatails("select *from accounts where Mobile like '%"+search_element.getText()+"%'");
						com.service.Search.main(null);
						dispose();
					}
					else if(search_type.equals("Enter  Address :"))
					{
						com.service.Search.get_search_deatails("select *from accounts where Address like '%"+search_element.getText()+"%'");
						com.service.Search.main(null);
						dispose();
					}
					
				}
			});
			btnNewButton.setBounds(166, 149, 110, 36);
			contentPanel.add(btnNewButton);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Search_Data.class.getResource("/com/image/background.jpg")));
			lblNewLabel.setBounds(0, 0, 286, 205);
			contentPanel.add(lblNewLabel);
		}
	}

}
