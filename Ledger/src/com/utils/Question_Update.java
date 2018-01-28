package com.utils;

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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Toolkit;

public class Question_Update extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField ques;
	private JTextField ans;
	private JTextField nques;
	private JTextField nans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Question_Update dialog = new Question_Update();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Question_Update() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Question_Update.class.getResource("/com/image/logo.png")));
		setBounds(450, 150, 430, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Security Question :");
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			label.setBounds(10, 11, 135, 26);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Answer :");
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			label.setBounds(10, 93, 135, 26);
			contentPanel.add(label);
		}
		{
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
			ques.setBounds(10, 48, 391, 26);
			contentPanel.add(ques);
		}
		{
			ans = new JTextField();
			ans.setColumns(10);
			ans.setBounds(10, 130, 391, 26);
			contentPanel.add(ans);
		}
		{
			JLabel lblNewSecurityQuestion = new JLabel("New Security Question :");
			lblNewSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewSecurityQuestion.setBounds(10, 171, 211, 26);
			contentPanel.add(lblNewSecurityQuestion);
		}
		{
			nques = new JTextField();
			nques.setColumns(10);
			nques.setBounds(10, 206, 391, 26);
			contentPanel.add(nques);
		}
		{
			JLabel lblAnswer = new JLabel("Answer :");
			lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblAnswer.setBounds(10, 243, 211, 26);
			contentPanel.add(lblAnswer);
		}
		{
			nans = new JTextField();
			nans.setColumns(10);
			nans.setBounds(10, 273, 391, 26);
			contentPanel.add(nans);
		}
		{
			JButton btnNewButton = new JButton("Update Question");
			btnNewButton.setIcon(new ImageIcon(Question_Update.class.getResource("/com/image/ok.png")));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try
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
				                if (nques.getText().equals("") || nans.getText().equals(""))
				                {
				                     JOptionPane.showMessageDialog(null, Message.EMPTY_STRING);
				                } 
				                else
				                {
				                    String query3="update userinfo set question='"+nques.getText()+"' where S_NO=1";
				                    PreparedStatement pst3=connection.prepareStatement(query3);
				                    pst3.executeUpdate();

				                    String query4="update userinfo set answer='"+nans.getText()+"' where S_NO=1";
				                    PreparedStatement pst4=connection.prepareStatement(query4);
				                    pst4.executeUpdate();
				                    JOptionPane.showMessageDialog(null, Message.QUESTION_UPDATE);
				                    dispose();
				                    
				                }
				            }
				            else
				           JOptionPane.showMessageDialog(null, Message.QUESTION_UPDATE_FAIL);
				                
				        }
				          catch(Exception f)
				         {
				             JOptionPane.showMessageDialog(null, f);
				         }
				    }
			});
				        
				   
			btnNewButton.setBounds(245, 353, 159, 32);
			contentPanel.add(btnNewButton);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Question_Update.class.getResource("/com/image/background.jpg")));
			lblNewLabel.setBounds(0, 0, 414, 396);
			contentPanel.add(lblNewLabel);
		}
	}

}
