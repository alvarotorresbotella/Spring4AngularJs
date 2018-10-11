package com.atb.service;

import java.util.ArrayList;



import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.atb.dao.UserDao;
import com.atb.dao.UserDaoImpl;
import com.atb.model.User;


 
	
	@Service("userService")
	public class UserServiceImpl implements UserService{
	     
	    private static final AtomicLong counter = new AtomicLong();
	     
	    private static List<User> users;
	    
	    private UserDao userDao;
	    private User user;
	     
	    static{
	        //users= populateDummyUsers();
	        users=loadUsers();
	    }
	 
	    public List<User> findAllUsers() {
	    	return loadUsers();
	    }
	     
	    public User findById(long id) {
	        for(User user : loadUsers()){
	            if(user.getId() == id){
	                return user;
	            }
	        }
	        return null;
	    }
	     
	    public User findByName(String name) {
	        for(User user : loadUsers()){
	            if(user.getUsername().equalsIgnoreCase(name)){
	                return user;
	            }
	        }
	        return null;
	    }
	     
	    public void saveUser(User user) {
	    	
	        
	        userDao=new UserDaoImpl();
	        
	    	userDao.getConnection();
	    	user.setId(userDao.getIdMaxUser()+1);
	    	userDao.insertUser(user);
	    	userDao.closeConnection();
	    }
	 
	    public void updateUser(User user) {
	    	userDao=new UserDaoImpl();
	    	userDao.getConnection();
	    	userDao.updateUser(user);
	    	userDao.closeConnection();
	    }
	 
	    public void deleteUserById(long id) {
	    	
	    	userDao=new UserDaoImpl();
	    	userDao.getConnection();
	    	userDao.deleteUser(id);
	    	userDao.closeConnection();
	       
	    }
	 
	    public boolean isUserExist(User user) {
	        return findByName(user.getUsername())!=null;
	    }
	     
	    public void deleteAllUsers(){
	        users.clear();
	    }
	 
	   /* private static List<User> populateDummyUsers(){
	      List<User> users = new ArrayList<User>();
	      users=new UserDaoImpl().selectAllUsers();
	        users.add(new User(counter.incrementAndGet(),"alvaro", "NY", "sam@abc.com"));
	        users.add(new User(counter.incrementAndGet(),"mawita", "ALBAMA", "tomy@abc.com"));
	        users.add(new User(counter.incrementAndGet(),"Kelly", "NEBRASKA", "kelly@abc.com"));
	        return users;
	    }*/
	    
	    private static List<User> loadUsers()
	    {
	    	List<User> users = new ArrayList<User>();
	    	UserDao userDao=new UserDaoImpl();
	    	userDao.getConnection();
	    	users=userDao.selectAllUsers();
	    	userDao.closeConnection();
	    	return users;
	    }

}
