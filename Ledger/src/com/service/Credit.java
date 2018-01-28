package com.service;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.constants.Message;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Credit extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Credit dialog = new Credit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static int id;
	static String name1;
	static String adds1;
	@SuppressWarnings("unused")
	private JTextField damount;
	private JTextField particulars;
	private JTextField price;
	private JTextField quantity;
		static void get_id(int temp,String temp_name,String temp_adds)
		{
			id=temp;
			name1=temp_name;
			adds1=temp_adds;
		}
	/**
	 * Create the dialog.
	 */
	public Credit() {
		setTitle(Message.ACCOUNT_NAME+name1+Message.ACCOUNT_ADDRESS+adds1);
		setBounds(500, 200, 310, 214);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblParticulars = new JLabel("Particulars -");
		lblParticulars.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblParticulars.setBounds(10, 11, 101, 27);
		contentPanel.add(lblParticulars);
		
		JLabel lblPrice = new JLabel("Price        -");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrice.setBounds(10, 51, 101, 27);
		contentPanel.add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity   -");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantity.setBounds(10, 89, 101, 27);
		contentPanel.add(lblQuantity);
		
		particulars = new JTextField();
		particulars.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		particulars.setFont(new Font("Tahoma", Font.PLAIN, 15));
		particulars.setBounds(121, 11, 161, 27);
		contentPanel.add(particulars);
		particulars.setColumns(10);
		
		price = new JTextField();
		price.setFont(new Font("Tahoma", Font.PLAIN, 15));
		price.setColumns(10);
		price.setBounds(121, 51, 161, 27);
		contentPanel.add(price);
		
		quantity = new JTextField();
		quantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
						com.utils.Check.get_data("credit");
						com.utils.Check.credit(particulars.getText(),price.getText(),quantity.getText(),id);
						com.utils.Check.main(null);
						dispose();
				}
				else if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		quantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quantity.setColumns(10);
		quantity.setBounds(121, 89, 161, 27);
		contentPanel.add(quantity);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(121, 141, 65, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					com.utils.Check.get_data("credit");
					com.utils.Check.credit(particulars.getText(),price.getText(),quantity.getText(),id);
					com.utils.Check.main(null);
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(196, 141, 86, 23);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Credit.class.getResource("/com/image/background.jpg")));
		lblNewLabel.setBounds(0, 0, 294, 175);
		contentPanel.add(lblNewLabel);
	}
}
