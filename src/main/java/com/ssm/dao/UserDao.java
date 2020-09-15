package com.ssm.dao;

import com.ssm.domain.QueryVo;
import com.ssm.domain.User;

import java.util.List;

/**
 * @author zhouhai
 * @date 2020/8/18 11:42
 * @company bbaqw
 * user表操作 持久层接口
 */
public interface UserDao {
    //查询所有
    List<User> findAll();
    //添加user
    void saveUser(User user);
    //修改user
    void updateUser(User user);
    //删除user
    void deleteUser(Integer uid);
    //根据ID查询用户
    User getUserById(Integer uid);
    //根据用户名模糊查询
    List<User> getUserByName(String name);
    //查询总用户数
    int findTotal();
    //根据username查询
    List<User> findByVo(QueryVo queryVo);
}
