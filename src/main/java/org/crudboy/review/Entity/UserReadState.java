package org.crudboy.review.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 用户阅读状态实体
 */
@TableName("user_read_state")
public class UserReadState {

    @TableId(type = IdType.AUTO)
    private Long rsId;
    private Long bookId;
    private Long userId;
    private Integer readState;
    private Date createTime;

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getReadState() {
        return readState;
    }

    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserReadState{" +
                "rsId=" + rsId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", readState=" + readState +
                ", createTime=" + createTime +
                '}';
    }
}