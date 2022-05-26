package com.gyp.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gyp.chatapp.dto.UserDTO;
import com.gyp.chatapp.util.Encrytion;

/*
 * User CRUD
 */


public class UserDAO {
	
	public boolean isLogin(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT userid FROM users WHERE userid=? and password=?";
		try {
			con=CommonDAO.createConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,  userDTO.getUserid());
			String encr=Encrytion.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,  encr);
			rs=pstmt.executeQuery();
			return rs.next();
			
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	
	public boolean add(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection=null;
		PreparedStatement stmt=null;
		final String sql="INSERT INTO users (userid, password, email, phno, city) values(?,?,?,?,?);";
		try {
		//1. connection
		connection=CommonDAO.createConnection();
		stmt=connection.prepareStatement(sql);
		//2. query
		//String sql="INSERT INTO users (userid, password) values('"+userDTO.getUserid()+"','"+Encrytion.passwordEncrypt(new String(userDTO.getPassword()))+"')";
		stmt.setString(1, userDTO.getUserid());
		String pass=Encrytion.passwordEncrypt(new String(userDTO.getPassword()));
		stmt.setString(2, pass);
		stmt.setString(3, userDTO.getEmail());
		stmt.setString(4, userDTO.getPhno());
		stmt.setString(5, userDTO.getCity());
		stmt.execute();
		}
		finally {
		//3. resource cleaning
			if(stmt!=null)
				stmt.close();
			if(connection!=null)
				connection.close();
		}
		return true;
	}
	
	public boolean updatePassword(UserDTO userDTO, char[]newPassword) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection=null;
		PreparedStatement stmt=null;
		final String sql="UPDATE users SET password=? WHERE userid=?;";
		try{
			connection=CommonDAO.createConnection();
			stmt=connection.prepareStatement(sql);
			String pass=Encrytion.passwordEncrypt(new String(newPassword));
			stmt.setString(1,pass);
			stmt.setString(2, userDTO.getUserid());
			stmt.execute();
		}
		finally {
			if(stmt!=null)
				stmt.close();
			if(connection!=null)
				connection.close();
		}
		return true;
	}
}
