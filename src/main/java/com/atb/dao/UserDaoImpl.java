package com.atb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.atb.model.User;

public class UserDaoImpl implements UserDao{
	
	private List<User> listUser;

	 Connection connection = null;
	 
	    public Connection getConnection(){
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            if(connection == null)
	                connection = DriverManager.getConnection("jdbc:mysql://localhost/dbangular1?user=root&password=admin");
	 
	        } catch (ClassNotFoundException e) {
	 
	            e.printStackTrace();
	             
	        } catch (SQLException e) {
	             
	            e.printStackTrace();
	             
	        }
	        return connection;
	    }
	
	    public void closeConnection(){
	        try {
	              if (connection != null) {
	                  connection.close();
	              }
	            } catch (Exception e) { 
	                //do nothing
	            }
	    }
	
	
	
	
	@Override
	public void insertUser(User user) {
		 try {
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (id ,username,address,email) VALUES (?,?,?,?)");
	            preparedStatement.setLong(1,user.getId());
	            preparedStatement.setString(2,user.getUsername());
	            preparedStatement.setString(3,user.getAddress());
	            preparedStatement.setString(4,user.getEmail());
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

	@Override
	public List<User> selectAllUsers() {
		 listUser = new ArrayList<User>();
         try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM user order by username asc");
                 
                User user = null;
                while(rs.next()){
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setAddress(rs.getString("address"));
                    user.setEmail(rs.getString("email"));
                    listUser.add(user);
                }
                rs.close();
                stmt.close();
                 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(listUser);
            return listUser;
	}

	@Override
	public void deleteUser(long id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id=?");
	        preparedStatement.setLong(1,id);
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser(User user) {
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user set username=?,address=?,email=? WHERE id=?");
	        preparedStatement.setString(1,user.getUsername());
	        preparedStatement.setString(2,user.getAddress());
	        preparedStatement.setString(3,user.getEmail());
	        preparedStatement.setLong(4,user.getId());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

	@Override
	public long getIdMaxUser() {
		 long id = 1;
         try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT MAX(id) id FROM user");
                if(rs.next()){
                    id=rs.getLong("id");
                }
                rs.close();
                stmt.close();
                 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(listUser);
            return id;
	}

}
