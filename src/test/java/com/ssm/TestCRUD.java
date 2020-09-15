package com.ssm;

import com.ssm.dao.UserDao;
import com.ssm.domain.QueryVo;
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
     * 知识点5：查询所有User 只有没修改变量名的addRess赋值进去了
     * 因为在windows中mysql数据库不区分大小写（或者数据库在linux中设置了不区分大小写）
     * 但是像其他字段名字都变了的就无法赋值了
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
        u.setNeame("塔睿2");
        u.setUserSex("女");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        u.setUserBirthday(sdf.parse("1998-12-20"));
        u.setAddRess("河北雄安新区a幢67号");
        System.out.println("保存前:"+u);
        userDao.saveUser(u);
        //用于获取插入后文章ID
        System.out.println("保存后:"+u);
    }

    /**
     * 更新用户
     */
    @Test
    public void testUpdUser() throws Exception{
        User u = new User();
        u.setUserId(62);
        u.setNeame("阿狸");
        u.setUserSex("女");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        u.setUserBirthday(sdf.parse("2994-12-20"));
        u.setAddRess("天河苏氏18号");
        userDao.updateUser(u);
    }

    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser() throws Exception{
        userDao.deleteUser(61);
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
//        List<User> list = userDao.getUserByName("%播客%");
        List<User> list = userDao.getUserByName("播客");
        for(User u:list){
            System.out.println(u);
        }
    }

    /**
     * 查询总用户数
     */
    @Test
    public void findTotal(){
        int count=userDao.findTotal();
        System.out.println(count);
    }


    /**
     * 根据自定义实体类获取数据
     */
    @Test
    public void findByVo(){
        QueryVo q = new QueryVo();
        User u = new User();
        u.setNeame("%播客%");
        q.setUser(u);
        List<User> list = userDao.findByVo(q);
        for (User user:list){
            System.out.println(user);
        }
    }


}
