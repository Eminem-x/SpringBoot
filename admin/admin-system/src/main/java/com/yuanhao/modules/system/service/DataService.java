package com.yuanhao.modules.system.service;

import com.yuanhao.modules.system.service.dto.UserDto;

import java.util.List;

/**
 * @author Yuanhao
 */
public interface DataService {

    /**
     * 获取数据权限
     *
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);
}
