package com.yuanhao.modules.system.service;

import com.yuanhao.modules.system.service.dto.UserDto;

/**
 * @author Yuanhao
 */
public interface UserService {
    /**
     * 根据用户名查询
     * @param userName /
     * @return /
     */
    UserDto findByName(String userName);
}
