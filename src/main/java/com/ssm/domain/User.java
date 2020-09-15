package com.ssm.domain;


import java.util.Date;

/**
 * @author zhouhai
 * @date 2020/8/18 11:16
 * @company bbaqw
 * 知识点1：每个字段和数据库都不对应!
 */
public class User {
    private Integer userId;
    private String name;//成员变量
    private Date userBirthday;
    private String userSex;
    private String addRess;//address只是修改了R为大写

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", userBirthday=" + userBirthday +
                ", userSex='" + userSex + '\'' +
                ", address='" + addRess + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *  知识点4：这里的名字和成员变量的并不同，一个是name一个是neame但是不会影响到
     *  mapper文件的使用！
     */
    public String getNeame() { //去掉get set后首字母小写为属性
        System.out.println("获取name"+name);
        return name;
    }

    /**
     * 当获取文章列表时修改set名和变量名不相同但是也能赋值也就是(Mybatisbean没有set方法也可以赋值)
     * 这是因为mybatis做了判断，如果没找到set方法直接反射的方式直接给变量赋值了
     * @param name
     */
    public void setNeame(String name) {
        System.out.println("设置name"+name);
        this.name = name;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSex() {
        System.out.println("获取sex:"+userSex);
        return userSex;
    }

    public void setUserSex(String userSex) {
        System.out.println("设置sex:"+userSex);
        this.userSex = userSex;
    }

    public String getAddRess() {
        System.out.println("地址获取："+addRess);
        return addRess;
    }

    public void setAddRess(String addRess) {
        System.out.println("地址设置："+addRess);
        this.addRess = addRess;
    }
}
