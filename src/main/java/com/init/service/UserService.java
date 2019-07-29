package com.init.service;

import com.init.entity.User;
import com.init.model.UserDto;


public interface UserService extends BaseService<User> {

    User toEntity(UserDto dto);

    UserDto toDto(User entity);
}
