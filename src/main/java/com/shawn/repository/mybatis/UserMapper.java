package com.shawn.repository.mybatis;

import com.shawn.repository.UserRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Xiaoyue Xiao
 */
@Mapper
@Component
public interface UserMapper extends UserRepository {
	
}
