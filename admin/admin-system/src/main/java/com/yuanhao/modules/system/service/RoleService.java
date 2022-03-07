package com.yuanhao.modules.system.service;

import com.yuanhao.modules.system.service.dto.RoleSmallDto;
import com.yuanhao.modules.system.service.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author Yuanhao
 */
public interface RoleService {
    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return /
     */
    List<RoleSmallDto> findByUsersId(Long id);

    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);
}
