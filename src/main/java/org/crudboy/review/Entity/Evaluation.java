package org.crudboy.review.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 评论实体
 */
@TableName("evaluation")
public class Evaluation {
    @TableId(type = IdType.AUTO)
    private Long evaluationId;
    private Long bookId;
    private String content;
    private Integer score;
    private Long userId;
    private Date createTime;
    private Integer enjoy;
    private String state;
    private String disableReason;
    private Date disableTime;

    @TableField(exist = false)
    private Book book;

    @TableField(exist = false)
    private User user;

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(Integer enjoy) {
        this.enjoy = enjoy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", bookId=" + bookId +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", enjoy=" + enjoy +
                ", state='" + state + '\'' +
                ", disableReason='" + disableReason + '\'' +
                ", disableTime=" + disableTime +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
