package com.example.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bishe.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huaye
 * @since 2023-01-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
