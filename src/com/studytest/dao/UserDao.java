package com.studytest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.studytest.entity.User;

public class UserDao {
	private static String jdbc = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:liaozy";
	private static String name = "liaozy_test";
	private static String password = "123456";
	
	public static void main(String[] args){
		try {
			User user = new User("liaozy","123456");
			boolean b = login(user);
			if(b){
				System.out.println("success");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 加载Oracle驱动程序
	}
	
	public static Connection getConnection() throws Exception{
		Class.forName(jdbc);// 加载Oracle驱动程序
		Connection con = DriverManager.getConnection(url, name, password);// 获取连接
		return con;
	}
	
	public static boolean login(User user) throws Exception{
		Connection con = getConnection();
		String sql = "select * from t_user where username = ? and userpassword = ?";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, user.getUserName());
		pre.setString(2, user.getPassWord());
		ResultSet rs = pre.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
}
