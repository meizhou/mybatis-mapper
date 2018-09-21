package com.meizhou.fly.mapper;

import com.meizhou.fly.model.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by meizhou on 2018/9/15.
 */
public interface IUserMapper extends IBaseMapper<User> {

    User select2(Integer id);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User insert2(Integer id);

}
