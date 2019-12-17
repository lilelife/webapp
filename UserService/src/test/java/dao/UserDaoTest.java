package dao;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lile.common.mybits.model.User;
import com.lile.common.mybits.persistence.UserMapper;

import junit.framework.Assert;

public class UserDaoTest {
	 @Test
	    public void findUserById() {
	        SqlSession sqlSession = getSessionFactory().openSession();  
	        UserMapper userM = sqlSession.getMapper(UserMapper.class);
	        User u= userM.selectByPrimaryKey(2);
	        Assert.assertNotNull("û�ҵ�����", u);
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
