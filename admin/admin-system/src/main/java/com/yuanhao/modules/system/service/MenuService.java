package com.yuanhao.modules.system.service;

import com.yuanhao.modules.system.service.dto.MenuDto;

import java.util.List;

/**
 * @author Yuanhao
 */
public interface MenuService {

    /**
     * 根据当前用户获取菜单
     * @param currentUserId /
     * @return /
     */
    List<MenuDto> findByUser(Long currentUserId);

    /**
     * 构建菜单树
     * @param menuDtos 原始数据
     * @return /
     */
    List<MenuDto> buildTree(List<MenuDto> menuDtos);

    /**
     * 构建菜单树
     * @param menuDtos /
     * @return /
     */
    Object buildMenus(List<MenuDto> menuDtos);
}
