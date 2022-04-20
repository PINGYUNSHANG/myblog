package com.pys.dao;

import com.pys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Mapper注解： 让Mybatis找到对应的mapper，在编译的时候动态生成代理类，实现相应SQL功能
 * @Repository注解： 用来声明dao层的bean（这个注解可有可无，可以消去依赖注入的报错信息
 */
@Mapper
@Repository
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
