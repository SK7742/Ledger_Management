package com.utils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Welcome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
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
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/com/image/logo.png")));
		frame.setBounds(200, 5, 1000, 716);
		frame.setTitle("Ledger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 995, 687);
		label.setIcon(new ImageIcon(Welcome.class.getResource("/com/image/LEDGER_1.png")));
		frame.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setIcon(new ImageIcon(Welcome.class.getResource("/com/image/ok.png")));
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					com.utils.Login.main(null);
					frame.dispose();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				com.utils.Login.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(380, 599, 188, 37);
		frame.getContentPane().add(btnNewButton);
	}

}
