package com.utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.constants.Message;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Help extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Help dialog = new Help();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Help() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		setTitle("Help");
		setBounds(450, 200, 446, 363);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("1."+com.constants.Message.ENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(10, 11, 414, 24);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblpressEscFor = new JLabel("2."+com.constants.Message.ESC);
		lblpressEscFor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblpressEscFor.setBounds(10, 46, 414, 24);
		contentPanel.add(lblpressEscFor);
		
		JLabel label = new JLabel("3."+com.constants.Message.PRINT);
		
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(10, 81, 414, 24);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("4."+Message.NEW_ACCOUNT_SHORTCUT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(10, 116, 414, 24);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("5."+Message.REFRESH_SHORTCUT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(10, 151, 414, 24);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("6."+Message.LEDGER_SHORTCUT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 188, 414, 24);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("7."+Message.DEBIT_SHORTCUT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 224, 414, 24);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("8."+Message.CREDIT_SHORTCUT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(10, 259, 414, 24);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("9."+Message.UPDATE_SHORTCUT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 289, 414, 24);
		contentPanel.add(label_6);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Help.class.getResource("/com/image/background.jpg")));
		lblNewLabel_1.setBounds(0, 0, 430, 324);
		contentPanel.add(lblNewLabel_1);
	}
}
