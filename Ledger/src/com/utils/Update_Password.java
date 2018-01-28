package com.utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;

import com.constants.Message;

import javax.swing.event.AncestorEvent;
import javax.swing.ImageIcon;

public class Update_Password extends JDialog {
	private JTextField user;
	private JTextField pass;
	private JTextField ques;
	private JTextField ans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Update_Password dialog = new Update_Password();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Update_Password() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Update_Password.class.getResource("/com/image/logo.png")));
		setTitle("Change PassWord ");
		setBounds(450, 150, 430, 435);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ledger");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Old English Text MT", Font.BOLD, 36));
		label.setBounds(10, 11, 394, 47);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("New UserName :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 63, 111, 26);
		getContentPane().add(label_1);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(126, 63, 278, 26);
		getContentPane().add(user);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(126, 104, 278, 26);
		getContentPane().add(pass);
		
		JLabel label_2 = new JLabel("New PassWord :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 100, 111, 26);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Security Question :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(10, 158, 137, 26);
		getContentPane().add(label_3);
		
		ques = new JTextField();
		ques.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				 try
			        {
			            Connection connection=com.dao.Connect.DBConnector();
			            String query="select question from userinfo where S_NO=1";
			            PreparedStatement pst=connection.prepareStatement(query);
			            ResultSet rs=pst.executeQuery();

			            while(rs.next())
			            {
			                ques.setText(rs.getString("question"));
			            }
			        }
			        catch(Exception e)
			        {
			            JOptionPane.showMessageDialog(null, e);
			        }
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		ques.setEditable(false);
		ques.setColumns(10);
		ques.setBounds(10, 195, 394, 26);
		getContentPane().add(ques);
		
		JLabel label_4 = new JLabel("Answer :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 235, 158, 26);
		getContentPane().add(label_4);
		
		ans = new JTextField();
		ans.setColumns(10);
		ans.setBounds(10, 272, 394, 26);
		getContentPane().add(ans);
		
		JButton button = new JButton("Update User");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try
			        {
			            if ( user.getText().equals("") || pass.getText().equals(""))
			            {
			                JOptionPane.showMessageDialog(null, "UserName or PassWord can't be blank");
			            }
			            else
			            {
			                Connection connection=com.dao.Connect.DBConnector();
			                String query2="select answer from userinfo where S_NO=1";
			                PreparedStatement pst2=connection.prepareStatement(query2);
			                ResultSet rs2=pst2.executeQuery();
			                String ans2="";
			                while(rs2.next())
			                {
			                    ans2=rs2.getString("answer");
			                }
			                if(ans.getText().equals(ans2))
			                {
			                    String query3="update userinfo set username ='"+user.getText()+"' , password='"+pass.getText()+"' where S_NO=1";
			                    PreparedStatement pst3=connection.prepareStatement(query3);
			                    pst3.executeUpdate();
			                    JOptionPane.showMessageDialog(null, Message.USER_UPDATE);
			                    dispose();
			                }
			                else
			                {
			                    JOptionPane.showMessageDialog(null, Message.USER_UPDATE_FAIL);
			                }
			            }
			        }
			        catch(Exception f)
			        {
			            JOptionPane.showMessageDialog(null, f);
			        }

			}
		});
		button.setBounds(143, 350, 120, 35);
		getContentPane().add(button);
		
		JButton btnNewButton = new JButton("Update Question");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.utils.Question_Update.main(null);
			}
		});
		btnNewButton.setBounds(273, 350, 131, 35);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Update_Password.class.getResource("/com/image/background.jpg")));
		lblNewLabel.setBounds(0, 0, 414, 396);
		getContentPane().add(lblNewLabel);
	}
}
