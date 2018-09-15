package com.meizhou.fly.mapper;

import com.meizhou.fly.model.User;

import java.util.List;

/**
 * Created by meizhou on 2018/9/15.
 */
public interface IUserMapper {

    int select();

    List<User> select2();
}
