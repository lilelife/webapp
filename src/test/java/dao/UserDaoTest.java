package dao;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import entity.User;
import junit.framework.Assert;

public class UserDaoTest {
	 @Test
	    public void findUserById() {
	        SqlSession sqlSession = getSessionFactory().openSession();  
	        UserDao userMapper = sqlSession.getMapper(UserDao.class);  
	        User user = userMapper.findUserById(2);  
	        Assert.assertNotNull("û�ҵ�����", user);
	    }
	    
	    //Mybatis ͨ��SqlSessionFactory��ȡSqlSession, Ȼ�����ͨ��SqlSession�����ݿ���н���
	    private static SqlSessionFactory getSessionFactory() {  
	        SqlSessionFactory sessionFactory = null;  
	        String resource = "configuration.xml";  
	        try {  
	            sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
	                    .getResourceAsReader(resource));
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return sessionFactory;  
	    }  
	    
}
