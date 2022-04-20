package com.pys.dao;

import com.pys.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {
    //新增保存的分类
    int saveType(Type type);

    //根据id查询分类
    Type selectTypeById(Long id);

    //获取所有的分类
    List<Type> selectAllType();

    //根据分类名称查询所有的分类
    Type selectTypeByName(String name);

    //编辑修改分类
    int updateType(Type type);

    //删除分类
    void deleteType(Long id);

    //查询所有的分类
    List<Type> getAllTypeAndBlog();
}
