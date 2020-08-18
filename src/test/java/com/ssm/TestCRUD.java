package com.ssm;

import com.ssm.dao.UserDao;
import com.ssm.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhouhai
 * @date 2020/8/18 14:29
 * @company bbaqw
 */
public class TestCRUD {
    private InputStream in;
    private UserDao userDao;
    private SqlSession session;

    //测试方法执行前执行
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    //测试方法执行后执行
    @After
    public void clear() throws Exception{
        //提交事务（否则事务回滚不会保存进数据库，但是id已经被使用）
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 查询所有User
     * @throws IOException
     */
    @Test
    public void testFindAll() throws IOException {
        List<User> list = userDao.findAll();
        for(User u : list){
            System.out.println(u);
        }
    }

    /**
     * 添加用户
     * @throws Exception
     */
    @Test
    public void testSaveUser() throws Exception{
        User u = new User();
        u.setUsername("塔睿");
        u.setSex("女");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        u.setBirthday(sdf.parse("1998-12-20"));
        u.setAddress("河北雄安新区a幢67号");
        userDao.saveUser(u);
    }

    /**
     * 更新用户
     */
    @Test
    public void testUpdUser() throws Exception{
        User u = new User();
        u.setId(54);
        u.setUsername("塔睿");
        u.setSex("男");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        u.setBirthday(sdf.parse("1994-12-20"));
        u.setAddress("河北雄安新区a幢67号");
        userDao.updateUser(u);
    }

    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser() throws Exception{
        userDao.deleteUser(54);
    }

    /**
     * 根据ID查询用户
     */
    @Test
    public void testGetUserById(){
        User u = userDao.getUserById(48);
        System.out.println(u);
    }

    /**
     * 用户名模糊查询用户
     */
    @Test
    public void getUserByName(){
        List<User> list = userDao.getUserByName("%播客%");
        for(User u:list){
            System.out.println(u);
        }
    }
}
