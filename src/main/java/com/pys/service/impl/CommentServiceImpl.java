package com.pys.service.impl;

import com.pys.dao.BlogDao;
import com.pys.dao.CommentDao;
import com.pys.entity.Comment;
import com.pys.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private BlogDao blogDao;
    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    //查询评论
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询父节点
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname = comment.getNickname();
            //查询出子评论
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            combineChildren(blogId,childComments,parentNickname);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return  comments;
    }
    //查询出子评论  parentNickname：父评论姓名
    private void combineChildren(Long blogId,List<Comment> childComments,String parentNickname){
        //判断是否有一级子评论
        if(childComments.size()>0){
            //循环找出子评论的id
            for (Comment childComment : childComments) {
                String nickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询出二级子评论
                recursively(blogId,childId,nickname);
            }
        }
    }
    //循环迭代找出子回复
    private void  recursively(Long blogId,Long chileId,String parentNickname){
        //根据子一级评论id找到子二级评论
        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId, chileId);
        if(replayComments.size()>0){
            for (Comment replayComment : replayComments) {
                String nickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname);
                Long replayCommentId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayCommentId,nickname);
            }
        }
    }

    //新增评论
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDao.saveComment(comment);
        //文章评论计数
        blogDao.getCommentCountById(comment.getBlogId());
        return comments;
    }

    @Override
    public void deleteComment(Comment comment, Long id) {
        commentDao.deleteComment(id);
        blogDao.getCommentCountById(comment.getBlogId());
    }
}
