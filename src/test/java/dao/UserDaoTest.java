package dao;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.atb.dao.UserDao;
import com.atb.dao.UserDaoImpl;
import com.atb.model.User;

public class UserDaoTest {
	
	private UserDao userDao;

	@Before
	public void load()
	{
	userDao=new UserDaoImpl();
	userDao.getConnection();
	}
	
	@Test
	public void numeroMaxPalabras()
	{
		assertEquals(userDao.getIdMaxUser(),37);		
	}

}
