package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.User;
import com.atguigu.eduservice.entity.vo.LoginQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-03-22
 */
public interface UserMapper extends BaseMapper<User> {
    //分页查询所有用户信息
    @Select("SELECT * FROM `user` limit #{current},#{limit}")
    List<User> listAllUsers(long current,long limit);
    //添加用户信息
    //按id修改用户信息
    //按条件查询用户信息

    //添加用户信息
    @Insert("insert into user (`name`,`password`,cellphone) values(#{username},#{password},#{phone})")
    int addUser(String username,String password,String phone) ;
    //查询用户密码
    @Select("select `password` from user where name=#{username}")
    String getPassword(String username);
}
