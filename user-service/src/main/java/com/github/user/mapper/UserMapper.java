package com.github.user.mapper;

import com.github.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author peach
 * @since 2020/11/25 17:19
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户名查询
     */
    User selectByUsername(String username);
}