package org.crudboy.review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.crudboy.review.Entity.User;
import org.crudboy.review.Entity.UserReadState;
import org.crudboy.review.mapper.UserMapper;
import org.crudboy.review.mapper.UserReadStateMapper;
import org.crudboy.review.service.UserService;
import org.crudboy.review.service.exception.BussinessException;
import org.crudboy.review.util.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("memberService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserReadStateMapper userReadStateMapper;

    public User createUser(String username, String password, String nickname) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", username);
        List<User> userList = userMapper.selectList(queryWrapper);

        if (!userList.isEmpty()) {
            throw new BussinessException("U01", "用户名已存在");
        }

        User user = new User();
        int salt = new Random().nextInt(1000) + 1000;
        user.setUsername(username);
        user.setPassword(MD5Utils.md5Digest(password, salt));
        user.setSalt(salt);
        user.setNickname(nickname);
        user.setCreateTime(new Date());

        userMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public User checkLogin(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BussinessException("M02", "用户不存在");
        }

        String md5 = MD5Utils.md5Digest(password, user.getSalt());
        if (!md5.equals(user.getPassword())) {
            throw new BussinessException("M03", "输入密码有误");
        }
        return user;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public UserReadState selectUserReadState(Long bookId, Long userId) {
        QueryWrapper<UserReadState> queryWrapper = new QueryWrapper<UserReadState>();
        queryWrapper.eq("book_id", bookId);
        queryWrapper.eq("user_id", userId);

        return userReadStateMapper.selectOne(queryWrapper);
    }

    public UserReadState updateUserReadState(Long bookId, Long userId, Integer readState) {
        QueryWrapper<UserReadState> queryWrapper = new QueryWrapper<UserReadState>();
        UserReadState userReadState = selectUserReadState(bookId, userId);
        if (userReadState == null) {
            userReadState = new UserReadState();
            userReadState.setReadState(readState);
            userReadState.setBookId(bookId);
            userReadState.setUserId(userId);
            userReadState.setCreateTime(new Date());
        } else {
            userReadState.setReadState(readState);
            userReadStateMapper.updateById(userReadState);
        }
        return userReadState;
    }

    public User selectById(Long id) {
        return userMapper.selectById(id);
    }
}
