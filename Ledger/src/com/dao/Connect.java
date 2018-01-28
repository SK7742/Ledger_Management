package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import com.constants.Message;


public class Connect {

static String jdbc_driver="com.mysql.jdbc.Driver";


public static Connection DBConnector()
{
	try
	{
		Class.forName(jdbc_driver);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ledger","root","SHIVAM");
		if(con!=null)
		return con;
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, Message.CONNECTION_FAIL_MSG);
	}
	return null;
}
public static void main(String agrs[])
		{
			Connect.DBConnector();
		}
}
