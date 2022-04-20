package com.pys.dao;

import com.pys.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论直接在前端页面上进行操作，没有后台管理，
 * 只是区分的管理员和普通用户，管理员可以对评论进行删除，
 * 因此需要查询评论列表（listCommentByBlogId）、
 * 添加保存评论（saveComment）、
 * 删除评论(deleteComment)
 *
 * 查询评论列表的时候，
 * 需要将评论和回复加以区分，
 * 根据评论功能来看，
 * 有父评论、子评论（回复），
 * 并且父子评论在前端显示的位置有不同
 *
 * 根据id为“-1” 和 博客id 查询出所有 父评论（父级评论id为‘-1’）
 * 根据父评论的id查询出一级子回复
 * 根据子回复的id循环迭代查询出所有子集回复
 * 将查询出来的子回复放到一个集合中
 *
 * 查询父级评论（findByBlogIdParentIdNull）、
 * 查询一级回复（findByBlogIdParentIdNotNull）、
 * 查询二级回复（findByBlogIdAndReplayId）
 *
 */
@Mapper
@Repository
public interface CommentDao {

    //查询父级评论  根据id为“-1”和博客id查询出所有父评论（父级评论id为‘-1’）
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询一级评论  根据父评论的id查询出一级子回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    //查询二级回复  根据子回复的id循环迭代查询出所有子集回复
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    //添加一个评论
    int saveComment(Comment comment);

    //删除评论
    void deleteComment(Long id);

}
