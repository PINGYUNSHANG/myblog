package com.pys.service;

import com.pys.entity.Type;

import java.util.List;

public interface TypeService {
    //新增保存分类
    int saveType(Type type);

    //根据id查询分类
    Type queryTypeById(Long id);

    //查询所有分类
    List<Type> queryAllType();

    //根据分类名称查询分类
    Type queryTypeByName(String name);

    //编辑修改分类
    int editType(Type type);

    //删除分类
    void deleteType(Long id);

    //查询所有分类
    List<Type> getAllTypeAndBlog();
}
