package com.rainy.service_user.mapper;

import com.rainy.service_user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rainy
 * @since 2023-05-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
