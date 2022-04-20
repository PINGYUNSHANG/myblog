package com.pys.dao;

import com.pys.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface FriendLinkDao {
    //查询友情链管理列表
    List<FriendLink> listFriendLink();

    //新增友情链
    int saveFriendLink(FriendLink friendLink);

    //根据网址查询友情链
    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    //根据id查询友情链
    FriendLink getFriendLink(Long id);

    //编辑友情链
    int updateFriendLink(FriendLink friendLink);

    //删除友情链
    void deleteFriendLink(Long id);

}
