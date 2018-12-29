package com.meizhou.fly.mapper;

import com.meizhou.fly.model.UserInfo;
import com.meizhou.mybatis.mapper.ICommonMapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by meizhou on 2018/9/15.
 */
public interface IUserInfoMapper extends ICommonMapper<UserInfo> {

    @Select("select * from user_info where id=5")
    void select2();

}
