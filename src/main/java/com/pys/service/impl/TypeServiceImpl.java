package com.pys.service.impl;

import com.pys.dao.TypeDao;
import com.pys.entity.Type;
import com.pys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }
    @Transactional
    @Override
    public Type queryTypeById(Long id) {
        return typeDao.selectTypeById(id);
    }
    @Transactional
    @Override
    public List<Type> queryAllType() {
        return typeDao.selectAllType();
    }
    @Transactional
    @Override
    public Type queryTypeByName(String name) {
        return typeDao.selectTypeByName(name);

    }
    @Transactional
    @Override
    public int editType(Type type) {
        return  typeDao.updateType(type);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
       typeDao.deleteType(id);
    }

    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeDao.getAllTypeAndBlog();
    }

}
