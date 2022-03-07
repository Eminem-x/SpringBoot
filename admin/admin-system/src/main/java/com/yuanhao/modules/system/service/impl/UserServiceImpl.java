package com.yuanhao.modules.system.service.impl;

import com.yuanhao.exception.EntityNotFoundException;
import com.yuanhao.modules.system.domain.User;
import com.yuanhao.modules.system.repository.UserRepository;
import com.yuanhao.modules.system.service.UserService;
import com.yuanhao.modules.system.service.dto.UserDto;
import com.yuanhao.modules.system.service.mapstruct.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author Yuanhao
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }
}
