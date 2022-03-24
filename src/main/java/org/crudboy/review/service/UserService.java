package org.crudboy.review.service;

import org.crudboy.review.Entity.User;
import org.crudboy.review.Entity.UserReadState;

public interface UserService {

    /**
     * 创建新用户
     * @param username
     * @param password
     * @param nickname 昵称
     * @return user 创建对象
     */
    User createUser(String username, String password, String nickname);

    /**
     * 检查对象是否登录并返回登录对象
     * @param username
     * @param password
     * @return user 登录对象
     */
    User checkLogin(String username, String password);


    /**
     * 查询用户阅读读书的状态
     * @param bookId
     * @param userId
     * @return
     */
    UserReadState selectUserReadState(Long bookId, Long userId);

    /**
     * 更新用户阅读状态并返回更新后的值
     * @param bookId
     * @param userId
     * @param readState 阅读状态
     * @return
     */
    UserReadState updateUserReadState(Long bookId, Long userId, Integer readState);
}
